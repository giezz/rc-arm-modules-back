package ru.rightcode.anketi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.rightcode.anketi.model.Question;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    /*@Query("select q from Question q join q.formQuestions as fq where fq.idForm.id = ?")
    List<Question> findAllByFormQuestions(Long idForm);*/

    Optional<Question> findById(Long id);

    @Query("SELECT q FROM Question q WHERE q.id IN :ids")
    List<Question> findQuestionsByIds(List<Long> ids);

    @Query("SELECT q FROM Question q WHERE q.id NOT IN :ids")
    List<Question> findQuestionsByNotIds(List<Long> ids);
}
