package ru.rightcode.patient.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import ru.rightcode.patient.model.Form;

import java.util.Optional;

public interface FormRepository extends JpaRepository<Form, Long> {
    @Override
    @EntityGraph(attributePaths = {"scale", "formQuestions.question.variants"})
    @NonNull
    Optional<Form> findById(@NonNull Long id);
}