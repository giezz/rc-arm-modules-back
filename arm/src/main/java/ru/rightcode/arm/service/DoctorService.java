package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rightcode.arm.dto.DoctorIdInfo;
import ru.rightcode.arm.model.Doctor;
import ru.rightcode.arm.model.Patient;
import ru.rightcode.arm.repository.DoctorRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public <T> Optional<T> getByLogin(String login, Class<T> projection) {
        return doctorRepository.findByUserUsername(login, projection);
    }

    public List<Patient> getPatients(Doctor doctor) {
        return doctor.getPatients();
    }

    public DoctorIdInfo getDoctorIdByLogin(String login) {
        return doctorRepository
                .findByUserUsername(login, DoctorIdInfo.class)
                .orElseThrow(EntityNotFoundException::new);
    }

}
