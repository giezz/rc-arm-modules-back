package ru.rightcode.anketi.service.form;

import lombok.RequiredArgsConstructor;
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
import ru.rightcode.anketi.repository.FormQuestionRepository;
import ru.rightcode.anketi.repository.FormRepository;
import ru.rightcode.anketi.repository.QuestionRepository;
import ru.rightcode.anketi.repository.VariantRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class FormServiceImpl {

    private final FormRepository formRepository;
    private final QuestionRepository questionRepository;
    private final FormQuestionRepository formQuestionRepository;
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



    public void deleteForm(Long id) {
        formRepository.deleteById(id);
    }

    // TODO: При указании существующих вариантов ответа, невозможно указать один и тот же вариант к нескольким вопросам
    public FormDto createForm(FormDto formDTO) {
        Form form = formDtoMapper.toEntity(formDTO);
        List<QuestionDto> questionDtos = formDTO.questions();

        if (questionDtos.isEmpty()) {
            return formDtoMapper.toDto(form);
        }

        List<FormQuestion> formQuestionList = new ArrayList<>();
        List<Variant> variantList = new ArrayList<>();
        List<Question> questionList = processQuestions(questionDtos, formQuestionList, variantList, form);

        saveQuestionsAndVariants(questionList, variantList);

        Form form1 = formRepository.save(form);
        formQuestionRepository.saveAll(formQuestionList);

        return getFormById(form1.getId());
    }

    private List<Question> processQuestions(List<QuestionDto> questionDtos, List<FormQuestion> formQuestionList,
                                            List<Variant> variantList, Form form) {
        List<Question> questionList = new ArrayList<>();

        for (QuestionDto questionDto : questionDtos) {
            if (questionDto.getId() != null) {
                processExistingQuestion(questionDto, form, formQuestionList);
            } else {
                processNewQuestion(questionDto, form, formQuestionList, variantList, questionList);
            }
        }

        return questionList;
    }

    private void processExistingQuestion(QuestionDto questionDto, Form form, List<FormQuestion> formQuestionList) {
        Question question = getQuestionById(questionDto.getId());
        FormQuestion formQuestion = createFormQuestion(form, question);
        formQuestionList.add(formQuestion);
    }

    private Question getQuestionById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new NotFoundException("Question not found with id: " + questionId));
    }

    private FormQuestion createFormQuestion(Form form, Question question) {
        return FormQuestion.builder()
                .idForm(form)
                .idQuestion(question)
                .createdAt(Instant.now())
                .build();
    }

    private void processNewQuestion(QuestionDto questionDto, Form form, List<FormQuestion> formQuestionList,
                                    List<Variant> variantList, List<Question> questionList) {
        Question question = questionDtoMapper.toEntity(questionDto);
        questionList.add(question);
        FormQuestion formQuestion = createFormQuestion(form, question);
        formQuestionList.add(formQuestion);

        processVariants(questionDto, question, variantList);
    }

    private void processVariants(QuestionDto questionDto, Question question, List<Variant> variantList) {
        List<VariantDto> variantDtos = questionDto.getVariants();
        if (variantDtos != null) {
            for (VariantDto variantDto : variantDtos) {
                Variant variant = variantDtoMapper.toEntity(variantDto);
                variant.setQuestion_id(question);
                variantList.add(variant);
            }
        }
    }

    private void saveQuestionsAndVariants(List<Question> questionList, List<Variant> variantList) {
        questionRepository.saveAll(questionList);
        variantRepository.saveAll(variantList);
    }


}
