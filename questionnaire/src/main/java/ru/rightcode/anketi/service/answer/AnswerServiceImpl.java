package ru.rightcode.anketi.service.answer;

import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rightcode.anketi.model.Answer;
import ru.rightcode.anketi.repository.AnswerRepository;

import java.util.List;

@WebService
@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService{

    @Autowired
    private final AnswerRepository answerRepository;

    @Override
    public List<Answer> getAnswers(Long patient_id) {
        return answerRepository.findAllByPatient_id(patient_id);
    }
}
