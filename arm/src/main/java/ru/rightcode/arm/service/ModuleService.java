package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rightcode.arm.model.Module;
import ru.rightcode.arm.model.RehabProgram;
import ru.rightcode.arm.repository.ModuleRepository;
import ru.rightcode.arm.repository.RehabProgramRepository;

@Service
@RequiredArgsConstructor
public class ModuleService {

    private final ModuleRepository moduleRepository;

    private final RehabProgramRepository rehabProgramRepository;

    public void create(String name, Long rehabProgramId) {
        RehabProgram rehabProgram = rehabProgramRepository.findById(rehabProgramId).orElseThrow(EntityNotFoundException::new);
        Module module = new Module();
        module.setName(name);
        module.setRehabProgram(rehabProgram);
        moduleRepository.save(module);
    }
}
