package ru.rightcode.anketi.service.form;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.anketi.dto.FormDto;
import ru.rightcode.anketi.dto.QuestionDto;
import ru.rightcode.anketi.dto.VariantDto;
import ru.rightcode.anketi.exception.NotFoundException;
import ru.rightcode.anketi.mapper.FormDtoMapper;
import ru.rightcode.anketi.mapper.QuestionDtoMapper;
import ru.rightcode.anketi.mapper.VariantDtoMapper;
import ru.rightcode.anketi.model.Form;
import ru.rightcode.anketi.model.FormQuestion;
import ru.rightcode.anketi.model.Question;
import ru.rightcode.anketi.model.Variant;
import ru.rightcode.anketi.repository.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class FormServiceImpl {

    @Autowired
    private final FormRepository formRepository;

    @Autowired
    private final ScaleRepository scaleRepository;

    @Autowired
    private final QuestionRepository questionRepository;

    @Autowired
    private final FormQuestionRepository formQuestionRepository;

    @Autowired
    private final VariantRepository variantRepository;

    private final FormDtoMapper formDtoMapper;
    private final QuestionDtoMapper questionDtoMapper;
    private final VariantDtoMapper variantDtoMapper;


    public List<FormDto> getAllForms() {
        List<Form> forms = formRepository.findAll();
        return forms.stream()
                .map(formDtoMapper::toDto)
                .collect(Collectors.toList());
    }


    public List<FormDto> getFormByName(String name) {
        List<Form> forms = formRepository.findAllByName(name);
        return forms.stream()
                .map(formDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public FormDto getFormById(Long id) {
        Form form = formRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Form not found with id: " + id));
        List<FormQuestion> formQuestionList = formQuestionRepository.findByIdForm(form);
        List<Question> questions = new ArrayList<>();
        for (FormQuestion fq : formQuestionList) {
            if (fq.getIdForm().getId().equals(form.getId())) {
                questions.add(fq.getIdQuestion());
            }
        }
        return formDtoMapper.toDto(form);
    }

    // TODO: При указании существующих вариантов ответа, невозможно указать один и тот же вариант к нескольким вопросам
    public FormDto createForm(FormDto formDTO) {

        Form form = formDtoMapper.toEntity(formDTO);
        List<QuestionDto> questionDtos = formDTO.questions();
        // Проверка на пустой questions
        if (questionDtos.isEmpty()) {
            return formDtoMapper.toDto(form);
        }

        List<FormQuestion> formQuestionList = new ArrayList<>();
        List<Variant> variantList = new ArrayList<>();
        List<Question> questionList = questionDtoMapper.listToEntity(questionDtos);
        // Проверка если указано только id
        if (questionDtos.stream().allMatch(q -> q.getId() != null)){
            List<Long> ids = questionList.stream()
                    .map(Question::getId)
                    .toList();
            List<Question> questionListFromDb = questionRepository.findQuestionsByIds(ids);

            for (Question q : questionListFromDb) {
                FormQuestion formQuestion1 = FormQuestion.builder()
                        .idForm(form)
                        .idQuestion(q)
                        .createdAt(Instant.now())
                        .build();

                formQuestionList.add(formQuestion1);
            }
        }else {
            // Создание новых вопросов, если указаны все остальные поля кроме идентификатора
            for (QuestionDto questionDto : questionDtos) {
                if (questionDto.getId() == null) {
                    Question question = questionDtoMapper.toEntity(questionDto);
                    questionList.add(question);
                    FormQuestion formQuestion = FormQuestion.builder()
                            .idForm(form)
                            .idQuestion(question)
                            .createdAt(Instant.now())
                            .build();
                    formQuestionList.add(formQuestion);

                    // Создание вариантов ответов
                    List<VariantDto> variantDtos = questionDto.getVariants();
                    if (variantDtos != null) {
                        for (VariantDto variantDto : variantDtos) {
                            Variant variant = variantDtoMapper.toEntity(variantDto);
                            variant.setQuestion_id(question);
                            variantList.add(variant);
                        }
                    }
                }
            }
            questionRepository.saveAll(questionList);
            variantRepository.saveAll(variantList);
        }

        Form form1 = formRepository.save(form);
        formQuestionRepository.saveAll(formQuestionList);

        return formDtoMapper.toDto(form1);
    }

    public void deleteForm(Long id) {
        formRepository.deleteById(id);
    }

    private boolean allQuestionsHaveIds(List<QuestionDto> questions) {
        return questions.stream().allMatch(q -> q.getId() != null);
    }

}
