package ru.rightcode.arm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.response.ProgramFormResultResponse;
import ru.rightcode.arm.mapper.ProgramFormResultMapper;
import ru.rightcode.arm.model.ProgramForm;
import ru.rightcode.arm.repository.ProgramFormRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ProgramFormService {

    private final ProgramFormRepository programFormRepository;
    private final ProgramFormResultMapper programFormResultMapper;

    public List<ProgramFormResultResponse> getAllResults(Long programId, List<Long> excludeIds) {
        List<ProgramForm> results = programFormRepository.findFormResultsByRehabProgramId(programId, excludeIds);

        return results.stream().map(pf -> programFormResultMapper.toDto(
                pf,
                pf.getForm().getScale().getInterpretations().get(0).getDescription()
        )).toList();
    }

}
