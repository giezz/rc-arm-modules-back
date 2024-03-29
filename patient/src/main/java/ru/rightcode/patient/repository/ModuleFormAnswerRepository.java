package ru.rightcode.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.patient.model.ModuleFormAnswer;

@Repository
public interface ModuleFormAnswerRepository extends JpaRepository<ModuleFormAnswer, Long> {
}