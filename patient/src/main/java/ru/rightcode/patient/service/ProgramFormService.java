package ru.rightcode.patient.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.patient.dto.request.AnswerRequest;
import ru.rightcode.patient.model.ProgramForm;
import ru.rightcode.patient.model.ProgramFormAnswer;
import ru.rightcode.patient.model.Variant;
import ru.rightcode.patient.repository.ProgramFormAnswerRepository;
import ru.rightcode.patient.repository.ProgramFormRepository;
import ru.rightcode.patient.repository.VariantRepository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProgramFormService {

    private final VariantRepository variantRepository;
    private final ProgramFormRepository programFormRepository;
    private final ProgramFormAnswerRepository programFormAnswerRepository;

    @Transactional
    public BigDecimal submitProgramFormAnswer(Long programFormId, List<AnswerRequest> request, String login) {
//        PatientInfo patientInfo = patientRepository.findByUserLogin(login, PatientInfo.class)
//                .orElseThrow(() -> new  EntityNotFoundException("Пациент не найден"));
        ProgramForm programForm = programFormRepository.findById(programFormId)
                .orElseThrow(() -> new  EntityNotFoundException("Анкета программы реабилитации не найдена"));
        if (programForm.getFinishedAt() != null) {
            throw new EntityExistsException("Анкета модуля уже пройдена");
        }

        // FIXME: bad query performance
        BigDecimal sum = BigDecimal.valueOf(0);
        for (AnswerRequest answerRequest : request) {
            Variant variant = variantRepository.findById(answerRequest.variantId()).orElseThrow();
            ProgramFormAnswer programFormAnswer = new ProgramFormAnswer();
            programFormAnswer.setProgramForm(programForm);
            programFormAnswer.setVariant(variant);
            programFormAnswerRepository.save(programFormAnswer);
            sum = sum.add(variant.getScore());
        }

        programForm.setFinishedAt(Instant.now());
        programForm.setScore(sum);
        programFormRepository.save(programForm);
        return sum;
    }
}
