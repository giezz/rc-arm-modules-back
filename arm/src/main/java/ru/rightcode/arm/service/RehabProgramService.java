package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.DoctorIdInfo;
import ru.rightcode.arm.dto.request.AddFormRequest;
import ru.rightcode.arm.dto.request.AddModuleRequest;
import ru.rightcode.arm.dto.request.CreateRehabProgramRequest;
import ru.rightcode.arm.dto.response.RehabProgramResponse;
import ru.rightcode.arm.exceptions.NoPermissionException;
import ru.rightcode.arm.mapper.RehabProgramResponseMapper;
import ru.rightcode.arm.model.Module;
import ru.rightcode.arm.model.Patient;
import ru.rightcode.arm.model.RehabProgram;
import ru.rightcode.arm.repository.DoctorRepository;
import ru.rightcode.arm.repository.RehabProgramRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RehabProgramService {

    private final RehabProgramRepository rehabProgramRepository;

    private final DoctorRepository doctorRepository;

    private final RehabProgramResponseMapper rehabProgramResponseMapper;

    public RehabProgramResponse getCurrent(String doctorLogin, Long patientId) {
        DoctorIdInfo doctor = getDoctorByLogin(doctorLogin);
        RehabProgram rehabProgram = rehabProgramRepository
                .findByDoctorIdAndPatientIdAndIsCurrentTrue(doctor.getId(), patientId).orElseThrow();
        return rehabProgramResponseMapper.map(rehabProgram);
    }

    @Transactional
    public RehabProgramResponse create(String doctorLogin, CreateRehabProgramRequest request) {
        //check if current exists
        DoctorIdInfo doctor = getDoctorByLogin(doctorLogin);
        RehabProgram rehabProgram = new RehabProgram();
        rehabProgram.setDoctorById(doctor.getId());
        rehabProgram.setPatient(new Patient(request.patientId()));


        return rehabProgramResponseMapper.map(rehabProgramRepository.save(rehabProgram));
    }

    @Transactional
    public RehabProgramResponse addForm(String doctorLogin, AddFormRequest request, Long programId) {
        DoctorIdInfo doctor = getDoctorByLogin(doctorLogin);
        checkIfDoctorCanEdit(doctor.getId(), programId);

        if (request.formType() == AddFormRequest.FormType.START) {
            rehabProgramRepository.addStartForm(request.formId(), programId);
        } else if (request.formType() == AddFormRequest.FormType.END){
            rehabProgramRepository.addEndForm(request.formId(), programId);
        }

        return rehabProgramResponseMapper.map(
                rehabProgramRepository.findById(programId).orElseThrow(EntityNotFoundException::new)
        );
    }

    @Transactional
    public RehabProgramResponse addModule(String doctorLogin, AddModuleRequest request, Long programId) {
        DoctorIdInfo doctor = getDoctorByLogin(doctorLogin);
        checkIfDoctorCanEdit(doctor.getId(), programId);

        RehabProgram rehabProgram = rehabProgramRepository.findById(programId).orElseThrow(
                EntityNotFoundException::new
        );

        Module module = new Module();
        module.setName(request.name());

        rehabProgram.addModule(module);

        return rehabProgramResponseMapper.map(rehabProgramRepository.save(rehabProgram));
    }

    public void checkIfDoctorCanEdit(Long doctorId, Long programId) {
        boolean canEdit = rehabProgramRepository.checkIfDoctorCanEdit(doctorId, programId);
        if (!canEdit) {
            throw new NoPermissionException("Нет прав на редактирование данной программы реабилитации");
        }
    }

    public DoctorIdInfo getDoctorByLogin(String login) {
        return doctorRepository
                .findByUserUsername(login, DoctorIdInfo.class)
                .orElseThrow(EntityNotFoundException::new);
    }
}
