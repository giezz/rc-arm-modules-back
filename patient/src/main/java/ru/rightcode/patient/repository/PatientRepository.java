package ru.rightcode.patient.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rightcode.patient.model.Patient;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @EntityGraph(attributePaths = {"user"})
    <T> Optional<T> findByUserUsername(@Param("login") String login, Class<T> projection);

    @EntityGraph(attributePaths = {"patientStatus", "passport"})
    Optional<Patient> getPatientByUserUsername(@Param("login") String login);

    @Query("select p from Patient p " +
            "left join fetch p.rehabPrograms r " +
            "left join fetch r.protocols pr " +
            "where p.user.username = :login and r.isCurrent = false")
    @EntityGraph(attributePaths = {"rehabPrograms.protocols"})
    Optional<Patient> getPatientRehabProgramByUserUsername(@Param("login") String login);


    @EntityGraph(attributePaths = {"rehabPrograms.modules", "rehabPrograms.forms.form", "rehabPrograms.forms.type"})
    Optional<Patient> getPatientCurrentRehabProgramByUserUsername(@Param("login") String login);

    @EntityGraph(attributePaths = {"rehabPrograms.modules.exercises.exercise.exerciseType", "rehabPrograms.modules.exercises.block", "rehabPrograms.modules.forms.form"})
    Optional<Patient> getPatientCurrentModuleByUserUsername(@Param("login") String login);

    @Override
    @NonNull
    @EntityGraph(attributePaths = {"patientStatus", "passport"})
    Optional<Patient> findById(@NonNull Long id);

}
