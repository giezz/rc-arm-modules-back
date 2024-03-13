package ru.rightcode.arm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.model.ProgramForm;

@Repository
public interface ProgramFormRepository extends JpaRepository<ProgramForm, Long> {
}