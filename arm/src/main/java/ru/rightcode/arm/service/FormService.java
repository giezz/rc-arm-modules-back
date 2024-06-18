package ru.rightcode.arm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.response.FormDetailsResponse;
import ru.rightcode.arm.dto.response.FormResponse;
import ru.rightcode.arm.dto.response.PageableResponse;
import ru.rightcode.arm.dto.response.QuestionResponse;
import ru.rightcode.arm.mapper.FormMapper;
import ru.rightcode.arm.mapper.QuestionMapper;
import ru.rightcode.arm.model.Form;
import ru.rightcode.arm.model.Question;
import ru.rightcode.arm.repository.FormRepository;
import ru.rightcode.arm.repository.QuestionRepository;
import ru.rightcode.arm.util.PageableUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class FormService {

    private final FormRepository formRepository;
    private final QuestionRepository questionRepository;

    private final FormMapper formMapper;
    private final QuestionMapper questionMapper;

    private final PageableUtils pageableUtils;

    public PageableResponse<FormResponse> getAll(int pageNumber, int pageSize, String name) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<Form> page = pageableUtils.getPageableResult(
                name,
                () -> formRepository.findAll(pageable),
                () -> formRepository.findAllByNameContainsIgnoreCase(name, pageable)
        );

        return new PageableResponse<>(
                page.get().map(formMapper::toDto).toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements()
        );
    }

    public FormDetailsResponse getFormDetails(Long formId) {
        Form form = formRepository.findWithQuestions(formId).orElseThrow();
        List<Question> questions = questionRepository.findQuestionsByFormId(form.getId());
        FormResponse formResponse = formMapper.toDto(form);
        List<QuestionResponse> questionResponses = questions.stream()
                .map(questionMapper::toDto)
                .toList();

        return new FormDetailsResponse(
                formResponse,
                questionResponses
        );
    }

}
