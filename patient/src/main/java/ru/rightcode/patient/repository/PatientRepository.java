package ru.rightcode.patient.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rightcode.patient.model.Patient;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // auth
    // attributePaths = {"user", "status"}
    // Could not write JSON: could not initialize proxy [ru.rightcode.patient.model.Passport#3] - no Session]
    @EntityGraph(attributePaths = {"user"})
    <T> Optional<T> findByUserUsername(@Param("login") String login, Class<T> projection);

    @EntityGraph(attributePaths = {"user", "patientStatus", "passport"})
    Optional<Patient> getPatientByUserUsername(@Param("login") String login);

    @Override
    @NonNull
    @EntityGraph(attributePaths = {"patientStatus", "passport"})
    Optional<Patient> findById(@NonNull Long id);

}
