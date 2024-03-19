package ru.rightcode.arm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.model.ModuleForm;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModuleFormRepository extends JpaRepository<ModuleForm, Long> {

    @Query("select mf from ModuleForm mf " +
            "join fetch mf.module m " +
            "join fetch mf.form " +
            "where m.rehabProgram.id = :id and mf.score is not null")
    List<ModuleForm> findFormResultsByRehabProgramId(@Param("id") Long id);

    @Query("select mf from ModuleForm mf " +
            "join fetch mf.form f " +
            "join fetch mf.moduleFormAnswers mfa " +
            "join fetch mfa.variant v " +
            "join fetch v.question q " +
            "where mf.id = :id")
    Optional<ModuleForm> findFormResultsWithAnswers(@Param("id") Long id);
}