package ru.rightcode.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.patient.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}