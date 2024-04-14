package ru.rightcode.anketi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.anketi.dto.FormDto;
import ru.rightcode.anketi.dto.QuestionDto;
import ru.rightcode.anketi.exception.NotFoundException;
import ru.rightcode.anketi.mapper.mapstruct.FormMapper;
import ru.rightcode.anketi.model.Form;
import ru.rightcode.anketi.model.FormQuestion;
import ru.rightcode.anketi.model.Question;
import ru.rightcode.anketi.repository.FormRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
@Transactional
public class FormService {

    private final FormRepository formRepository;
    private final FormMapper formMapper;

    private final FormQuestionService formQuestionService;
    private final VariantService variantService;
    private final QuestionService questionService;
    private final ScaleService scaleService;


    public Form getFormById(Long id) {
        return formRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Form not found with id: " + id));
    }

    public List<FormDto> getAllFormDto() {
        List<Form> forms = formRepository.findAll();
        return forms.stream().map((Form form) -> formMapper.toDto(
                form, form.getFormQuestions().stream().map(FormQuestion::getQuestion).toList()
        )).toList();
    }


    public List<FormDto> getListFormDtoByName(String name) {
        List<Form> forms = formRepository.findAllByName(name);
        return forms.stream().map((Form form) -> formMapper.toDto(
                form, form.getFormQuestions().stream().map(FormQuestion::getQuestion).toList()
        )).toList();
    }

    public FormDto getFormDtoById(Long id) {
        Form form = getFormById(id);
        List<FormQuestion> formQuestionList = form.getFormQuestions();
        List<Question> questions = new ArrayList<>();
        for (FormQuestion fq : formQuestionList) {
            if (fq.getForm().getId().equals(form.getId())) {
                questions.add(fq.getQuestion());
            }
        }
        return formMapper.toDto(form, questions);
    }

    @Transactional
    public void deleteForm(Long id) {
        List<FormQuestion> formQuestionList = formQuestionService.findByFormId(id);
        List<Question> questions = formQuestionList.stream().map(FormQuestion::getQuestion).toList();

        formQuestionService.deleteByFormId(id);
        questions.forEach(question -> {
            formQuestionService.deleteByQuestionId(question.getId());
            variantService.deleteByQuestionId(question.getId());
            questionService.delete(question);
        });
        formRepository.deleteById(id);
    }


    // Возможность удалять вопросы и варианты
    @Transactional
    public FormDto createForm(FormDto formDTO) {
        // Создаем новую форму
        Form form = formMapper.toEntity(formDTO);

        return createSaveFormDto(formDTO, form);
    }

    @Transactional
    public FormDto updateForm( Long id, FormDto formDTO) {
        Form existingForm = getFormById(formDTO.getId());
        // Обновляем поля существующей формы
        existingForm.setName(formDTO.getName());
        existingForm.setDescription(formDTO.getDescription());
        existingForm.setScale(scaleService.toEntity(formDTO.getScaleId()));

        return createSaveFormDto(formDTO, existingForm);
    }

    private FormQuestion createFormQuestion(Form form, Question question) {
        return FormQuestion.builder()
                .form(form)
                .question(question)
                .createdAt(Instant.now())
                .build();
    }

    private FormDto createSaveFormDto(FormDto formDto, Form form) {
        List<Question> oldQuestions = form.getFormQuestions()
                .stream()
                .map(FormQuestion::getQuestion)
                .filter(Objects::nonNull)
                .toList();
        // Обрабатываем вопросы и варианты
        List<Question> savedNewQuestions = processQuestionsAndVariants(formDto.getQuestions());

        // Сохраняем форму в базе данных
        Form savedForm = formRepository.save(form);

        List<Question> newQuestions = questionService.toEntityList(formDto.getQuestions());
        // Удаляем старые вопросы, которых уже нет в новом списке вопросов
        deleteOldQuestions(newQuestions, oldQuestions, savedForm.getId());
        // Создаем связи между формой и вопросами
        savedNewQuestions.forEach(question -> formQuestionService.save(createFormQuestion(savedForm, question)));

        // Соберем новые вопросы и старые вопросы
        List<Question> allQuestions = Stream.concat(oldQuestions.stream(), savedNewQuestions.stream())
                .toList();
        return formMapper.toDto(savedForm, allQuestions);
    }

    private List<Question> processQuestionsAndVariants(List<QuestionDto> questionDTOs) {
        List<Question> newQuestionList = new ArrayList<>();
        // Проходимся по всем вопросам в DTO и обновляем или создаем соответствующие вопросы и варианты
        for (QuestionDto questionDTO : questionDTOs) {
            Question question;
            // Проверяем, указан ли ID вопроса
            if (questionDTO.getId() != null) {
                // Если ID указан, получаем существующий вопрос из базы данных
                question = questionService.findById(questionDTO.getId());
                // Обновляем поля существующего вопроса
                question.setContent(questionDTO.getContent());
                question.setType(String.valueOf(questionDTO.getType()));
                question.setRequired(questionDTO.getRequired());
            } else {
                // Если ID не указан, создаем новый вопрос
                question = questionService.toEntity(questionDTO);
                newQuestionList.add(question);
            }

            Question savedQuestion = questionService.save(question);
            // Обрабатываем варианты для вопроса
            if (questionDTO.getVariants() != null) {
                variantService.processVariants(questionDTO.getVariants(), savedQuestion);
            }
        }

        return newQuestionList;
    }

    private void deleteOldQuestions(List<Question> newQuestionList, List<Question> oldQuestionList, Long savedFormId) {
        List<Question> ostatok = oldQuestionList.stream()
                .filter(question -> !newQuestionList.contains(question) && question != null)
                .toList();
        ostatok.forEach(question -> {
            formQuestionService.deleteByQuestionFormId(savedFormId, question.getId());
            variantService.deleteByQuestionId(question.getId());
            questionService.deleteById(question.getId());
        });
    }
}
