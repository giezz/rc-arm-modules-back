package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.DoctorIdInfo;
import ru.rightcode.arm.repository.DoctorRepository;
import ru.rightcode.arm.repository.RehabProgramRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestrictionsService {

    private final RehabProgramRepository rehabProgramRepository;
    private final DoctorRepository doctorRepository;

    public boolean canDoctorEditRehabProgram(Long doctorId, Long rehabProgramId) {
        return rehabProgramRepository.checkIfDoctorCanEdit(doctorId, rehabProgramId);
    }

    public DoctorIdInfo getDoctorByLogin(String login) {
        return doctorRepository
                .findByUserUsername(login, DoctorIdInfo.class)
                .orElseThrow(EntityNotFoundException::new);
    }
}
