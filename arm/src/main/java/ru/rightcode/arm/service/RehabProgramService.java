package ru.rightcode.arm.service;

import jakarta.persistence.EntityExistsException;
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
import ru.rightcode.arm.repository.RehabProgramRepository;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class RehabProgramService {

    private final RehabProgramRepository rehabProgramRepository;
    private final RestrictionsService restrictionsService;
    private final RehabProgramResponseMapper rehabProgramResponseMapper;

    private final DoctorService doctorService;

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

        return rehabProgramResponseMapper.map(rehabProgramRepository.save(rehabProgram));
    }

    @Transactional
    public RehabProgramResponse addForm(String doctorLogin, AddFormRequest request, Long programId) {
        DoctorIdInfo doctor = doctorService.getDoctorIdByLogin(doctorLogin);
        if (!restrictionsService.canDoctorEditRehabProgram(doctor.getId(), programId)) {
            throw new NoPermissionException("Нет прав на редактирование данной программы реабилитации");
        }
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
        DoctorIdInfo doctor = doctorService.getDoctorIdByLogin(doctorLogin);
        if (!restrictionsService.canDoctorEditRehabProgram(doctor.getId(), programId)) {
            throw new NoPermissionException("Нет прав на редактирование данной программы реабилитации");
        }
        RehabProgram rehabProgram = rehabProgramRepository.findById(programId).orElseThrow(
                EntityNotFoundException::new
        );
        Module module = new Module();
        module.setName(request.name());
        rehabProgram.addModule(module);

        return rehabProgramResponseMapper.map(rehabProgramRepository.save(rehabProgram));
    }
}
