package ru.rightcode.anketi.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rightcode.anketi.dto.QuestionDto;
import ru.rightcode.anketi.dto.VariantDto;
import ru.rightcode.anketi.exception.NotFoundException;
import ru.rightcode.anketi.model.Question;
import ru.rightcode.anketi.model.Variant;
import ru.rightcode.anketi.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class QuestionDtoMapper implements Mapper<QuestionDto, Question>{
    private final VariantDtoMapper variantDtoMapper;
    private final QuestionRepository questionRepository;

    @Override
    public Question toEntity(QuestionDto questionDto) {
        if (questionDto.getId() != null){
            Optional<Question> q = questionRepository.findById(questionDto.getId());
            if (q.isPresent()){
                return q.get();
            }else{
                throw new NotFoundException("Question not found with id: "+ questionDto.getId());
            }
        }

        Question question = new Question();
        question.setId(questionDto.getId());
        question.setContent(questionDto.getContent());

        if (questionDto.getVariants() != null) {
            List<Variant> variants = questionDto.getVariants().stream()
                    .map(variantDtoMapper::toEntity)
                    .collect(Collectors.toList());
            question.setVariants(variants);
        }

        return question;
    }

    @Override
    public QuestionDto toDto(Question question){
        final List<VariantDto> variantDtoList = Optional.ofNullable(question.getVariants())
                .map(variantDtoMapper::variantDtoList)
                .orElse(null);
        return QuestionDto.builder()
                .id(question.getId())
                .content(question.getContent())
                .variants(variantDtoList)
                .build();
    }

    public List<Question> listToEntity(List<QuestionDto> questionDtoList){
        return questionDtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public List<QuestionDto> listToDto(List<Question> questionList){
        return questionList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}