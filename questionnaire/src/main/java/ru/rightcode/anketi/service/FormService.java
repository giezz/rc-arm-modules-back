package ru.rightcode.anketi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.anketi.dto.FormDto;
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
    private final ScaleService scaleService;


    /**
     * Получить анкету (Form) по id
     * @param id Long
     * @return Form
     */
    public Form getFormById(Long id) {
        return formRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Form not found with id: " + id));
    }

    /**
     * Получить список всех анкет
     * @return List<FormDto>
     */
    public List<FormDto> getAllFormDto() {
        List<Form> forms = formRepository.findAll();
        return forms.stream().map((Form form) -> formMapper.toDto(
                form, form.getFormQuestions().stream().map(FormQuestion::getQuestion).toList()
        )).toList();
    }

    /**
     * Получить список анкет по имени
     * @param name String
     * @return List<FormDto>
     */
    public List<FormDto> getListFormDtoByName(String name) {
        List<Form> forms = formRepository.findAllByName(name);
        return forms.stream().map((Form form) -> formMapper.toDto(
                form, form.getFormQuestions().stream().map(FormQuestion::getQuestion).toList()
        )).toList();
    }

    /**
     * Получить анкету по id
     * @param id Long
     * @return FormDto
     */
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

    /**
     * Удалить анкету по id
     * @param id Long
     */
    @Transactional
    public void deleteForm(Long id) {
        formQuestionService.deleteByFormId(id);
        formRepository.deleteById(id);
    }

    /**
     * Создать анкету
     * @param formDTO FormDto
     * @return FormDto
     */
    // Возможность удалять вопросы и варианты
    @Transactional
    public FormDto createForm(FormDto formDTO) {
        // Создаем новую форму
        Form form = formMapper.toEntity(formDTO);

        return createSaveFormDto(formDTO, form);
    }

    /**
     * Обновить анкету
     * Возможность удалять вопросы и варианты
     * Если ранее был вопрос или вариант, то удаляем его
     * @param id Long
     * @param formDTO FormDto
     * @return FormDto
     */
    @Transactional
    public FormDto updateForm( Long id, FormDto formDTO) {
        Form existingForm = getFormById(id);
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
        List<Question> savedNewQuestions = formQuestionService.processQuestionsAndVariants(formDto.getQuestions());

        // Сохраняем форму в базе данных
        Form savedForm = formRepository.save(form);

        // Удаляем старые вопросы, которых уже нет в новом списке вопросов
        formQuestionService.deleteOldQuestions(formDto.getQuestions(), oldQuestions, savedForm.getId());
        // Создаем связи между формой и вопросами
        savedNewQuestions.forEach(question -> formQuestionService.save(createFormQuestion(savedForm, question)));

        // Соберем новые вопросы и старые вопросы
        List<Question> allQuestions = Stream.concat(oldQuestions.stream(), savedNewQuestions.stream())
                .toList();
        return formMapper.toDto(savedForm, allQuestions);
    }
}
