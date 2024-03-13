package ru.rightcode.anketi.service.answer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.anketi.repository.AnswerRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class AnswerServiceImpl {

    @Autowired
    private final AnswerRepository answerRepository;


    public List<Answer> getAnswers(Long patient_id) {
        return answerRepository.findAllByPatient_id(patient_id);
    }
}
