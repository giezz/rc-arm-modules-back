package ru.rightcode.formback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.formback.model.Variant;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {
}