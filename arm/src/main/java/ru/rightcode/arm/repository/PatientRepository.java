package ru.rightcode.arm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.model.Patient;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>, JpaSpecificationExecutor<Patient>, PagingAndSortingRepository<Patient, Long> {

    @EntityGraph(attributePaths = {"patientStatus", "passport"})
    @NonNull
    @Override
    Page<Patient> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"patientStatus", "passport"})
    @NonNull
    @Override
    Page<Patient> findAll(@Nullable Specification<Patient> specification, Pageable pageable);

    @EntityGraph(attributePaths = {"patientStatus", "passport"})
    Optional<Patient> findByPatientCode(@Param("patientCode") Long patientCode);
}
