package ru.rightcode.arm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.model.Exercise;
import ru.rightcode.arm.model.Exercise_;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    @NonNull
    @Override
    @EntityGraph(attributePaths = {Exercise_.EXERCISE_TYPE})
    Page<Exercise> findAll(@NonNull Pageable pageable);

    @EntityGraph(attributePaths = {Exercise_.EXERCISE_TYPE})
    Page<Exercise> findAllByNameContainsIgnoreCase(String name, Pageable pageable);
}
