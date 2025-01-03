package ru.rightcode.patient.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.patient.model.Patient;
import ru.rightcode.patient.model.RehabProgram;

import java.util.List;
import java.util.Optional;


@Repository
public interface RehabProgramRepository extends JpaRepository<RehabProgram, Long> {
//    cannot simultaneously fetch multiple bags hibernate
    @EntityGraph(attributePaths = {"doctor", "modules", "forms.form.scale"})
    Optional<RehabProgram> findByPatientId(@NotNull Long patientId);

    @EntityGraph(attributePaths = {"protocols"})
    List<RehabProgram> findAllByPatient(Patient patient);
}