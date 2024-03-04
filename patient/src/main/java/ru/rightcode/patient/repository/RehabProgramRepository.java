package ru.rightcode.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.patient.model.RehabProgram;

import java.util.Optional;

@Repository
public interface RehabProgramRepository extends JpaRepository<RehabProgram, Long> {
    Optional<RehabProgram> findByPatientIdAndIsCurrentTrue(Long patientId);
}