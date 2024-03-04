package ru.rightcode.arm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.model.Form;

import java.util.Optional;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {
    @Query("select f from Form f join fetch f.results where f.id = :id")
    Optional<Form> findFormWithResults(@Param("id") Long id);
}