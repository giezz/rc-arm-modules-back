package ru.rightcode.arm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.model.ModuleFormAnswer;

@Repository
public interface ModuleFormAnswerRepository extends JpaRepository<ModuleFormAnswer, Long> {
}