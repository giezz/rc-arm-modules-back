package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.response.ModuleFormResponse;
import ru.rightcode.arm.mapper.ModuleFormsResponseMapper;
import ru.rightcode.arm.model.Form;
import ru.rightcode.arm.model.ModuleForm;
import ru.rightcode.arm.model.ModuleFormAnswer;
import ru.rightcode.arm.repository.ModuleFormAnswerRepository;
import ru.rightcode.arm.repository.ModuleFormRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ModuleFormService {

    private final ModuleFormRepository moduleFormRepository;
    private final ModuleFormsResponseMapper moduleFormsResponseMapper;

    public List<ModuleFormResponse> getResults(Long programId) {
        List<ModuleForm> results = moduleFormRepository.findFormResultsByRehabProgramId(programId);
        if (results.isEmpty()) {
            throw new EntityNotFoundException("No results");
        }

        return results.stream().map(moduleFormsResponseMapper::map).toList();
    }

    public void getFormAndResults(Long moduleFormId) {
        ModuleForm moduleForm = moduleFormRepository.findFormResultsWithAnswers(moduleFormId).orElseThrow();

    }
}
