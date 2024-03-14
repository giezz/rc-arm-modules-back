package ru.rightcode.anketi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.anketi.model.Interpretation;
@Repository
public interface InterpretationRepository extends JpaRepository<Interpretation, Long> {
}