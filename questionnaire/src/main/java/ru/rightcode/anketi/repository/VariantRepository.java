package ru.rightcode.anketi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.anketi.model.Variant;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {
}
