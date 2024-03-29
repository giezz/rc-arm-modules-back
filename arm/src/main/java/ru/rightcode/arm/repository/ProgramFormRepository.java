package ru.rightcode.arm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.model.ProgramForm;

import java.util.List;

@Repository
public interface ProgramFormRepository extends JpaRepository<ProgramForm, Long> {

    @Query("select pf from ProgramForm pf " +
            "join fetch pf.rehabProgram rp " +
            "join fetch pf.form f " +
            "join fetch f.scale s " +
            "join fetch s.interpretations i " +
            "where rp.id = :id and pf.score is not null " +
            "and pf.score between i.minValue and i.maxValue " +
            "and pf.id not in (:ids)")
    List<ProgramForm> findFormResultsByRehabProgramId(
            @Param("id") Long id,
            @Param("ids") List<Long> excludeIds
    );
}