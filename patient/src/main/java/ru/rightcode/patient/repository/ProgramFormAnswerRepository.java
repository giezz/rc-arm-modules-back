package ru.rightcode.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rightcode.patient.model.ProgramFormAnswer;

public interface ProgramFormAnswerRepository extends JpaRepository<ProgramFormAnswer, Long> {
}