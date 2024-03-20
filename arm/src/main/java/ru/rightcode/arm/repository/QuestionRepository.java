package ru.rightcode.arm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.model.Question;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("select q from Question q " +
            "join fetch q.variants " +
            "join q.formQuestions fq " +
            "join fq.form f " +
            "where f.id = :id")
    List<Question> findQuestionsByFormId(@Param("id") Long formId);
}