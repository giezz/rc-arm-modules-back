package ru.rightcode.arm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rightcode.arm.model.ModuleForm;

public interface ModuleFormRepository extends JpaRepository<ModuleForm, Long> {
}