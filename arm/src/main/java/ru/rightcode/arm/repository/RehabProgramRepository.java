package ru.rightcode.arm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.model.Doctor;
import ru.rightcode.arm.model.Patient;
import ru.rightcode.arm.model.RehabProgram;

import java.util.List;
import java.util.Optional;

@Repository
public interface RehabProgramRepository extends JpaRepository<RehabProgram, Long> {

    @Query("select rp from RehabProgram rp where rp.doctor = :doctor and rp.patient = :patient and rp.isCurrent = false")
    List<RehabProgram> getAllProgramByDoctorAndPatient(
            @Param("doctor") Doctor doctor,
            @Param("patient") Patient patient
    );

    @Query("select rp from RehabProgram rp where rp.doctor = :doctor and rp.patient = :patient and rp.isCurrent = true")
    Optional<RehabProgram> getProgramByDoctorAndPatient(
            @Param("doctor") Doctor doctor,
            @Param("patient") Patient patient
    );
}
