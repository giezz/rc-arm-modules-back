package ru.rightcode.arm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.model.ModuleForm;

import java.util.List;

@Repository
public interface ModuleFormRepository extends JpaRepository<ModuleForm, Long> {

    @Query("select mf from ModuleForm mf " +
            "join fetch mf.module m " +
            "join fetch mf.form f " +
            "left join fetch f.scale s " +
            "left join fetch s.interpretations i " +
            "where m.rehabProgram.id = :id and mf.score is not null " +
            "and mf.score between i.minValue and i.maxValue " +
            "and mf.id not in (:ids)")
    List<ModuleForm> findFormResultsByRehabProgramId(
            @Param("id") Long id,
            @Param("ids") List<Long> excludeIds
    );

}