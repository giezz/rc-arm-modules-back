package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.response.*;
import ru.rightcode.arm.mapper.FormResponseMapper;
import ru.rightcode.arm.mapper.ModuleFormsResponseMapper;
import ru.rightcode.arm.mapper.QuestionResponseMapper;
import ru.rightcode.arm.mapper.VariantResponseMapper;
import ru.rightcode.arm.model.*;
import ru.rightcode.arm.repository.ModuleFormRepository;
import ru.rightcode.arm.repository.QuestionRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ModuleFormService {

    private final ModuleFormRepository moduleFormRepository;
    private final QuestionRepository questionRepository;

    private final ModuleFormsResponseMapper moduleFormsResponseMapper;
    private final FormResponseMapper formResponseMapper;
    private final QuestionResponseMapper questionResponseMapper;
    private final VariantResponseMapper variantResponseMapper;


    public List<ModuleFormResponse> getResults(Long programId) {
        List<ModuleForm> results = moduleFormRepository.findFormResultsByRehabProgramId(programId);
        if (results.isEmpty()) {
            throw new EntityNotFoundException("No results");
        }

        return results.stream().map(moduleFormsResponseMapper::map).toList();
    }

    // cringe???
    public FormWithAnswersResponse getFormAndResults(Long moduleFormId) {
        ModuleForm moduleForm = moduleFormRepository.findFormResultsWithAnswers(moduleFormId)
                .orElseThrow(() -> new EntityNotFoundException("ModuleForm not found"));
        Form form = moduleForm.getForm();
        List<Question> questions = questionRepository.findQuestionsByFormId(form.getId());
        List<ModuleFormAnswer> moduleFormAnswers = moduleForm.getModuleFormAnswers();
        List<Variant> answeredVariants = moduleFormAnswers
                .stream()
                .filter(ModuleFormAnswer::getIsMarked)
                .map(ModuleFormAnswer::getVariant)
                .toList();
        FormResponse formResponse = formResponseMapper.map(form);
        List<QuestionResponse> questionsResponse = questions
                .stream()
                .map(questionResponseMapper::map)
                .toList();
        List<VariantResponse> answeredVariantsResponse = answeredVariants
                .stream()
                .map(variantResponseMapper::map)
                .toList();

        return new FormWithAnswersResponse(
                formResponse,
                questionsResponse,
                answeredVariantsResponse
        );
    }


}
