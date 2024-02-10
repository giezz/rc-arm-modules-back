package ru.rightcode.arm.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.model.Module;
import ru.rightcode.arm.model.Module_;

import java.util.Optional;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

    @Override
//    @EntityGraph(attributePaths = {Module_.FORMS, Module_.EXERCISES})
//    @Query("select m from Module m join fetch m.forms join fetch m.exercises where m.id = :id")
    @NonNull
    Optional<Module> findById(@NonNull @Param("id") Long id);
}
