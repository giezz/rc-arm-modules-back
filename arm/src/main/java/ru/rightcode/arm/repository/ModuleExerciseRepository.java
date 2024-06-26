package ru.rightcode.arm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.model.ModuleExercise;

@Repository
public interface ModuleExerciseRepository extends JpaRepository<ModuleExercise, Long> {
}