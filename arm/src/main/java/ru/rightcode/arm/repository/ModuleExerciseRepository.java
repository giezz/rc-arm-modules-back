package ru.rightcode.arm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.model.ModuleExercise;
import ru.rightcode.arm.model.ModuleForm;

import java.util.List;

@Repository
public interface ModuleExerciseRepository extends JpaRepository<ModuleExercise, Long> {
}