package ru.rightcode.patient.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.patient.dto.request.AnswerRequest;
import ru.rightcode.patient.exception.BusinessException;
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

    @Transactional
    public BigDecimal submitProgramFormAnswer(Long programFormId, List<AnswerRequest> request) {
        ProgramForm programForm = programFormRepository.findById(programFormId)
                .orElseThrow(() -> new  EntityNotFoundException("Анкета программы реабилитации не найдена"));
        if (programForm.getFinishedAt() != null) {
            throw new BusinessException("Анкета модуля уже пройдена");
        }

        List<Long> variantIds = request.stream().map(AnswerRequest::variantId).toList();
        List<Variant> variants = variantRepository.findAllById(variantIds);

        validateVariants(programForm.getProgramFormAnswers(), variants);

        BigDecimal sum = calculateSumProgramForm(request, variants);

        List<ProgramFormAnswer> programFormAnswers = createModuleFormAnswers(programForm, variants);
        programFormAnswerRepository.saveAll(programFormAnswers);

        programForm.setFinishedAt(Instant.now());
        programForm.setScore(sum);
        programFormRepository.save(programForm);
        return sum;
    }


    private void validateVariants(List<ProgramFormAnswer> programFormAnswers, List<Variant> variants) {
        for (Variant variant : variants) {
            if (programFormAnswers.stream().anyMatch(mFA -> mFA.getVariant().getId().equals(variant.getId())))  {
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
