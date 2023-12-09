package ru.rightcode.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.rightcode.core.model.Patient;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @NonNull
    @Override
    @Query("select p from Patient p join fetch p.patientStatus join fetch p.passport")
    List<Patient> findAll();

    @Query("select p from Patient p join fetch p.patientStatus join fetch p.passport where p.patientCode = :patientCode")
    Optional<Patient> findByPatientCode(@Param("patientCode") Long patientCode);

}
