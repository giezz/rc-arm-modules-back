package ru.rightcode.arm.repository.specification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import ru.rightcode.arm.dto.request.RehabProgramRequest;
import ru.rightcode.arm.model.Doctor_;
import ru.rightcode.arm.model.Patient_;
import ru.rightcode.arm.model.RehabProgram;
import ru.rightcode.arm.model.RehabProgram_;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RehabProgramSpecification {

    public static Specification<RehabProgram> hasDoctorIdEqual(Long id) {
        if (id == null) {
            return null;
        }

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(RehabProgram_.DOCTOR).get(Doctor_.ID), id
        );
    }

    public static Specification<RehabProgram> params(RehabProgramRequest request) {
        String firstName = request.patientFirstName();
        String middleName = request.patientMiddleName();
        String lastName = request.patientLastName();
        LocalDate startDate = request.startDate();
        LocalDate endDate = request.endDate();

        return (root, query, criteriaBuilder) -> {
            root.fetch(RehabProgram_.PATIENT).fetch(Patient_.PATIENT_STATUS);
            List<Predicate> predicates = new ArrayList<>();
            if (firstName != null) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.upper(root.get(RehabProgram_.PATIENT).get(Patient_.FIRST_NAME)),
                        "%" + firstName.toUpperCase() + "%"
                ));
            }
            if (middleName != null) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.upper(root.get(RehabProgram_.PATIENT).get(Patient_.MIDDLE_NAME)),
                        "%" + middleName.toUpperCase() + "%"
                ));
            }
            if (lastName != null) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.upper(root.get(RehabProgram_.PATIENT).get(Patient_.LAST_NAME)),
                        "%" + lastName.toUpperCase() + "%"
                ));
            }
            if (startDate != null && endDate != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(RehabProgram_.START_DATE), startDate));
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(RehabProgram_.END_DATE), endDate));
            }

            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }

}
