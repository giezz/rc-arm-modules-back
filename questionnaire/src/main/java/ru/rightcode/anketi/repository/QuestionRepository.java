package ru.rightcode.anketi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.anketi.model.Question;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    /*@Query("select q from Question q join q.formQuestions as fq where fq.idForm.id = ?")
    List<Question> findAllByFormQuestions(Long idForm);*/

    Optional<Question> findById(Long id);
}
