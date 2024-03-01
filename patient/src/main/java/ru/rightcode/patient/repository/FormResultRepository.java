package ru.rightcode.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rightcode.patient.model.FormResult;

public interface FormResultRepository extends JpaRepository<FormResult, Long> {
}