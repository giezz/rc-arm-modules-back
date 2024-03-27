package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.response.ProgramFormResponse;
import ru.rightcode.arm.mapper.ProgramFormResponseMapper;
import ru.rightcode.arm.model.ProgramForm;
import ru.rightcode.arm.repository.ProgramFormRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ProgramFormService {

    private final ProgramFormRepository programFormRepository;
    private final ProgramFormResponseMapper programFormResponseMapper;

    public List<ProgramFormResponse> getAllResults(Long programId, List<Long> excludeIds) {
        List<ProgramForm> results = programFormRepository.findFormResultsByRehabProgramId(programId, excludeIds);

        return results.stream().map(programFormResponseMapper::map).toList();
    }
}
