package ru.rightcode.arm.repository.specification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import ru.rightcode.arm.dto.request.RehabProgramRequest;
import ru.rightcode.arm.model.RehabProgram;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// TODO: Spring Data JPA Specifications или pure Criteria API?
public class RehabProgramSpecification {

    public static Specification<RehabProgram> params(RehabProgramRequest request, Long doctorId) {
        String firstName = request.patientFirstName();
        String middleName = request.patientMiddleName();
        String lastName = request.patientLastName();
        LocalDate startDateFrom = request.startDateFrom();
        LocalDate startDateTo = request.startDateTo();
        Boolean isCurrent = request.isCurrent();

        return (root, query, cb) -> {
            root.fetch("patient").fetch("patientStatus");
            List<Predicate> predicates = new ArrayList<>();
            if (firstName != null) {
                predicates.add(cb.like(
                        cb.upper(root.get("patient").get("firstName")),
                        "%" + firstName.toUpperCase() + "%"
                ));
            }
            if (middleName != null) {
                predicates.add(cb.like(
                        cb.upper(root.get("patient").get("middleName")),
                        "%" + middleName.toUpperCase() + "%"
                ));
            }
            if (lastName != null) {
                predicates.add(cb.like(
                        cb.upper(root.get("patient").get("lastName")),
                        "%" + lastName.toUpperCase() + "%"
                ));
            }
            if (startDateFrom != null && startDateTo != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("startDate"), startDateFrom));
                predicates.add(cb.lessThanOrEqualTo(root.get("startDate"), startDateTo));
            }
            if (isCurrent != null) {
                predicates.add(isCurrent ?
                        cb.equal(root.get("isCurrent"), true) :
                        cb.equal(root.get("isCurrent"), false));
            }

            predicates.add(cb.equal(root.get("doctor").get("id"), doctorId));

            return cb.and(predicates.toArray(Predicate[]::new));
        };
    }

}
