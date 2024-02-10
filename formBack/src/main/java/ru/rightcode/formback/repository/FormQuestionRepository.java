package ru.rightcode.formback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.formback.model.FormQuestion;
@Repository
public interface FormQuestionRepository extends JpaRepository<FormQuestion, Long> {
}