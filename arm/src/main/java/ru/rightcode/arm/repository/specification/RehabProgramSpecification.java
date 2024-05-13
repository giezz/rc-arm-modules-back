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
        LocalDate startDateFrom = request.startDateFrom();
        LocalDate startDateTo = request.startDateTo();
        Boolean isCurrent = request.isCurrent();

        return (root, query, cb) -> {
            root.fetch(RehabProgram_.PATIENT).fetch(Patient_.PATIENT_STATUS);
            List<Predicate> predicates = new ArrayList<>();
            if (firstName != null) {
                predicates.add(cb.like(
                        cb.upper(root.get(RehabProgram_.PATIENT).get(Patient_.FIRST_NAME)),
                        "%" + firstName.toUpperCase() + "%"
                ));
            }
            if (middleName != null) {
                predicates.add(cb.like(
                        cb.upper(root.get(RehabProgram_.PATIENT).get(Patient_.MIDDLE_NAME)),
                        "%" + middleName.toUpperCase() + "%"
                ));
            }
            if (lastName != null) {
                predicates.add(cb.like(
                        cb.upper(root.get(RehabProgram_.PATIENT).get(Patient_.LAST_NAME)),
                        "%" + lastName.toUpperCase() + "%"
                ));
            }
            if (startDateFrom != null && startDateTo != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get(RehabProgram_.START_DATE), startDateFrom));
                predicates.add(cb.lessThanOrEqualTo(root.get(RehabProgram_.START_DATE), startDateTo));
            }
            if (isCurrent != null) {
                predicates.add(isCurrent ?
                        cb.equal(root.get(RehabProgram_.IS_CURRENT), true) :
                        cb.equal(root.get(RehabProgram_.IS_CURRENT), false));
            }

            return cb.and(predicates.toArray(Predicate[]::new));
        };
    }

}
