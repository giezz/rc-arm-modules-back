package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.DoctorIdInfo;
import ru.rightcode.arm.repository.DoctorRepository;
import ru.rightcode.arm.repository.PatientRepository;
import ru.rightcode.arm.repository.RehabProgramRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestrictionsService {

    private final RehabProgramRepository rehabProgramRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public boolean canDoctorEditRehabProgram(Long doctorId, Long rehabProgramId) {
        return rehabProgramRepository.checkIfDoctorCanEdit(doctorId, rehabProgramId);
    }

    public boolean canDoctorCreateRehaProgram(Long doctorId, Long patientId) {
        return patientRepository.checkIfDoctorCanCreateRehabProgram(doctorId, patientId);
    }
}
