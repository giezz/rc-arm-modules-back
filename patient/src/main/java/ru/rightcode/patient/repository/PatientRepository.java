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


    @EntityGraph(attributePaths = {"rehabPrograms.modules.moduleExercises.exercise.exerciseType",
            "rehabPrograms.modules.moduleExercises.block",
            "rehabPrograms.forms.form",
            "rehabPrograms.forms.type"})
    Optional<Patient> getPatientCurrentRehabProgramByUserUsername(@Param("login") String login);

    @EntityGraph(attributePaths = {"rehabPrograms.modules.moduleExercises.exercise.exerciseType", "rehabPrograms.modules.moduleExercises.block", "rehabPrograms.modules.moduleForms.form", "rehabPrograms.forms"})
    Optional<Patient> getPatientCurrentModuleByUserUsername(
            @Param("login") String login);

    @EntityGraph(attributePaths = {"rehabPrograms.forms.form", "rehabPrograms.forms.type"})
    Optional<Patient> getPatientCurrentRehabProgramProgramFormByUserUsername(
            @Param("login") String login);
//    @Query("select p from Patient p " +
//            "left join fetch p.rehabPrograms r " +
//            "left join fetch r.modules m " +
//            "left join fetch m.moduleForms mf " +
//            "left join fetch mf.form f " +
//            "left join fetch f.scale s " +
//            "left join fetch f.formQuestions fq " +
//            "left join fetch fq.question q " +
//            "left join fetch q.variants v " +
//            "where p.user.username = :login and r.isCurrent = true and m.id = :moduleId and mf.id = :formId")
//    @EntityGraph(attributePaths = {"rehabPrograms.modules.forms.module.forms.form.scale", "rehabPrograms.modules.forms.module.forms.form.formQuestions.question.variants"})
//    Optional<Patient> getPatientCurrentModuleFormQuestionByUserUsername(
//            @Param("login") String login, @Param("moduleId") Long moduleId, @Param("formId") Long formId);


    @Override
    @NonNull
    @EntityGraph(attributePaths = {"patientStatus", "passport"})
    Optional<Patient> findById(@NonNull Long id);

}
