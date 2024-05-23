package ru.rightcode.patient.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.patient.model.ModuleForm;

import java.util.Optional;

@Repository
public interface ModuleFormRepository extends JpaRepository<ModuleForm, Long> {

    @EntityGraph(attributePaths = {"form.scale"})
    Optional<ModuleForm> getModuleFormByModuleIdAndId(@NonNull Long moduleId, @NonNull Long moduleFormId);
}