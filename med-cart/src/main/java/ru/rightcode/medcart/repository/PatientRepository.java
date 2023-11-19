package ru.rightcode.medcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.medcart.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByPatientCode(Long code);
}
