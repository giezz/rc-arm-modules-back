package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rightcode.arm.dto.SimpleDoctorResponse;
import ru.rightcode.arm.model.Doctor;
import ru.rightcode.arm.repository.DoctorRepository;
import ru.rightcode.arm.model.DoctorPatient;
import ru.rightcode.arm.repository.DoctorPatientRepository;

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
