package ru.rightcode.patient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.patient.exception.NotFoundException;
import ru.rightcode.patient.model.Form;
import ru.rightcode.patient.repository.FormRepository;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class FormQuestionService {
    private final FormRepository formRepository;

    @Transactional
    public Form getForm(Long formId) {
        return formRepository.findById(formId).orElseThrow(
                () -> new NotFoundException("Анкета не найдена (FormRepository)")
        );
    }

}
