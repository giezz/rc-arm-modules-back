package ru.rightcode.arm.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.projection.DoctorIdInfo;
import ru.rightcode.arm.dto.projection.RehabProgramInfo;
import ru.rightcode.arm.dto.request.AddFormRequest;
import ru.rightcode.arm.dto.request.AddModuleRequest;
import ru.rightcode.arm.dto.request.CreateRehabProgramRequest;
import ru.rightcode.arm.dto.response.RehabProgramResponse;
import ru.rightcode.arm.exceptions.NoPermissionException;
import ru.rightcode.arm.mapper.RehabProgramResponseMapper;
import ru.rightcode.arm.model.Module;
import ru.rightcode.arm.model.*;
import ru.rightcode.arm.repository.RehabProgramRepository;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class RehabProgramService {

    private final RehabProgramRepository rehabProgramRepository;
    private final RehabProgramResponseMapper rehabProgramResponseMapper;

    private final DoctorService doctorService;

    public List<RehabProgramInfo> getProgramsByCurrentDoctor(String doctorLogin) {
        DoctorIdInfo doctor = doctorService.getDoctorIdByLogin(doctorLogin);

        return rehabProgramRepository.findAllByDoctorId(doctor.getId());
    }

    @Transactional
    public RehabProgramResponse create(String doctorLogin, CreateRehabProgramRequest request) {
        DoctorIdInfo doctor = doctorService.getDoctorIdByLogin(doctorLogin);

        if (rehabProgramRepository.checkIfCurrentExists(doctor.getId(), request.patientId())) {
            throw new EntityExistsException("Программа реабилтации уже существует");
        }

        RehabProgram rehabProgram = new RehabProgram();
        rehabProgram.setDoctorById(doctor.getId());
        rehabProgram.setPatient(new Patient(request.patientId()));
        rehabProgram.setIsCurrent(true);
        rehabProgram.setCreatedAt(Instant.now());

        return rehabProgramResponseMapper.map(rehabProgramRepository.save(rehabProgram));
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

        return rehabProgramResponseMapper.map(rehabProgramRepository.save(rehabProgram));
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

        return rehabProgramResponseMapper.map(rehabProgramRepository.save(rehabProgram));
    }

}
