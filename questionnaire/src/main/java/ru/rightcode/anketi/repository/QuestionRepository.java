package ru.rightcode.anketi.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.rightcode.anketi.model.Question;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Override
    @EntityGraph(attributePaths = {"variants", "formQuestions"})
    @NonNull
    Optional<Question> findById(Long id);

    @Override
    @EntityGraph(attributePaths = {"variants"})
    @NonNull
    List<Question> findAll();
}
