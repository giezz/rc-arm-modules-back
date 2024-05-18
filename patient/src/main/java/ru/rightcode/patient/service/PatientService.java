package ru.rightcode.patient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.patient.dto.PatientInfo;
import ru.rightcode.patient.dto.response.PatientResponse;
import ru.rightcode.patient.exception.NotFoundException;
import ru.rightcode.patient.mapper.PatientResponseMapper;
import ru.rightcode.patient.model.Patient;
import ru.rightcode.patient.model.User;
import ru.rightcode.patient.repository.PatientRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientResponseMapper patientResponseMapper;

    public PatientResponse getYourSelf(String username){
        Patient patientFromDB = patientRepository.findByUserUsername(username, Patient.class)
                .orElseThrow(() -> new NotFoundException("Пациент не найден"));
        return patientResponseMapper.toResponse(patientFromDB);
    }

    // TODO: История проведения реабилитаций
    // TODO: Результаты реабилитаций
    // TODO: ПРограмма реабилитации
    // TODO: Модули реабилитации
    // TODO: get Эпикриз
    // TODO: get Вводная анкета
    // TODO: get Выходная анкета
}
