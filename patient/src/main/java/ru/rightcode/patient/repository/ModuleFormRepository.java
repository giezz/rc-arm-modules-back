package ru.rightcode.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rightcode.patient.model.ModuleForm;

public interface ModuleFormRepository extends JpaRepository<ModuleForm, Long> {
}