package ru.rightcode.patient.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.patient.model.Patient;
import ru.rightcode.patient.model.RehabProgram;

import java.util.Optional;

@Repository
public interface RehabProgramRepository extends JpaRepository<RehabProgram, Long> {
//    cannot simultaneously fetch multiple bags hibernate
    @EntityGraph(attributePaths = {"modules", "forms.form.scale"})
    RehabProgram findByPatient(@NotNull Patient patient);

    @EntityGraph(attributePaths = {"doctor"})
    Optional<RehabProgram> findDoctorByPatient(@NotNull Patient patient);
}