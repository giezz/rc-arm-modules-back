package ru.rightcode.arm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.model.Form;

import java.util.Optional;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {

    @Override
    @NonNull
    @EntityGraph(attributePaths = {"scale"})
    Optional<Form> findById(@NonNull Long id);

    @Override
    @NonNull
    @EntityGraph(attributePaths = {"scale"})
    Page<Form> findAll(@NonNull Pageable pageable);

    @EntityGraph(attributePaths = {"scale"})
    Page<Form> findAllByNameContainsIgnoreCase(String name, Pageable pageable);

    @Query("select f from Form f " +
            "join fetch f.formQuestions fq " +
            "join fetch fq.question " +
            "join fetch f.scale " +
            "where f.id = :id")
    Optional<Form> findWithQuestions(@Param("id") Long id);
}