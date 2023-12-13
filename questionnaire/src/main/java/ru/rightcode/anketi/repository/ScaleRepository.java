package ru.rightcode.anketi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.anketi.model.Scale;

@Repository
public interface ScaleRepository extends JpaRepository<Scale, Long> {
}
