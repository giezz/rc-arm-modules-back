package ru.rightcode.arm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.model.Module;

import java.util.Optional;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

    @Query("select m from Module m " +
            "left join fetch m.exercises me " +
            "left join fetch me.exercise e " +
            "left join fetch e.exerciseType et " +
            "left join fetch me.block b " +
            "where m.id = :id")
    Optional<Module> findByIdWithExercises(@Param("id") Long id);

    @Query("select m from Module m " +
            "left join fetch m.forms mf " +
            "left join fetch mf.form f " +
            "left join fetch f.scale " +
            "where m.id = :id")
    Optional<Module> findByIdWithForms(@Param("id") Long id);
}
