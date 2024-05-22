package ru.rightcode.patient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.patient.dto.response.RehabProgramResponse;
import ru.rightcode.patient.exception.NotFoundException;
import ru.rightcode.patient.mapper.DoctorResponseMapper;
import ru.rightcode.patient.mapper.rehab.RehabProgramMapper;
import ru.rightcode.patient.model.Patient;
import ru.rightcode.patient.model.RehabProgram;
import ru.rightcode.patient.repository.RehabProgramRepository;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class RehabProgramService {

    private final RehabProgramRepository rehabProgramRepository;
    private final RehabProgramMapper rehabProgramMapper;

    // Получение программы реабилитации по пациенту без доктора и пациента
    @Transactional
    protected RehabProgram getRehabProgramByPatient(Patient patient) {
        return rehabProgramRepository.findByPatient(patient);
    }

    // Проверка актуальности программы реабилитации
    private RehabProgram checkRehabProgramIsCurrent(RehabProgram rehabProgram) {
        if (!rehabProgram.getIsCurrent()) {
            throw new NotFoundException("Rehab program not found");
        }
        return rehabProgram;
    }

//    @Cacheable(value = "RehabProgramService::getRehabProgramResponseByPatient", key = "#patient.id")
    public RehabProgramResponse getRehabProgramResponseByPatient(Patient patient) {
        return rehabProgramMapper.toRehabProgramResponse(getRehabProgramByPatient(patient));
    }

}
