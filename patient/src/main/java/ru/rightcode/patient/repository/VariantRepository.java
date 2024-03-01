package ru.rightcode.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.patient.model.Variant;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {
}