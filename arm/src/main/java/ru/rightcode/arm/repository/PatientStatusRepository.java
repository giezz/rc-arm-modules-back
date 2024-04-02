package ru.rightcode.arm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.model.PatientStatus;

import java.util.Optional;

@Repository
public interface PatientStatusRepository extends JpaRepository<PatientStatus, Long> {
    Optional<PatientStatus> findByName(String name);
}