package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rightcode.arm.dto.response.SimplePatientResponse;
import ru.rightcode.arm.model.Doctor;
import ru.rightcode.arm.model.Patient;
import ru.rightcode.arm.repository.DoctorRepository;
import ru.rightcode.arm.repository.PatientRepository;
import ru.rightcode.arm.utils.ResponseMappers;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    private final DoctorRepository doctorRepository;

    public List<SimplePatientResponse> getAll() {
        return patientRepository.findAll().stream()
                .map(ResponseMappers::mapToSimplePatientResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void addDoctor(Long patientId, Doctor doctor) {
        Doctor doctorFromDb = doctorRepository.getReferenceById(doctor.getId());
        patientRepository.addDoctor(doctorFromDb, patientId);
    }

    public Patient getById(Long id) {
        return patientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public SimplePatientResponse getByCode(Long code) {
        return ResponseMappers.mapToSimplePatientResponse(
                patientRepository.findByPatientCode(code)
                        .orElseThrow(
                                () -> new EntityNotFoundException(code.toString())
                        )
        );
    }

}
