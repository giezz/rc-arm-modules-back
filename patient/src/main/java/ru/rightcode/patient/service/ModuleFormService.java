package ru.rightcode.patient.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.patient.dto.request.AnswerRequest;
import ru.rightcode.patient.model.*;
import ru.rightcode.patient.repository.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ModuleFormService {

    private final VariantRepository variantRepository;
    private final ModuleFormRepository moduleFormRepository;
    private final ModuleFormAnswerRepository moduleFormAnswerRepository;

    @Transactional
    public BigDecimal submitModuleFormAnswer(Long moduleFormId, List<AnswerRequest> request, String login) {
//        PatientInfo patientInfo = patientRepository.findByUserLogin(login, PatientInfo.class)
//                .orElseThrow(() -> new  EntityNotFoundException("Пациент не найден"));
        ModuleForm moduleForm = moduleFormRepository.findById(moduleFormId)
                .orElseThrow(() -> new  EntityNotFoundException("Анкета модуля не найдена"));
        if (moduleForm.getFinishedAt() != null) {
            throw new EntityExistsException("Анкета модуля уже пройдена");
        }

        // FIXME: bad query performance
        BigDecimal sum = BigDecimal.valueOf(0);
        for (AnswerRequest answerRequest : request) {
            Variant variant = variantRepository.findById(answerRequest.variantId()).orElseThrow();
            ModuleFormAnswer moduleFormAnswer = new ModuleFormAnswer();
            moduleFormAnswer.setModuleForm(moduleForm);
            moduleFormAnswer.setVariant(variant);
            moduleFormAnswerRepository.save(moduleFormAnswer);
            sum = sum.add(variant.getScore());
        }

        moduleForm.setFinishedAt(Instant.now());
        moduleForm.setScore(sum);
        moduleFormRepository.save(moduleForm);
        return sum;
    }
}
