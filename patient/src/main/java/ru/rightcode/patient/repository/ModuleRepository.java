package ru.rightcode.patient.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.patient.model.Module;

import java.util.Optional;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

    @EntityGraph(attributePaths = {"moduleForms.form.scale", "moduleForms.form.formQuestions.question.variants"})
    Optional<Module> getModuleByIdAndModuleFormsId(@NonNull Long id, @NonNull Long moduleFormsId);
}