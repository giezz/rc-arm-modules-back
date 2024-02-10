package ru.rightcode.formback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.formback.model.Form;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {
}