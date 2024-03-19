package ru.rightcode.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.patient.model.ModuleForm;

@Repository
public interface ModuleFormRepository extends JpaRepository<ModuleForm, Long> {
}