package ru.rightcode.arm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rightcode.arm.dto.response.QuestionResponse;
import ru.rightcode.arm.dto.response.VariantResponse;
import ru.rightcode.arm.model.Question;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionResponseMapper implements Mapper<Question, QuestionResponse> {

    private final VariantResponseMapper variantResponseMapper;

    @Override
    public QuestionResponse map(Question object) {
        final List<VariantResponse> variantResponses = object.getVariants()
                .stream()
                .map(variantResponseMapper::map)
                .toList();

        return new QuestionResponse(
                object.getId(),
                object.getContent(),
                variantResponses
        );
    }
}
