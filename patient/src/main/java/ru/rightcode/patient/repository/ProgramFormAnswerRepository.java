package ru.rightcode.patient.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rightcode.patient.model.ProgramFormAnswer;

import java.util.List;

public interface ProgramFormAnswerRepository extends JpaRepository<ProgramFormAnswer, Long> {
    @EntityGraph(attributePaths = {"variant", "programForm"})
    List<ProgramFormAnswer> findAllByProgramFormId(Long programId);
}