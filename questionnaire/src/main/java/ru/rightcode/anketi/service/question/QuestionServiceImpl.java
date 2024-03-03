package ru.rightcode.anketi.service.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.anketi.dto.QuestionDto;
import ru.rightcode.anketi.mapper.mapstruct.QuestionMapper;
import ru.rightcode.anketi.repository.QuestionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuestionServiceImpl {

    @Autowired
    private final QuestionRepository questionRepository;

    private final QuestionMapper questionMapper;

    @Autowired
    public QuestionServiceImpl(QuestionMapper questionMapper,
                               QuestionRepository questionRepository) {
        this.questionMapper= questionMapper;
        this.questionRepository = questionRepository;
    }


    public List<QuestionDto> getQuestions() {
        return questionRepository.findAll().stream().map(questionMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public QuestionDto getById(Long id){
        return questionRepository.findById(id).map(questionMapper::toDto).get();
    }
}
