package ru.rightcode.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.back.model.Scale;

@Repository
public interface ScaleRepository extends JpaRepository<Scale, Long> {
}