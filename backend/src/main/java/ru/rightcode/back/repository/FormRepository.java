package ru.rightcode.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rightcode.back.model.Form;

public interface FormRepository extends JpaRepository<Form, Long> {
}