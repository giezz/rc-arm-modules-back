package ru.rightcode.anketi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.anketi.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
