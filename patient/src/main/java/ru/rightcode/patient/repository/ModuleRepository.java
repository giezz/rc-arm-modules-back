package ru.rightcode.patient.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.patient.model.Module;

import java.util.Optional;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    @Override
    @NonNull
    @EntityGraph(attributePaths = {"exercises.exercise.exerciseType", "exercises.block", "forms.form.programForms.type"})
    Optional<Module> findById(@NonNull Long id);
}