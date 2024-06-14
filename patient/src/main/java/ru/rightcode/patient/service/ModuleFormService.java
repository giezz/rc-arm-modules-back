package ru.rightcode.patient.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.patient.dto.request.AnswerRequest;
import ru.rightcode.patient.dto.response.form.VariantResponse;
import ru.rightcode.patient.exception.BusinessException;
import ru.rightcode.patient.mapper.form.VariantResponseMapper;
import ru.rightcode.patient.model.*;
import ru.rightcode.patient.repository.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ModuleFormService {

    private final VariantRepository variantRepository;
    private final ModuleFormRepository moduleFormRepository;
    private final ModuleFormAnswerRepository moduleFormAnswerRepository;

    private final VariantResponseMapper variantResponseMapper;

    @Transactional
    public List<VariantResponse> getAnsweredVariants(Long moduleFormId)  {
        List<Variant> variants = variantRepository.findAnsweredVariantsByModuleFormId(moduleFormId);
        return variants.stream().map(variantResponseMapper::toResponse).collect(Collectors.toList());
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict("PatientService::getFormResponseByProgramIdFormId"),
            @CacheEvict("PatientService::getFormByModuleIdFormId"),
            @CacheEvict("PatientService::getHistory"),
            @CacheEvict("PatientService::getRehabProgram")
    })
    public BigDecimal submitModuleFormAnswer(Long moduleFormId, List<AnswerRequest> request) {
        ModuleForm moduleForm = moduleFormRepository.findById(moduleFormId)
                .orElseThrow(() -> new EntityNotFoundException("Модуль не найден"));
        if (moduleForm.getFinishedAt() != null) {
            throw new BusinessException("Анкета модуля уже пройдена");
        }

        List<Long> variantIds = request.stream().map(AnswerRequest::variantId).toList();
        List<Variant> variants = variantRepository.findAllById(variantIds);

        validateVariants(moduleForm.getModuleFormAnswers(), variants);

        BigDecimal sum = calculateSumModuleForm(request, variants);

        List<ModuleFormAnswer> moduleFormAnswers = createModuleFormAnswers(moduleForm, variants);
        moduleFormAnswerRepository.saveAll(moduleFormAnswers);

        moduleForm.setFinishedAt(Instant.now());
        moduleForm.setScore(sum);
        moduleFormRepository.save(moduleForm);

        return sum;
    }

    private void validateVariants(List<ModuleFormAnswer> moduleFormAnswers, List<Variant> variants) {
        for (Variant variant : variants) {
            if (moduleFormAnswers.stream().anyMatch(mFA -> mFA.getVariant().getId().equals(variant.getId())))  {
                throw new BusinessException("Ответ уже отмечен как пройден");
            }
        }
    }

    private BigDecimal calculateSumModuleForm(List<AnswerRequest> request, List<Variant> variants) {
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

    private List<ModuleFormAnswer> createModuleFormAnswers(ModuleForm moduleForm, List<Variant> variants) {
        return variants.stream()
                .map(variant -> {
                    ModuleFormAnswer mFA = new ModuleFormAnswer();
                    mFA.setModuleForm(moduleForm);
                    mFA.setVariant(variant);
                    return mFA;
                })
                .collect(Collectors.toList());
    }
}
