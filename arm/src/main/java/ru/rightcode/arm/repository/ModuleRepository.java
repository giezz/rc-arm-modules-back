package ru.rightcode.arm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.model.Module;

import java.util.Optional;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

    @Query("select m from Module m left join fetch m.exercises me join fetch me.exercise join fetch me.block where m.id = :id")
    Optional<Module> findByIdWithExercises(@Param("id") Long id);

    @Query("select m from Module m left join fetch m.forms f join fetch f.form where m.id = :id")
    Optional<Module> findByIdWithForms(@Param("id") Long id);
}
