package ru.rightcode.patient.service;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.patient.dto.PatientInfo;
import ru.rightcode.patient.dto.request.AnswerRequest;
import ru.rightcode.patient.model.*;
import ru.rightcode.patient.repository.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FormService {

    private final AnswerRepository answerRepository;
    private final FormResultRepository formResultRepository;
    private final PatientRepository patientRepository;
    private final VariantRepository variantRepository;
    private final RehabProgramRepository rehabProgramRepository;

    // Нет никаких проверок на соответсвие бизнес-логики

    @Transactional
    public BigDecimal submitAnswers(Long formId, List<AnswerRequest> request, String login) {
        PatientInfo patientInfo = patientRepository.findByUserLogin(login, PatientInfo.class).orElseThrow();
        List<Variant> variants = variantRepository
                .findAllById(request.stream().map(AnswerRequest::variantId).toList());
        FormResult formResult = createResult(patientInfo.getId(), formId, variants);
        List<Answer> answers = new ArrayList<>();
        for (Variant variant : variants) {
            Answer answer = new Answer();
            answer.setFormResult(formResult);
            answer.setVariant(variant);
            answers.add(answer);
        }
        answerRepository.saveAll(answers);
        return formResult.getScore();
    }

    private FormResult createResult(Long patientId, Long formId, List<Variant> variants) {
        BigDecimal sum = BigDecimal.valueOf(0);
        RehabProgram rehabProgram = rehabProgramRepository.findByPatientIdAndIsCurrentTrue(patientId).orElseThrow();
        for (Variant variant : variants) {
            sum = sum.add(variant.getScore());
        }
        Form form = new Form();
        form.setId(formId);
        FormResult formResult = new FormResult();
        formResult.setForm(form);
        formResult.setRehabProgram(rehabProgram);
        formResult.setScore(sum);
        formResult.setCreationDate(Instant.now());
        return formResultRepository.save(formResult);
    }
}
