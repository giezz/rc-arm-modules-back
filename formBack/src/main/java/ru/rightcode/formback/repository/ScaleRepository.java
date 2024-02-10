package ru.rightcode.formback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.formback.model.Scale;

@Repository
public interface ScaleRepository extends JpaRepository<Scale, Long> {
}