package ru.rightcode.anketi.service.question;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.anketi.model.Question;
import ru.rightcode.anketi.repository.QuestionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionServiceImpl {

    @Autowired
    private final QuestionRepository questionRepository;


    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }
}
