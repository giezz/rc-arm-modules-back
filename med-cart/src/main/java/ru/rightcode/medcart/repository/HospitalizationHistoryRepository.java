package ru.rightcode.medcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.medcart.model.HospitalizationHistory;

import java.util.List;

@Repository
public interface HospitalizationHistoryRepository extends JpaRepository<HospitalizationHistory, Long> {

    List<HospitalizationHistory> findAllByPatientPatientCode(Long code);
}
