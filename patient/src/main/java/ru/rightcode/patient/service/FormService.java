package ru.rightcode.patient.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.patient.dto.PatientInfo;
import ru.rightcode.patient.dto.request.AnswerRequest;
import ru.rightcode.patient.model.*;
import ru.rightcode.patient.repository.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FormService {

    private final AnswerRepository answerRepository;
    private final ModuleFormRepository moduleFormRepository;
    private final FormResultRepository formResultRepository;
    private final PatientRepository patientRepository;
    private final VariantRepository variantRepository;

    // Нет никаких проверок на соответсвие бизнес-логики
    @Transactional
    public BigDecimal submitAnswers(Long formId, List<AnswerRequest> request, String login) {
        PatientInfo patientInfo = patientRepository.findByUserUsername(login, PatientInfo.class)
                .orElseThrow(EntityNotFoundException::new);

        List<Variant> variants = variantRepository
                .findAllById(request.stream().map(AnswerRequest::variantId).toList());

        List<Answer> answers = new ArrayList<>();
        Patient patient = new Patient();
        patient.setId(patientInfo.getId());

        for (Variant variant : variants) {
            answers.add(new Answer(patient, variant));
        }

        answerRepository.saveAll(answers);

        return calculateFormScore(formId, patient, variants);
    }

//    @Transactional
    public BigDecimal calculateFormScore(Long formId, Patient patient, List<Variant> variants) {
        BigDecimal sum = BigDecimal.valueOf(0);
        for (Variant variant : variants) {
            sum = sum.add(variant.getScore());
        }

        Form form = new Form();
        form.setId(formId);

        FormResult formResult = new FormResult();
        formResult.setForm(form);
        formResult.setPatient(patient);
        formResult.setScore(sum);
        formResult.setCreationDate(LocalDate.now());
        formResultRepository.save(formResult);

        return sum;
    }
}
