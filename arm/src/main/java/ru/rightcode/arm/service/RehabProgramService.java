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
import ru.rightcode.arm.mapper.RehabProgramResponseMapper;
import ru.rightcode.arm.model.Doctor;
import ru.rightcode.arm.model.Module;
import ru.rightcode.arm.model.Patient;
import ru.rightcode.arm.model.RehabProgram;
import ru.rightcode.arm.repository.DoctorRepository;
import ru.rightcode.arm.repository.RehabProgramRepository;

import javax.print.Doc;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RehabProgramService {

    private final RehabProgramRepository rehabProgramRepository;

    private final DoctorRepository doctorRepository;

    private final RehabProgramResponseMapper rehabProgramResponseMapper;

    /*
        TODO:
            Необходимо реализовать проверку: может ли врач редактировать программу реабилитации.
     */

    public RehabProgramResponse getCurrent(String doctorLogin, Long patientId) {
        DoctorIdInfo doctor = doctorRepository
                .findByUserUsername(doctorLogin, DoctorIdInfo.class)
                .orElseThrow(EntityNotFoundException::new);
        RehabProgram rehabProgram = rehabProgramRepository
                .findByDoctorIdAndPatientIdAndIsCurrentTrue(doctor.getId(), patientId).orElseThrow();
        return rehabProgramResponseMapper.map(rehabProgram);
    }

    @Transactional
    public RehabProgramResponse create(String doctorLogin, CreateRehabProgramRequest request) {
        //check if current exists
        DoctorIdInfo doctor = doctorRepository
                .findByUserUsername(doctorLogin, DoctorIdInfo.class)
                .orElseThrow(EntityNotFoundException::new);
        RehabProgram rehabProgram = new RehabProgram();
        rehabProgram.setDoctorById(doctor.getId());
        rehabProgram.setPatient(new Patient(request.patientId()));


        return rehabProgramResponseMapper.map(rehabProgramRepository.save(rehabProgram));
    }

    @Transactional
    public RehabProgramResponse addForm(String doctorLogin, AddFormRequest request, Long programId) {
        DoctorIdInfo doctor = doctorRepository
                .findByUserUsername(doctorLogin, DoctorIdInfo.class)
                .orElseThrow(EntityNotFoundException::new);

        final int rowAffected;

        if (request.formType() == AddFormRequest.FormType.START) {
            rowAffected = rehabProgramRepository.addStartForm(request.formId(), programId, doctor.getId());
        } else {
            rowAffected = rehabProgramRepository.addEndForm(request.formId(), programId, doctor.getId());
        }

        if (rowAffected == 0) {
            throw new EntityNotFoundException();
        }

        return rehabProgramResponseMapper.map(
                rehabProgramRepository.findById(programId).orElseThrow(
                        EntityNotFoundException::new
                )
        );
    }

    @Transactional
    public RehabProgramResponse addModule(String doctorLogin, AddModuleRequest request, Long programId) {
        RehabProgram rehabProgram = rehabProgramRepository.findById(programId).orElseThrow(
                EntityNotFoundException::new
        );

        Module module = new Module();
        module.setName(request.name());

        rehabProgram.addModule(module);

        return rehabProgramResponseMapper.map(
                rehabProgramRepository.save(rehabProgram)
        );
    }
}
