package ru.rightcode.patient.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rightcode.patient.model.Protocol;

public interface ProtocolRepository extends JpaRepository<Protocol, Long> {
    @EntityGraph(attributePaths = {"rehabProgram"})
    Protocol findByRehabProgramId (Long id);
}