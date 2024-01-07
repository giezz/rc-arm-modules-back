package ru.rightcode.arm.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.rightcode.arm.model.Patient;
import ru.rightcode.arm.model.PatientStatus_;
import ru.rightcode.arm.model.Patient_;

import java.time.LocalDate;

public class PatientSpecification {

    public static Specification<Patient> isDead() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isNotNull(root.get(Patient_.DEATH_DATE));
    }
    public static Specification<Patient> firstNameLike(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                root.get(Patient_.FIRST_NAME),
                "%" + name + "%"
        );
    }

    public static Specification<Patient> middleNameLike(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                root.get(Patient_.MIDDLE_NAME),
                "%" + name + "%"
        );
    }

    public static Specification<Patient> lastNameLike(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                root.get(Patient_.LAST_NAME),
                "%" + name + "%"
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
}
