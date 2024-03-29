package ru.rightcode.anketi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.anketi.dto.QuestionDto;
import ru.rightcode.anketi.exception.NotFoundException;
import ru.rightcode.anketi.mapper.mapstruct.QuestionMapper;
import ru.rightcode.anketi.model.Question;
import ru.rightcode.anketi.repository.QuestionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionServiceImpl {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public QuestionDto toDto(Question question) {
        return questionMapper.toDto(question);
    }

    public Question toEntity(QuestionDto questionDto) {
        return questionMapper.toEntity(questionDto);
    }

    public List<Question> toEntityList(List<QuestionDto> questionDtoList) {
        return questionMapper.toEntityList(questionDtoList);
    }

    public List<QuestionDto> getQuestions() {
        return questionRepository.findAll().stream().map(questionMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public Question findById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Question not found with id: " + id));
    }

    @Transactional
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Transactional
    public QuestionDto getDtoById(Long id){
        return questionRepository.findById(id).map(questionMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Question not found with id: " + id));
    }

    @Transactional
    public void deleteById(Long id){questionRepository.deleteById(id);}

    @Transactional
    public void delete(Question question){questionRepository.delete(question);}
}
