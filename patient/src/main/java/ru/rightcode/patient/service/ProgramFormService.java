package ru.rightcode.patient.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.patient.dto.request.AnswerRequest;
import ru.rightcode.patient.dto.response.form.VariantResponse;
import ru.rightcode.patient.exception.BusinessException;
import ru.rightcode.patient.mapper.form.VariantResponseMapper;
import ru.rightcode.patient.model.*;
import ru.rightcode.patient.repository.ProgramFormAnswerRepository;
import ru.rightcode.patient.repository.ProgramFormRepository;
import ru.rightcode.patient.repository.VariantRepository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProgramFormService {

    private final VariantRepository variantRepository;
    private final ProgramFormRepository programFormRepository;
    private final ProgramFormAnswerRepository programFormAnswerRepository;

    private final VariantResponseMapper variantResponseMapper;

    @Transactional
    public List<VariantResponse> getAnsweredVariants(Long programFormId)  {
        List<Variant> variants = variantRepository.findAnsweredVariantsByProgramFormId(programFormId);
        return variants.stream().map(variantResponseMapper::toResponse).collect(Collectors.toList());
    }

    @Caching(evict = {
            @CacheEvict("PatientService::getFormResponseByProgramIdFormId"),
            @CacheEvict("PatientService::getFormByModuleIdFormId"),
            @CacheEvict("PatientService::getHistory"),
            @CacheEvict("PatientService::getRehabProgram")
    })
    @Transactional
    public BigDecimal submitProgramFormAnswer(Long programFormId, List<AnswerRequest> request) {
        ProgramForm programForm = programFormRepository.findById(programFormId)
                .orElseThrow(() -> new EntityNotFoundException("Анкета программы реабилитации не найдена"));
        if (programForm.getFinishedAt() != null) {
            throw new BusinessException("Анкета модуля уже пройдена");
        }

        List<Long> variantIds = request.stream().map(AnswerRequest::variantId).toList();
        List<Variant> variants = variantRepository.findAllById(variantIds);

        validateVariants(programForm.getProgramFormAnswers(), variants);

        BigDecimal sum = calculateSumProgramForm(request, variants);

        List<ProgramFormAnswer> programFormAnswers = createModuleFormAnswers(programForm, variants);
        programFormAnswerRepository.saveAll(programFormAnswers);

        updateSumFinishedAt(programForm, sum);
        return sum;
    }

    // вынесен в отдельный метод, обнволение данных
    public void updateSumFinishedAt(ProgramForm programForm, BigDecimal sum) {
        programForm.setFinishedAt(Instant.now());
        programForm.setScore(sum);
        programFormRepository.save(programForm);
    }

    private void validateVariants(List<ProgramFormAnswer> programFormAnswers, List<Variant> variants) {
        for (Variant variant : variants) {
            if (programFormAnswers.stream().anyMatch(mFA -> mFA.getVariant().getId().equals(variant.getId()))) {
                throw new BusinessException("Ответ уже отмечен как пройден");
            }
        }
    }

    private BigDecimal calculateSumProgramForm(List<AnswerRequest> request, List<Variant> variants) {
        BigDecimal sum = BigDecimal.ZERO;
        Map<Long, BigDecimal> variantScoreMap = request.stream()
                .filter(aR -> aR.scaleScore() != null)
                .collect(Collectors.toMap(AnswerRequest::variantId, AnswerRequest::scaleScore));

        for (Variant variant : variants) {
            BigDecimal scaleScore = variantScoreMap.get(variant.getId());
            sum = sum.add(scaleScore != null ? scaleScore : variant.getScore());
        }
        return sum;
    }

    private List<ProgramFormAnswer> createModuleFormAnswers(ProgramForm programForm, List<Variant> variants) {
        return variants.stream()
                .map(variant -> {
                    ProgramFormAnswer pFA = new ProgramFormAnswer();
                    pFA.setProgramForm(programForm);
                    pFA.setVariant(variant);
                    return pFA;
                })
                .collect(Collectors.toList());
    }
}
