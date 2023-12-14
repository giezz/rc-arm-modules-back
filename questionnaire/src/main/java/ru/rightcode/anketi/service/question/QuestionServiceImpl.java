package ru.rightcode.anketi.service.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rightcode.anketi.model.Question;
import ru.rightcode.anketi.repository.QuestionRepository;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> getQuestions(){
        return questionRepository.findAll();
    }
}
