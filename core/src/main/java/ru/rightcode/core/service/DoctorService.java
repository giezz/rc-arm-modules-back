package ru.rightcode.core.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rightcode.core.dto.SimpleDoctorResponse;
import ru.rightcode.core.model.Doctor;
import ru.rightcode.core.model.DoctorPatient;
import ru.rightcode.core.repository.DoctorPatientRepository;
import ru.rightcode.core.repository.DoctorRepository;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorPatientRepository doctorPatientRepository;

    public Doctor getByLogin(String login) {
        return doctorRepository.findByLogin(login).orElseThrow(EntityNotFoundException::new);
    }

    public void addPatient(Long doctorId, Long patientId) {
        DoctorPatient doctorPatient = new DoctorPatient(doctorId, patientId);
        doctorPatientRepository.save(doctorPatient);
    }

    private SimpleDoctorResponse mapToSimpleDoctorResponse(Doctor doctor) {
        return SimpleDoctorResponse.builder()
                .id(doctor.getId())
                .doctorCode(doctor.getDoctorCode())
                .firstName(doctor.getFirstName())
                .middleName(doctor.getMiddleName())
                .lastName(doctor.getLastName())
                .email(doctor.getEmail())

                .build();
    }
}
