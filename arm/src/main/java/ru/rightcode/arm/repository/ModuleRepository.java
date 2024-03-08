package ru.rightcode.arm.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.model.Module;
import ru.rightcode.arm.model.Module_;

import java.util.Optional;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

    @Override
    @EntityGraph(attributePaths = {"exercises.exercise.exerciseType", "exercises.block", "forms.form", "forms.block"})
    @NonNull
    Optional<Module> findById(@NonNull @Param("id") Long id);
}
