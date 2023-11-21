package ru.rightcode.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.rightcode.core.model.Patient;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @NonNull
    @Override
    @Query("select p from Patient p join fetch p.patientStatus join fetch p.passport")
    List<Patient> findAll();
}
