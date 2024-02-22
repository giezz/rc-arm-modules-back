package ru.rightcode.arm.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.model.Exercise;
import ru.rightcode.arm.model.Exercise_;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    @EntityGraph(attributePaths = {Exercise_.EXERCISE_TYPE})
    @NonNull
    @Override
    List<Exercise> findAll();
}
