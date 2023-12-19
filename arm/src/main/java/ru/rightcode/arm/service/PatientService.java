package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.rightcode.arm.dto.request.PatientRequest;
import ru.rightcode.arm.dto.response.SimplePatientResponse;
import ru.rightcode.arm.model.Doctor;
import ru.rightcode.arm.model.Patient;
import ru.rightcode.arm.repository.PatientRepository;
import ru.rightcode.arm.repository.specification.PatientSpecification;
import ru.rightcode.arm.utils.ResponseMappers;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    private final DoctorService doctorService;

    public List<SimplePatientResponse> getAll(PatientRequest patientRequest) {
        Optional<Specification<Patient>> spec = specificationBuilder(patientRequest);
        return spec.map(patientRepository::findAll)
                .orElseGet(patientRepository::findAll)
                .stream()
                .map(ResponseMappers::mapToSimplePatientResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void addDoctor(Long patientId, String doctorLogin) {
        Doctor doctor = doctorService.getByLogin(doctorLogin);
        patientRepository.addDoctor(doctor, patientId);
    }

    @Transactional
    public void removeDoctor(Long patientId) {
        patientRepository.removeDoctor(patientId);
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

    private Optional<Specification<Patient>> specificationBuilder(PatientRequest patientRequest) {
        List<Specification<Patient>> specificationList = Stream.of(
                        PatientSpecification.firstNameLike(patientRequest.getFirstName()),
                        PatientSpecification.middleNameLike(patientRequest.getMiddleName()),
                        PatientSpecification.lastNameLike(patientRequest.getLastName()),
                        PatientSpecification.hasPatientStatus(patientRequest.getStatus()),
                        PatientSpecification.hasBirthDate(patientRequest.getBirthDate())
                )
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (patientRequest.getIsDead() != null && patientRequest.getIsDead()) {
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
