package ru.rightcode.medcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.medcart.model.RehabilitationHistory;

import java.util.List;

@Repository
public interface RehabilitationHistoryRepository extends JpaRepository<RehabilitationHistory, Long> {

    List<RehabilitationHistory> findAllByPatientPatientCode(Long patientCode);
}