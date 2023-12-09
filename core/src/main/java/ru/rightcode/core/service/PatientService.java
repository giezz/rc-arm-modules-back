package ru.rightcode.core.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rightcode.core.dto.SimplePatientResponse;
import ru.rightcode.core.model.Patient;
import ru.rightcode.core.repository.PatientRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public List<SimplePatientResponse> getAll() {
        return patientRepository.findAll().stream()
                .map(this::mapToSimplePatientResponse)
                .collect(Collectors.toList());
    }

    public SimplePatientResponse getByCode(Long code) {
        return mapToSimplePatientResponse(
                patientRepository.findByPatientCode(code)
                        .orElseThrow(
                                () -> new EntityNotFoundException(code.toString())
                        )
        );
    }

    private SimplePatientResponse mapToSimplePatientResponse(Patient patient) {
        return SimplePatientResponse.builder()
                .id(patient.getId())
                .patientCode(patient.getPatientCode())
                .firstName(patient.getFirstName())
                .middleName(patient.getMiddleName())
                .lastName(patient.getLastName())
                .birthDate(patient.getBirthDate())
                .deathDate(patient.getDeathDate())
                .address(patient.getAddress())
                .phoneNumber(patient.getPhoneNumber())
                .workPlaceData(patient.getWorkPlaceData())
                .bookmark(patient.getBookmark())
                .snils(patient.getSnils())
                .polis(patient.getPolis())
                .patientStatus(patient.getPatientStatus())
                .passport(patient.getPassport())
                .build();
    }
}
