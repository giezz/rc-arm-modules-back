package ru.rightcode.patient.service;

import com.sun.jdi.request.ExceptionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.patient.dto.response.DoctorResponse;
import ru.rightcode.patient.dto.response.RehabProgramResponse;
import ru.rightcode.patient.exception.NotFoundException;
import ru.rightcode.patient.mapper.DoctorResponseMapper;
import ru.rightcode.patient.mapper.RehabProgramMapper;
import ru.rightcode.patient.model.Patient;
import ru.rightcode.patient.model.RehabProgram;
import ru.rightcode.patient.repository.RehabProgramRepository;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class RehabProgramService {

    private final RehabProgramRepository rehabProgramRepository;
    private final RehabProgramMapper rehabProgramMapper;

    private final DoctorResponseMapper doctorResponseMapper;

    // Получение программы реабилитации по пациенту без доктора и пациента
    @Transactional
    protected RehabProgram getRehabProgramByPatient(Patient patient) {
        return rehabProgramRepository.findByPatient(patient);
    }

    // Получение программы реабилитации по пациенту для получения доктора
    @Transactional
    protected RehabProgram getDoctorByPatient(Patient patient) {
        return rehabProgramRepository.findDoctorByPatient(patient)
                .orElseThrow(() -> new NotFoundException("Doctor not found"));
    }

    // Проверка актуальности программы реабилитации
    private RehabProgram checkRehabProgramIsCurrent(RehabProgram rehabProgram) {
        if (!rehabProgram.getIsCurrent()) {
            throw new NotFoundException("Rehab program not found");
        }
        return rehabProgram;
    }

    public RehabProgramResponse getRehabProgramResponseByPatient(Patient patient) {
        return rehabProgramMapper.toRehabProgramResponse(getRehabProgramByPatient(patient));
    }

    @Transactional
    public DoctorResponse getDoctorResponseByPatient(Patient patient) {
        return doctorResponseMapper.toResponse(
                checkRehabProgramIsCurrent(
                        getDoctorByPatient(patient)
                ).getDoctor());
    }
}
