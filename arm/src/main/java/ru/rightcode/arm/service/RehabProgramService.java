package ru.rightcode.arm.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.projection.DoctorIdInfo;
import ru.rightcode.arm.dto.request.*;
import ru.rightcode.arm.dto.response.RehabProgramResponse;
import ru.rightcode.arm.exceptions.NoPermissionException;
import ru.rightcode.arm.exceptions.PatientNotFoundException;
import ru.rightcode.arm.mapper.RehabProgramResponseMapper;
import ru.rightcode.arm.model.Module;
import ru.rightcode.arm.model.*;
import ru.rightcode.arm.repository.PatientRepository;
import ru.rightcode.arm.repository.PatientStatusRepository;
import ru.rightcode.arm.repository.RehabProgramRepository;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

import static ru.rightcode.arm.repository.specification.RehabProgramSpecification.*;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class RehabProgramService {

    private final RehabProgramRepository rehabProgramRepository;
    private final PatientRepository patientRepository;
    private final PatientStatusRepository patientStatusRepository;

    private final RehabProgramResponseMapper rehabProgramResponseMapper;

    private final DoctorService doctorService;
    private final ProtocolService protocolService;

    public List<RehabProgramResponse> getProgramsByCurrentDoctor(String doctorLogin, RehabProgramRequest request) {
        DoctorIdInfo doctor = doctorService.getDoctorIdByLogin(doctorLogin);
        Specification<RehabProgram> specification = params(request);
        specification = specification.and(hasDoctorIdEqual(doctor.getId()));

        return rehabProgramRepository.findAll(specification)
                .stream()
                .map(rehabProgramResponseMapper::map)
                .toList();
    }

    @Transactional
    public RehabProgramResponse create(String doctorLogin, CreateRehabProgramRequest request) {
        DoctorIdInfo doctor = doctorService.getDoctorIdByLogin(doctorLogin);
        if (rehabProgramRepository.checkIfCurrentExists(doctor.getId(), request.patientId())) {
            throw new EntityExistsException("Программа реабилтации уже существует");
        }
        Patient patient = patientRepository.findById(request.patientId())
                .orElseThrow(() -> new PatientNotFoundException(request.patientId()));
        RehabProgram rehabProgram = createCurrentProgram(doctor.getId(), patient);
        PatientStatus patientStatus = patientStatusRepository.findByName("Проходит реабилитацию")
                        .orElseThrow(EntityNotFoundException::new);
        patient.setPatientStatus(patientStatus);

        return rehabProgramResponseMapper.mapFull(rehabProgramRepository.save(rehabProgram));
    }

    @Transactional
    public RehabProgramResponse createProtocol(String doctorLogin, Long rehabProgramId, CreateProtocolRequest request) {
//        DoctorIdInfo doctor = doctorService.getDoctorIdByLogin(doctorLogin);
        RehabProgram rehabProgram = rehabProgramRepository.findById(rehabProgramId)
                .orElseThrow(EntityNotFoundException::new);
        completeRehabProgram(rehabProgram);
        Protocol protocol = protocolService.createProtocol(request);
        rehabProgram.addProtocol(protocol);

        return rehabProgramResponseMapper.mapFull(rehabProgramRepository.save(rehabProgram));
    }

    public void getProtocol() {

    }

    @Transactional
    public RehabProgramResponse addForm(String doctorLogin, AddFormRequest request, Long programId) {
        DoctorIdInfo doctor = doctorService.getDoctorIdByLogin(doctorLogin);
        RehabProgram rehabProgram = rehabProgramRepository.findById(programId).orElseThrow(EntityNotFoundException::new);

        if (!Objects.equals(rehabProgram.getDoctor().getId(), doctor.getId()) || !rehabProgram.getIsCurrent()) {
            throw new NoPermissionException("Нет прав на редактирование программы реабилитации");
        }

        ProgramForm programForm = new ProgramForm();
        programForm.setForm(new Form(request.formId()));
        programForm.setType(new Type(request.formType().getCode()));
        rehabProgram.addForm(programForm);

        return rehabProgramResponseMapper.mapFull(rehabProgramRepository.save(rehabProgram));
    }

    @Transactional
    public RehabProgramResponse addModule(String doctorLogin, AddModuleRequest request, Long programId) {
        DoctorIdInfo doctor = doctorService.getDoctorIdByLogin(doctorLogin);
        RehabProgram rehabProgram = rehabProgramRepository.findById(programId).orElseThrow(EntityNotFoundException::new);

        if (!Objects.equals(rehabProgram.getDoctor().getId(), doctor.getId()) || !rehabProgram.getIsCurrent()) {
            throw new NoPermissionException("Нет прав на редактирование программы реабилитации");
        }

        Module module = new Module();
        module.setName(request.name());
        rehabProgram.addModule(module);

        return rehabProgramResponseMapper.mapFull(rehabProgramRepository.save(rehabProgram));
    }

    private RehabProgram createCurrentProgram(Long doctorId, Patient patient) {
        RehabProgram rehabProgram = new RehabProgram();
        rehabProgram.setDoctorById(doctorId);
        rehabProgram.setPatient(patient);
        rehabProgram.setIsCurrent(true);
        rehabProgram.setCreatedAt(Instant.now());
        rehabProgram.setStartDate(Instant.now());
        return rehabProgram;
    }

    private void completeRehabProgram(RehabProgram rehabProgram) {
        rehabProgram.setIsCurrent(false);
        rehabProgram.setEndDate(Instant.now());
        Patient patient = rehabProgram.getPatient();
        PatientStatus patientStatus = patientStatusRepository.findByName("Проходил реабилитацию ранее")
                .orElseThrow(EntityNotFoundException::new);
        patient.setPatientStatus(patientStatus);
    }

}
