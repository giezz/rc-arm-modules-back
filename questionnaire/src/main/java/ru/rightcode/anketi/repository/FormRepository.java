package ru.rightcode.anketi.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.rightcode.anketi.model.Form;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {
    @Override
    @EntityGraph(attributePaths = {"formQuestions.question.variants"})
    @NonNull
    Optional<Form> findById(@NonNull Long id);

    @Override
    @EntityGraph(attributePaths = {"scale", "formQuestions.question.variants"})
    @NonNull
    List<Form> findAll ();

    List<Form> findAllByName(String name);

}
