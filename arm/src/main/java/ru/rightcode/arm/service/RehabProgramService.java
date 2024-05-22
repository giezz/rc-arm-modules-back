package ru.rightcode.arm.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.projection.DoctorIdInfo;
import ru.rightcode.arm.dto.request.*;
import ru.rightcode.arm.dto.response.PageableResponse;
import ru.rightcode.arm.dto.response.ProtocolResponse;
import ru.rightcode.arm.dto.response.RehabProgramResponse;
import ru.rightcode.arm.exceptions.NoPermissionException;
import ru.rightcode.arm.exceptions.PatientNotFoundException;
import ru.rightcode.arm.mapper.RehabProgramResponseMapper;
import ru.rightcode.arm.model.Module;
import ru.rightcode.arm.model.*;
import ru.rightcode.arm.repository.*;

import java.time.Instant;
import java.util.List;

import static ru.rightcode.arm.repository.specification.RehabProgramSpecification.hasDoctorIdEqual;
import static ru.rightcode.arm.repository.specification.RehabProgramSpecification.params;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class RehabProgramService {

    private final RehabProgramRepository rehabProgramRepository;
    private final PatientRepository patientRepository;
    private final PatientStatusRepository patientStatusRepository;
    private final ProgramFormRepository programFormRepository;
    private final FormRepository formRepository;
    private final ModuleRepository moduleRepository;

    private final DoctorService doctorService;
    private final ProtocolService protocolService;
    private final RestrictionsService restrictionsService;

    private final RehabProgramResponseMapper rehabProgramResponseMapper;

    public PageableResponse<RehabProgramResponse> getProgramsByCurrentDoctor(int pageNumber,
                                                                                   int pageSize,
                                                                                   String doctorLogin,
                                                                                   RehabProgramRequest request) {
        DoctorIdInfo doctor = doctorService.getDoctorIdByLogin(doctorLogin);
        Specification<RehabProgram> specification = params(request).and(hasDoctorIdEqual(doctor.getId()));
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<RehabProgram> page = rehabProgramRepository.findAll(specification, pageable);

        return new PageableResponse<>(
                page.get().map(rehabProgramResponseMapper::map).toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements()
        );
    }

    public RehabProgramResponse getProgram(Long id) {
        return rehabProgramResponseMapper.mapFull(getProgramById(id));
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
    public List<ProtocolResponse> createProtocol(String doctorLogin, Long programId, CreateProtocolRequest request) {
        RehabProgram rehabProgram = getProgramOrThrowIfDoctorCantEdit(programId, doctorLogin);
        Protocol protocol = protocolService.createProtocol(request);
        if (protocol.getIsFinal()) {
            completeRehabProgram(rehabProgram);
            protocolService.createMedCardRehabHistoryRecord(rehabProgram.getPatient(), rehabProgram.getDoctor(), request);
        }
        rehabProgram.addProtocol(protocol);
        rehabProgramRepository.save(rehabProgram);

        return getProtocols(rehabProgram.getId());
    }

    public List<ProtocolResponse> getProtocols(Long id) {
        return protocolService.getProtocolsByProgramId(id);
    }

    @Transactional
    public RehabProgramResponse addForm(String doctorLogin, AddFormRequest request, Long programId) {
        RehabProgram rehabProgram = getProgramOrThrowIfDoctorCantEdit(programId, doctorLogin);
        Form form = formRepository.findById(request.formId()).orElseThrow(EntityExistsException::new);
        rehabProgram.addForm(new ProgramForm(form, new Type(request.formType().getCode())));

        return rehabProgramResponseMapper.mapFull(rehabProgramRepository.save(rehabProgram));
    }

    @Transactional
    public RehabProgramResponse deleteForm(String doctorLogin, Long programFormId, Long programId) {
        RehabProgram rehabProgram = getProgramOrThrowIfDoctorCantEdit(programId, doctorLogin);
        rehabProgram.deleteFom(programFormRepository.findById(programFormId).orElseThrow());

        return rehabProgramResponseMapper.mapFull(rehabProgramRepository.save(rehabProgram));
    }

    @Transactional
    public RehabProgramResponse addModule(String doctorLogin, AddModuleRequest request, Long programId) {
        RehabProgram rehabProgram = getProgramOrThrowIfDoctorCantEdit(programId, doctorLogin);
        rehabProgram.addModule(new Module(request.name()));

        return rehabProgramResponseMapper.mapFull(rehabProgramRepository.save(rehabProgram));
    }

    @Transactional
    public RehabProgramResponse deleteModule(String doctorLogin, Long moduleId, Long programId) {
        RehabProgram rehabProgram = getProgramOrThrowIfDoctorCantEdit(programId, doctorLogin);
        rehabProgram.deleteModule(moduleRepository.findById(moduleId).orElseThrow());

        return rehabProgramResponseMapper.mapFull(rehabProgramRepository.save(rehabProgram));
    }

    private RehabProgram getProgramById(Long id) {
        RehabProgram rehabProgram = rehabProgramRepository.findByIdWithProgramForms(id)
                .orElseThrow(() -> new EntityNotFoundException("Программа реабилитации не найдена"));
        rehabProgramRepository.findByIdWithModules(id)
                .orElseThrow(() -> new EntityNotFoundException("Программа реабилитации не найдена"));

        return rehabProgram;
    }

    private RehabProgram getProgramOrThrowIfDoctorCantEdit(Long id, String doctorLogin) {
        RehabProgram rehabProgram = getProgramById(id);
        if (!restrictionsService.canDoctorEditRehabProgram(rehabProgram, doctorLogin)) {
            throw new NoPermissionException("Нет прав на редактирование программы реабилитации");
        }

        return rehabProgram;
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
