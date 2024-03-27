package ru.rightcode.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rightcode.patient.model.ProgramForm;

public interface ProgramFormRepository extends JpaRepository<ProgramForm, Long> {
}