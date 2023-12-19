package ru.rightcode.arm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.model.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
}
