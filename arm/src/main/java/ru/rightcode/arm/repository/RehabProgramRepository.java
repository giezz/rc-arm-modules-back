package ru.rightcode.arm.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.model.RehabProgram;
import ru.rightcode.arm.model.RehabProgram_;

import java.util.List;
import java.util.Optional;

@Repository
public interface RehabProgramRepository extends JpaRepository<RehabProgram, Long> {

    @Override
    @EntityGraph(attributePaths = {RehabProgram_.MODULES, RehabProgram_.START_FORM, RehabProgram_.END_FORM})
    @NonNull
    Optional<RehabProgram> findById(@NonNull Long Long);

    @EntityGraph(attributePaths = {RehabProgram_.MODULES, RehabProgram_.START_FORM, RehabProgram_.END_FORM})
    Optional<RehabProgram> findByDoctorIdAndPatientIdAndIsCurrentTrue(Long doctorId, Long patientId);

    @EntityGraph(attributePaths = {RehabProgram_.MODULES, RehabProgram_.START_FORM, RehabProgram_.END_FORM})
    Optional<RehabProgram> findByPatientIdAndIsCurrentTrue(Long patientId);

    @EntityGraph(attributePaths = {RehabProgram_.MODULES, RehabProgram_.START_FORM, RehabProgram_.END_FORM})
    List<RehabProgram> findAllByPatientId(Long patientId);

    @Query("select exists(select 1 from RehabProgram rp where rp.doctor.id = :doctorId and rp.patient.id = :patientId and rp.isCurrent = true)")
    boolean checkIfCurrentExists(
            @Param("doctorId") Long doctorId,
            @Param("patientId") Long patientId
    );

    @Query("select exists(select 1 from RehabProgram rp where rp.doctor.id = :doctorId and rp.id = :programId)")
    boolean checkIfDoctorCanEdit(@Param("doctorId") Long doctorId, @Param("programId") Long programId);

    @Modifying
    @Query("update RehabProgram rp set rp.startForm.id = :formId where rp.id = :programId")
    void addStartForm(@Param("formId") Long formId, @Param("programId") Long programId);

    @Modifying
    @Query("update RehabProgram rp set rp.endForm.id = :formId where rp.id = :programId")
    void addEndForm(@Param("formId") Long formId, @Param("programId") Long programId);
}
