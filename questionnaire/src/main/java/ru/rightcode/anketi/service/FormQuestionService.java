package ru.rightcode.anketi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.anketi.model.FormQuestion;
import ru.rightcode.anketi.repository.FormQuestionRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class FormQuestionService {
    private final FormQuestionRepository formQuestionRepository;

    public void save(FormQuestion fq){
        formQuestionRepository.save(fq);
    }

    public List<FormQuestion> findByFormId(Long id){
        return formQuestionRepository.findByFormId(id);
    }

    public void deleteByFormId(Long id){
        formQuestionRepository.deleteByFormId(id);
    }

    public void deleteByQuestionId(Long questionId){
        formQuestionRepository.deleteByQuestionId(questionId);
    }

    public void deleteByQuestionFormId(Long formId, Long questionId){
        formQuestionRepository.deleteByQuestionFormId(formId, questionId);
    }
}
