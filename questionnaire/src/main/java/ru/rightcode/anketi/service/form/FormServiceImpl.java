package ru.rightcode.anketi.service.form;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.anketi.dto.FormDto;
import ru.rightcode.anketi.dto.QuestionDto;
import ru.rightcode.anketi.dto.VariantDto;
import ru.rightcode.anketi.exception.NotFoundException;
import ru.rightcode.anketi.mapper.mapstruct.FormMapper;
import ru.rightcode.anketi.mapper.mapstruct.QuestionMapper;
import ru.rightcode.anketi.mapper.mapstruct.ScaleMapper;
import ru.rightcode.anketi.mapper.mapstruct.VariantMapper;
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
import java.util.Set;

@RequiredArgsConstructor
@Service
@Transactional
public class FormServiceImpl {

    private final FormRepository formRepository;
    private final QuestionRepository questionRepository;
    private final FormQuestionRepository formQuestionRepository;
    private final VariantRepository variantRepository;

    private final VariantMapper variantDtoMapper;
    private final QuestionMapper questionMapper;
    private final FormMapper formMapper;
    private final ScaleMapper scaleMapper;


    public List<FormDto> getAllForms() {
        List<Form> forms = formRepository.findAll();
        return forms.stream().map((Form form) -> formMapper.toDto(
                form, form.getFormQuestions().stream().map(FormQuestion::getQuestion).toList()
        )).toList();
    }


    public List<FormDto> getFormByName(String name) {
        List<Form> forms = formRepository.findAllByName(name);
        return forms.stream().map((Form form) -> formMapper.toDto(
                form, form.getFormQuestions().stream().map(FormQuestion::getQuestion).toList()
        )).toList();
    }

    public FormDto getFormById(Long id) {
        Form form = formRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Form not found with id: " + id));
//            formDtoMapper.toDto(form);
        List<FormQuestion> formQuestionList = form.getFormQuestions();
        List<Question> questions = new ArrayList<>();
        for (FormQuestion fq : formQuestionList) {
            if (fq.getForm().getId().equals(form.getId())) {
                questions.add(fq.getQuestion());
            }
        }
        return formMapper.toDto(form, questions);
    }

    // TODO: Добавить логику обработки анкеты

    public void deleteForm(Long id) {
        formRepository.deleteById(id);
    }


    // При указании id варианта, возвращается указанный id варианта, однако создался новый
    @Transactional
    public FormDto createForm(FormDto formDTO) {
        // Проверяем, указан ли ID формы
        if (formDTO.getId() != null) {
            // Если ID указан, значит мы обновляем существующую форму
            return updateForm(formDTO);
        } else {
            // Если ID не указан, создаем новую форму
            return saveNewForm(formDTO);
        }
    }

    private FormDto saveNewForm(FormDto formDTO) {
        // Создаем новую форму
        Form form = formMapper.toEntity(formDTO);

        // Обрабатываем вопросы и варианты
        processQuestionsAndVariants(formDTO.getQuestions(), form);

        // Сохраняем форму в базе данных
        Form savedForm = formRepository.save(form);

        // Возвращаем преобразованный объект DTO сохраненной формы
        return formMapper.toDto(savedForm,
                form.getFormQuestions().stream().map(FormQuestion::getQuestion).toList());
    }

    private FormDto updateForm(FormDto formDTO) {
        // Проверяем, существует ли форма с указанным ID в базе данных
        Form existingForm = formRepository.findById(formDTO.getId())
                .orElseThrow(() -> new NotFoundException("Form not found with id: " + formDTO.getId()));

        // Обновляем поля существующей формы
        existingForm.setName(formDTO.getName());
        existingForm.setDescription(formDTO.getDescription());
        existingForm.setScale(scaleMapper.toEntity(formDTO.getScaleId()));

        // Обрабатываем вопросы и варианты
        processQuestionsAndVariants(formDTO.getQuestions(), existingForm);

        // Сохраняем обновленную форму в базе данных
        Form savedForm = formRepository.save(existingForm);

        // Возвращаем преобразованный объект DTO сохраненной формы
        return formMapper.toDto(savedForm,
                savedForm.getFormQuestions().stream().map(FormQuestion::getQuestion).toList());
    }

    private void processQuestionsAndVariants(List<QuestionDto> questionDTOs, Form form) {
        // Проходимся по всем вопросам в DTO и обновляем или создаем соответствующие вопросы и варианты
        for (QuestionDto questionDTO : questionDTOs) {
            Question question;
            // Проверяем, указан ли ID вопроса
            if (questionDTO.getId() != null) {
                // Если ID указан, получаем существующий вопрос из базы данных
                question = questionRepository.findById(questionDTO.getId())
                        .orElseThrow(() -> new NotFoundException("Question not found with id: " + questionDTO.getId()));
                // Обновляем поля существующего вопроса
                question.setContent(questionDTO.getContent());
                question.setType(String.valueOf(questionDTO.getType()));
                question.setRequired(questionDTO.getRequired());
            } else {
                // Если ID не указан, создаем новый вопрос
                question = questionMapper.toEntity(questionDTO);
                FormQuestion formQuestion = createFormQuestion(form, question);
                formQuestionRepository.save(formQuestion);
            }
            // Сохраняем или обновляем вопрос в базе данных
            Question savedQuestion = questionRepository.save(question);

            // Обрабатываем варианты для вопроса
            processVariants(questionDTO.getVariants(), savedQuestion);
        }
    }

    private void processVariants(Set<VariantDto> variantDTOs, Question question) {
        // Проходимся по всем вариантам в DTO и обновляем или создаем соответствующие варианты
        for (VariantDto variantDTO : variantDTOs) {
            Variant variant;
            // Проверяем, указан ли ID варианта
            if (variantDTO.getId() != null) {
                // Если ID указан, получаем существующий вариант из базы данных
                variant = getVariantById(variantDTO.getId());
                // Обновляем поля существующего варианта
                variant.setContent(variantDTO.getContent());
                variant.setScore(variantDTO.getScore());
            } else {
                // Если ID не указан, создаем новый вариант
                variant = variantDtoMapper.toEntity(variantDTO);
                variant.setQuestion_id(question);
            }
            // Сохраняем или обновляем вариант в базе данных
            variantRepository.save(variant);
        }
    }

    private Variant getVariantById(Long variantId) {
        return variantRepository.findById(variantId)
                .orElseThrow(() -> new NotFoundException("Variant not found with id: " + variantId));
    }

    private FormQuestion createFormQuestion(Form form, Question question) {
        return FormQuestion.builder()
                .form(form)
                .question(question)
                .createdAt(Instant.now())
                .build();
    }

}
