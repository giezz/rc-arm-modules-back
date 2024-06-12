package ru.rightcode.anketi.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.rightcode.anketi.model.Interpretation;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterpretationRepository extends JpaRepository<Interpretation, Long> {

    @Override
    @EntityGraph(attributePaths = {"scale"})
    @NonNull
    Optional<Interpretation> findById(@NonNull Long id);

    @EntityGraph(attributePaths = {"scale"})
    @Query("SELECT i FROM Interpretation i join i.scale s where s.name like :name")
    @NonNull
    List<Interpretation> findAllByScaleName(@NonNull String name);

    @Override
    @EntityGraph(attributePaths = {"scale"})
    @NonNull
    List<Interpretation> findAll();

    @EntityGraph(attributePaths  = {"scale"})
    List<Interpretation> findAllByScaleId(@NonNull Long id);
}