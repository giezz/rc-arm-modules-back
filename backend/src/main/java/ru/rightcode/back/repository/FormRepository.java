package ru.rightcode.back.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.back.model.Form;

import java.util.List;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {
    List<Form> findAllByName(String name);
}