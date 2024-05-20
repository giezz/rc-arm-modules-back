package ru.rightcode.arm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.dto.projection.RehabProgramInfo;
import ru.rightcode.arm.model.RehabProgram;

import java.util.List;
import java.util.Optional;

@Repository
public interface RehabProgramRepository extends JpaRepository<RehabProgram, Long>, JpaSpecificationExecutor<RehabProgram> {

    @Override
    @NonNull
    Page<RehabProgram> findAll(@NonNull Specification<RehabProgram> specification, @NonNull Pageable pageable);

    List<RehabProgramInfo> findAllByPatientId(Long patientId);

    @Query("select rp from RehabProgram rp " +
            "join fetch rp.doctor " +
            "join fetch rp.patient p " +
            "join fetch p.patientStatus " +
            "left join fetch rp.forms pf " +
            "left join fetch pf.form f " +
            "left join fetch f.scale s " +
            "where rp.id = :id")
    Optional<RehabProgram> findByIdWithProgramForms(@Param("id") Long id);

    @Query("select rp from RehabProgram rp " +
            "left join fetch rp.modules " +
            "where rp.id = :id")
    Optional<RehabProgram> findByIdWithModules(@Param("id") Long id);

    @Query("select rp from RehabProgram rp " +
            "join fetch rp.doctor " +
            "join fetch rp.patient p " +
            "join fetch p.patientStatus " +
            "left join fetch rp.forms pf " +
            "left join fetch pf.form f " +
            "left join fetch f.scale s " +
            "where rp.isCurrent = true and rp.patient.id = :id")
    Optional<RehabProgram> findCurrentWithProgramForms(@Param("id") Long id);

    @Query("select rp from RehabProgram rp " +
            "left join fetch rp.modules " +
            "where rp.isCurrent = true and rp.patient.id = :id")
    Optional<RehabProgram> findCurrentWithModules(@Param("id") Long id);

    @Query("select rp from RehabProgram rp " +
            "join fetch rp.doctor " +
            "join fetch rp.patient p " +
            "join fetch p.patientStatus " +
            "left join fetch rp.forms pf " +
            "left join fetch pf.form f " +
            "left join fetch f.scale s " +
            "where rp.id = :programId and rp.patient.id = :patientId")
    Optional<RehabProgram> findByPatientIdWithProgramForms(
            @Param("programId") Long programId,
            @Param("patientId") Long patientId
    );

    @Query("select rp from RehabProgram rp " +
            "left join fetch rp.modules " +
            "where rp.id = :programId and rp.patient.id = :patientId")
    Optional<RehabProgram> findByPatientIdWithModules(
            @Param("programId") Long programId,
            @Param("patientId") Long patientId
    );

    @Query("select exists(select 1 from RehabProgram rp where rp.doctor.id = :doctorId and rp.patient.id = :patientId and rp.isCurrent = true)")
    boolean checkIfCurrentExists(
            @Param("doctorId") Long doctorId,
            @Param("patientId") Long patientId
    );

}
