package ru.rightcode.arm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.model.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    @NonNull
    @Override
    @EntityGraph(attributePaths = {"exerciseType"})
    Page<Exercise> findAll(@NonNull Pageable pageable);

    @EntityGraph(attributePaths = {"exerciseType"})
    Page<Exercise> findAllByNameContainsIgnoreCase(String name, Pageable pageable);
}
