package ru.rightcode.arm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rightcode.arm.model.ModuleExercise;

public interface ModuleExerciseRepository extends JpaRepository<ModuleExercise, Long> {
}