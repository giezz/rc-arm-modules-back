package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rightcode.arm.dto.response.SimplePatientResponse;
import ru.rightcode.arm.model.Doctor;
import ru.rightcode.arm.repository.DoctorRepository;
import ru.rightcode.arm.utils.ResponseMappers;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public Doctor getByLogin(String login) {
        return doctorRepository.findByLogin(login).orElseThrow(EntityNotFoundException::new);
    }

    public List<SimplePatientResponse> getPatients(Doctor doctor) {
        return doctor.getPatients().stream()
                .map(ResponseMappers::mapToSimplePatientResponse)
                .collect(Collectors.toList());
    }
}
