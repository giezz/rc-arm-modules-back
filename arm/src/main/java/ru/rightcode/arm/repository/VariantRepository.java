package ru.rightcode.arm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.model.Variant;

import java.util.List;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {

    @Query("select v from Variant v " +
            "join v.moduleFormAnswers mfa " +
            "where mfa.moduleForm.id = :id")
    List<Variant> findAnsweredVariantsByModuleFormId(@Param("id") Long moduleFormId);

    @Query("select v from Variant v " +
            "join v.programFormAnswers pfa " +
            "where pfa.programForm.id = :id")
    List<Variant> findAnsweredVariantsByProgramFormId(@Param("id") Long programFormId);
}