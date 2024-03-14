package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rightcode.arm.dto.projection.DoctorIdInfo;
import ru.rightcode.arm.repository.DoctorRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public <T> Optional<T> getByLogin(String login, Class<T> projection) {
        return doctorRepository.findByUserUsername(login, projection);
    }

    public DoctorIdInfo getDoctorIdByLogin(String login) {
        return doctorRepository
                .findByUserUsername(login, DoctorIdInfo.class)
                .orElseThrow(EntityNotFoundException::new);
    }

}
