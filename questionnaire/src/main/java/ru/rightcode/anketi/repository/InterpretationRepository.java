package ru.rightcode.anketi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rightcode.anketi.model.Interpretation;

public interface InterpretationRepository extends JpaRepository<Interpretation, Long> {
}