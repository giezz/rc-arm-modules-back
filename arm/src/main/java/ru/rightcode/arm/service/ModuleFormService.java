package ru.rightcode.arm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.response.ModuleFormResultResponse;
import ru.rightcode.arm.mapper.ModuleFormResultResponseMapper;
import ru.rightcode.arm.model.ModuleForm;
import ru.rightcode.arm.repository.ModuleFormRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ModuleFormService {

    private final ModuleFormRepository moduleFormRepository;
    private final ModuleFormResultResponseMapper moduleFormResultResponseMapper;


    public List<ModuleFormResultResponse> getAllResults(Long programId, List<Long> excludeIds) {
        List<ModuleForm> results = moduleFormRepository.findFormResultsByRehabProgramId(programId, excludeIds);

        return results.stream()
                .map(moduleFormResultResponseMapper::map)
                .toList();
    }

}
