package ru.rightcode.anketi.service.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rightcode.anketi.model.Answer;
import ru.rightcode.anketi.repository.AnswerRepository;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService{

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public List<Answer> getAnswers(Long patientCode) {
        return answerRepository.findAll();
    }
}
