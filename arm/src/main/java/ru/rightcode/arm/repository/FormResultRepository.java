package ru.rightcode.arm.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.model.FormResult;
import ru.rightcode.arm.model.FormResult_;

import java.util.List;

@Repository
public interface FormResultRepository extends JpaRepository<FormResult, Long> {
    List<FormResult> findByPatientIdAndFormId(Long patientId, Long formId);
    List<FormResult> findByPatientId(Long patientId);
    @EntityGraph(attributePaths = {FormResult_.FORM})
    List<FormResult> findByPatientIdAndFormIdIn(Long patientId, List<Long> formIds);
}