package ru.rightcode.anketi.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.rightcode.anketi.model.Form;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {
    @Override
    @EntityGraph(attributePaths = {"formQuestions.question.variants"})
    @NonNull
    Optional<Form> findById(Long aLong);

    @Override
    @EntityGraph(attributePaths = {"scale", "formQuestions.question.variants"})
    @NonNull
    List<Form> findAll ();

    List<Form> findFormById(Long id);

    List<Form> findAllByName(String name);

    /*@Query(value = "select sum(v.score) from Form f " +
            "join FormQuestion fq on fq.id=f.id " +
            "join Question q on q.id=fq.idQuestion.id " +
            "join Variant v on v.id=q.id " +
            "join Answer a on a.id = v.id " +
            "where f.id=?1 and a.patient.id=?2")
    Double sumScoreForm(Long idForm, Long idPatient);*/

}
