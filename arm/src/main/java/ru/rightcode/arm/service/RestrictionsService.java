package ru.rightcode.arm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.model.RehabProgram;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class RestrictionsService {

    private final DoctorService doctorService;

    public boolean canDoctorEditRehabProgram(RehabProgram rehabProgram, String login) {
        Long doctorId = doctorService.getDoctorIdByLogin(login).getId();

        return rehabProgram.canDoctorEdit(doctorId) && rehabProgram.getIsCurrent();
    }

}
