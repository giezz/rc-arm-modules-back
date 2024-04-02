package ru.rightcode.arm.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.rightcode.arm.dto.request.PatientRequest;
import ru.rightcode.arm.model.Patient;
import ru.rightcode.arm.model.PatientStatus_;
import ru.rightcode.arm.model.Patient_;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class PatientSpecification {

    public static Specification<Patient> isDead() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isNotNull(root.get(Patient_.DEATH_DATE));
    }

    public static Specification<Patient> firstNameLike(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.upper(root.get(Patient_.FIRST_NAME)),
                "%" + name.toUpperCase() + "%"
        );
    }

    public static Specification<Patient> middleNameLike(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.upper(root.get(Patient_.MIDDLE_NAME)),
                "%" + name.toUpperCase() + "%"
        );
    }

    public static Specification<Patient> lastNameLike(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.upper(root.get(Patient_.LAST_NAME)),
                "%" + name.toUpperCase() + "%"
        );
    }

    public static Specification<Patient> hasPatientStatus(String status) {
        if (status == null) {
            return null;
        }
        // FIXME add join
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Patient_.PATIENT_STATUS)
                .get(PatientStatus_.ID), status);
    }

    public static Specification<Patient> hasBirthDate(LocalDate birthDate) {
        if (birthDate == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Patient_.BIRTH_DATE), birthDate);
    }

    public static Optional<Specification<Patient>> specificationBuilder(PatientRequest request) {
        List<Specification<Patient>> specificationList = new ArrayList<>(
                Stream.of(
                                firstNameLike(request.firstName()),
                                middleNameLike(request.middleName()),
                                lastNameLike(request.lastName()),
                                hasPatientStatus(request.status()),
                                hasBirthDate(request.birthDate())
                        )
                        .filter(Objects::nonNull)
                        .toList()
        );
        if (request.isDead() != null && request.isDead()) {
            specificationList.add(isDead());
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
