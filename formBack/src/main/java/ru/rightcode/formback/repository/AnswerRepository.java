package ru.rightcode.formback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.formback.model.Answer;
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}