package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.DoctorIdInfo;
import ru.rightcode.arm.dto.request.PatientRequest;
import ru.rightcode.arm.model.Doctor;
import ru.rightcode.arm.model.Patient;
import ru.rightcode.arm.repository.DoctorRepository;
import ru.rightcode.arm.repository.PatientRepository;
import ru.rightcode.arm.repository.specification.PatientSpecification;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PatientService {

    private final PatientRepository patientRepository;

    private final DoctorRepository doctorRepository;

    public List<Patient> getAll(PatientRequest patientRequest) {
        Optional<Specification<Patient>> spec = specificationBuilder(patientRequest);
        return spec.map(patientRepository::findAll)
                .orElseGet(patientRepository::findAll);
    }

    @Transactional
    public void addDoctor(Long patientId, String doctorLogin) {
        DoctorIdInfo doctor = doctorRepository
                .findByUserUsername(doctorLogin, DoctorIdInfo.class)
                .orElseThrow(EntityNotFoundException::new);
        patientRepository.addDoctor(doctor.getId(), patientId);
    }

    @Transactional
    public void removeDoctor(Long patientId) {
        patientRepository.removeDoctor(patientId);
    }

    public Patient getById(Long id) {
        return patientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Patient getByCode(Long code) {
        return patientRepository.findByPatientCode(code)
                .orElseThrow(
                        () -> new EntityNotFoundException(code.toString())
                );
    }

    private Optional<Specification<Patient>> specificationBuilder(PatientRequest patientRequest) {
        List<Specification<Patient>> specificationList = Stream.of(
                        PatientSpecification.firstNameLike(patientRequest.firstName()),
                        PatientSpecification.middleNameLike(patientRequest.middleName()),
                        PatientSpecification.lastNameLike(patientRequest.lastName()),
                        PatientSpecification.hasPatientStatus(patientRequest.status()),
                        PatientSpecification.hasBirthDate(patientRequest.birthDate())
                )
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (patientRequest.isDead() != null && patientRequest.isDead()) {
            specificationList.add(PatientSpecification.isDead());
        }

        if (specificationList.isEmpty()) {
            return Optional.empty();
        }

        Specification<Patient> specification = Specification.where(specificationList.get(0));

        for (int i = 1; i < specificationList.size(); i++) {
            Specification<Patient> patientSpecification = specificationList.get(i);
            specification = specification.and(patientSpecification);
        }

        return Optional.of(specification);
    }
}
