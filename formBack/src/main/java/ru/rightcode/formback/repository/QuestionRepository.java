package ru.rightcode.formback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.formback.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}