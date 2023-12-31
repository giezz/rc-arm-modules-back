package ru.rightcode.arm.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.model.Doctor;
import ru.rightcode.arm.model.Patient;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>, JpaSpecificationExecutor<Patient> {

    @NonNull
    @Override
    @Query("select p from Patient p join fetch p.patientStatus join fetch p.passport left join fetch p.doctor")
    List<Patient> findAll();

    @EntityGraph(attributePaths = {"patientStatus", "doctor", "passport"})
    @NonNull
    @Override
    List<Patient> findAll(@Nullable Specification<Patient> specification);

    @Query("select p from Patient p join fetch p.patientStatus join fetch p.passport where p.patientCode = :patientCode")
    Optional<Patient> findByPatientCode(@Param("patientCode") Long patientCode);

    @Modifying
    @Query("update Patient p set p.doctor = :doctor where p.id = :id")
    void addDoctor(@Param("doctor") Doctor doctor, @Param("id") Long id);

    @Modifying
    @Query("update Patient p set p.doctor = null where p.id = :id")
    void removeDoctor(@Param("id") Long id);

}
