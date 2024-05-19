package ru.rightcode.patient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.patient.dto.response.DoctorResponse;
import ru.rightcode.patient.dto.response.PatientResponse;
import ru.rightcode.patient.dto.response.RehabProgramResponse;
import ru.rightcode.patient.exception.NotFoundException;
import ru.rightcode.patient.mapper.PatientResponseMapper;
import ru.rightcode.patient.model.Patient;
import ru.rightcode.patient.repository.PatientRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientResponseMapper patientResponseMapper;

    private final RehabProgramService rehabProgramService;

    @Transactional
    public Patient getPatientByUsername(String username) {
        return patientRepository.getPatientByUserUsername(username)
                .orElseThrow(() -> new NotFoundException("Пациент не найден"));
    }

    public PatientResponse getYourSelf(String username){
        return patientResponseMapper.toResponse(getPatientByUsername(username));
    }

    // TODO: doctor
    public DoctorResponse getDoctor(String username){
        return rehabProgramService.getDoctorResponseByPatient(getPatientByUsername(username));
    }

    // TODO: История проведения реабилитаций
    // TODO: Результаты реабилитаций
    // TODO: ПРограмма реабилитации
    @Transactional
    public RehabProgramResponse getRehabProgram(String username){
        Patient patientFromDB = getPatientByUsername(username);
        return rehabProgramService.getRehabProgramResponseByPatient(patientFromDB);
    }
    // TODO: Модули реабилитации
    // TODO: get Эпикриз
    // TODO: get Вводная анкета
    // TODO: get Выходная анкета
}
