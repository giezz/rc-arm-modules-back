package ru.rightcode.anketi.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.rightcode.anketi.model.Interpretation;
import ru.rightcode.anketi.model.Scale;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScaleRepository extends JpaRepository<Scale, Long> {
    @EntityGraph(attributePaths = {"interpretations"})
    @Query("SELECT s FROM Scale s where s.name like :name")
    @NonNull
    List<Scale> findAllByName(@NonNull String name);

    @EntityGraph(attributePaths  = {"interpretations"})
    List<Scale> findAll();

    @Override
    @EntityGraph(attributePaths  = {"interpretations"})
    Optional<Scale> findById(@NonNull Long id);
}
