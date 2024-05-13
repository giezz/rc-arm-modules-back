package ru.rightcode.arm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.model.Protocol;

import java.util.List;

@Repository
public interface ProtocolRepository extends JpaRepository<Protocol, Long> {

    List<Protocol> findByRehabProgramId(Long id);
}