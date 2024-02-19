package ru.rightcode.arm.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.model.Patient;
import ru.rightcode.arm.model.Patient_;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>, JpaSpecificationExecutor<Patient> {

    @EntityGraph(attributePaths = {Patient_.PATIENT_STATUS})
    @NonNull
    @Override
    List<Patient> findAll();

    @EntityGraph(attributePaths = {Patient_.PATIENT_STATUS})
    @NonNull
    @Override
    List<Patient> findAll(@Nullable Specification<Patient> specification);

    @EntityGraph(attributePaths = {Patient_.PATIENT_STATUS, Patient_.PASSPORT})
    Optional<Patient> findByPatientCode(@Param("patientCode") Long patientCode);

    @Modifying
    @Query("update Patient p set p.doctor.id = :doctorId where p.id = :id")
    void addDoctor(@Param("doctorId") Long doctorId, @Param("id") Long id);

    @Modifying
    @Query("update Patient p set p.doctor = null where p.id = :id")
    void removeDoctor(@Param("id") Long id);

}
