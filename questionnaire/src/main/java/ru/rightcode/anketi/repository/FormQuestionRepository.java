package ru.rightcode.anketi.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rightcode.anketi.model.Form;
import ru.rightcode.anketi.model.FormQuestion;
import ru.rightcode.anketi.model.Question;

import java.util.List;

@Repository
public interface FormQuestionRepository extends JpaRepository<FormQuestion, Long> {
    List<FormQuestion> findFormQuestionsByForm(Form idForm);

    @EntityGraph(attributePaths = {"question.variants", "form.scale"})
    List<FormQuestion> findByForm(Form idForm);

    @Query("SELECT fq.question FROM FormQuestion fq WHERE fq.form = :form")
    List<Question> findQuestionsByForm(@Param("form") Form form);

//    deleteByFormAndQuestion
    @Modifying
    @Query("DELETE FROM FormQuestion fq WHERE fq.form.id = ?1 and fq.question.id = ?2")
    void deleteByQuestionId(Long formId, Long questionId);


}