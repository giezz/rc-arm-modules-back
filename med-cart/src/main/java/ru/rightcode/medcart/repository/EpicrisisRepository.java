package ru.rightcode.medcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.medcart.model.Epicrisis;

import java.util.List;

@Repository
public interface EpicrisisRepository extends JpaRepository<Epicrisis, Long> {

    List<Epicrisis> findAllByPatientPatientCode(Long code);
}
