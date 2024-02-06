package ru.rightcode.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rightcode.back.model.Doctor;
import ru.rightcode.back.model.Patient;
import ru.rightcode.back.model.RehabProgram;

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
    Optional<RehabProgram> getCurrentProgramByDoctorAndPatient(
            @Param("doctor") Doctor doctor,
            @Param("patient") Patient patient
    );
}
