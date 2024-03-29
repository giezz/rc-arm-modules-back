package ru.rightcode.anketi.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.rightcode.anketi.model.FormQuestion;

import java.util.List;

@Repository
public interface FormQuestionRepository extends JpaRepository<FormQuestion, Long> {
    @EntityGraph(attributePaths = {"question.variants", "form.scale"})
    List<FormQuestion> findByFormId(Long idForm);

    @Modifying
    @Query("DELETE FROM FormQuestion fq WHERE fq.form.id = ?1 and fq.question.id = ?2")
    void deleteByQuestionFormId(Long formId, Long questionId);

    @Modifying
    @Query("DELETE FROM FormQuestion fq WHERE fq.question.id =?1")
    void deleteByQuestionId(Long questionId);

    @Modifying
    @Query("DELETE FROM FormQuestion fq WHERE fq.form.id =?1")
    void deleteByFormId(Long formId);
}