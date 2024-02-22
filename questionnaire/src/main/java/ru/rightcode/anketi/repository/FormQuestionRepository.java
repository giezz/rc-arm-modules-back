package ru.rightcode.anketi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rightcode.anketi.model.Form;
import ru.rightcode.anketi.model.FormQuestion;
import ru.rightcode.anketi.model.Question;

import java.util.List;

@Repository
public interface FormQuestionRepository extends JpaRepository<FormQuestion, Long> {
    List<FormQuestion> findFormQuestionsByIdForm(Form idForm);

    List<FormQuestion> findByIdForm(Form idForm);

    @Query("SELECT fq.idQuestion FROM FormQuestion fq WHERE fq.idForm = :form")
    List<Question> findQuestionsByForm(@Param("form") Form form);


}