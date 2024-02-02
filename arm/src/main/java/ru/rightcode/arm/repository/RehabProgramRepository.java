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

import java.util.Optional;

@Repository
public interface RehabProgramRepository extends JpaRepository<RehabProgram, Long> {

    @Override
    @EntityGraph(attributePaths = {RehabProgram_.MODULES, RehabProgram_.START_FORM, RehabProgram_.END_FORM})
    @NonNull
    Optional<RehabProgram> findById(@NonNull Long Long);

    @EntityGraph(attributePaths = {RehabProgram_.MODULES, RehabProgram_.START_FORM, RehabProgram_.END_FORM})
    Optional<RehabProgram> findByDoctorIdAndPatientIdAndIsCurrentTrue(Long doctorId, Long patientId);

    @Query("select exists(select 1 from RehabProgram rp where rp.doctor.id = :doctorId and rp.patient.id = :patientId and rp.isCurrent = true)")
    boolean checkIfExists(
            @Param("doctorId") Long doctorId,
            @Param("patientId") Long patientId
    );

    @Modifying
    @Query("update RehabProgram rp set rp.startForm.id = :formId where rp.id = :programId and rp.doctor.id = :doctorId")
    int addStartForm(@Param("formId") Long formId, @Param("programId") Long programId, @Param("doctorId") Long doctorId);

    @Modifying
    @Query("update RehabProgram rp set rp.endForm.id = :formId where rp.id = :programId and rp.doctor.id = :doctorId")
    int addEndForm(@Param("formId") Long formId, @Param("programId") Long programId, @Param("doctorId") Long doctorId);
}
