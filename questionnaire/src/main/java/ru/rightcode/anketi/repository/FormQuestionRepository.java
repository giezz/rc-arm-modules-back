package ru.rightcode.anketi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.anketi.model.Form;
import ru.rightcode.anketi.model.FormQuestion;

import java.util.List;

@Repository
public interface FormQuestionRepository extends JpaRepository<FormQuestion, Long> {
    List<FormQuestion> findFormQuestionsByIdForm(Form idForm);
}