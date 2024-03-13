package ru.rightcode.arm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.repository.DoctorRepository;
import ru.rightcode.arm.repository.PatientRepository;
import ru.rightcode.arm.repository.RehabProgramRepository;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class RestrictionsService {

    private final RehabProgramRepository rehabProgramRepository;

    public boolean canDoctorEditRehabProgram(Long doctorId, Long rehabProgramId) {
        return rehabProgramRepository.checkIfDoctorCanEdit(doctorId, rehabProgramId);
    }
}
