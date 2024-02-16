package ru.rightcode.anketi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import ru.rightcode.anketi.dto.FormDto;
import ru.rightcode.anketi.model.*;
import ru.rightcode.anketi.repository.FormQuestionRepository;
import ru.rightcode.anketi.repository.FormRepository;
import ru.rightcode.anketi.repository.QuestionRepository;
import ru.rightcode.anketi.repository.ScaleRepository;
import ru.rightcode.anketi.service.form.FormServiceImpl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class FormServiceTest {

    @Mock
    private FormRepository formRepository;

    @Mock
    private ScaleRepository scaleRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private FormQuestionRepository formQuestionRepository;

    @InjectMocks
    private FormServiceImpl formService;

    @Test
    public void testGetAllForms() {
        // Создание тестовых данных
        List<Form> forms = createFormData();


        // Настройка моков и поведения репозитория
        Mockito.when(formRepository.findAll()).thenReturn(forms);

        // Вызов метода сервиса для тестирования
        List<FormDto> result = formService.getAllForms();
        for (FormDto formDto : result) {
            System.out.println("Name form's: " + formDto.getName());
        }

        // Проверка результатов
        // напишите проверки, чтобы убедиться, что результат соответствует ожидаемому
        // например, проверьте, что результат содержит ожидаемое количество элементов
        assertEquals("Ожидается определенное количество форм", 3, result.size());
    }


    @Test
    public void testCreateForm() {
        // Создание тестовых данных
        FormDto formDto = new FormDto();
        formDto.setId(1L);
        formDto.setName("Test Form");
        formDto.setDescription("Test Description");
        formDto.setScaleId(1L);
        List<Question> questions = createQuestions();
        formDto.setQuestions(questions);

        Scale scale = new Scale();
        scale.setId(1L);

        // Настройка моков и поведения репозиториев
        Mockito.when(scaleRepository.findById(1L)).thenReturn(Optional.of(scale));
        Mockito.when(questionRepository.findById(1L)).thenReturn(Optional.of(questions.get(0)));
        Mockito.when(formRepository.save(Mockito.any(Form.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Вызов метода сервиса для тестирования
        List<FormQuestion> result = formService.createForm(formDto);
        for (FormQuestion formQuestion : result) {
            System.out.println(formQuestion.getIdForm() + " == " + formQuestion.getIdQuestion());
        }
        // Проверка результатов
        // напишите проверки, чтобы убедиться, что результат соответствует ожидаемому
        // например, проверьте, что результат не равен null и содержит ожидаемое количество элементов
        assertNotNull(result);
        assertEquals("Ожидается определенное количество FormQuestion", 3, result.size());
        Form form = convertToEntity(formDto, scale);
        assertEquals("Assert True",
                createFormQuestions(form, questions).get(0).getIdForm().getName(),
                result.get(0).getIdForm().getName());
    }

    private List<Form> createFormData() {
        Scale scale1 = Scale.builder().id(1L).name("Scale 1")
                .description("fdgdf")
                .build();
        Scale scale2 = Scale.builder().id(2L).name("Scale 2")
                .description("fdgdf")
                .build();
        Form form1 = Form.builder().id(1L).name("Form1").description("gfg")
                .scale(scale1)
                .build();
        Form form2 = Form.builder().id(2L).name("Form2").description("gfg")
                .scale(scale1)
                .build();
        Form form3 = Form.builder().id(3L).name("Form3").description("gfg")
                .scale(scale2)
                .build();
        List<Form> forms = new ArrayList<>();
        forms.add(form1);
        forms.add(form2);
        forms.add(form3);
        return forms;
    }

    private List<Question> createQuestions() {
        List<Variant> variantList = new ArrayList<>();
        List<Question> questionList = new ArrayList<>();
        // Quest 1 with 1 variant
        variantList.add(Variant.builder().id(1L).content("Variant 1")
                .score(2.00)
                .build()
        );
        questionList.add(Question.builder().id(1L).content("Question1")
                .variants(variantList)
                .build()
        );
        // Quest 2 with 2 variants
        variantList.add(Variant.builder().id(2L).content("Variant 2")
                .score(4.00)
                .build()
        );
        questionList.add(Question.builder().id(2L).content("Question2")
                .variants(variantList)
                .build()
        );
        // Quest 3 with 3 variants
        variantList.add(Variant.builder().id(3L).content("Variant 3")
                .score(5.00)
                .build()
        );
        questionList.add(Question.builder().id(3L).content("Question3")
                .variants(variantList)
                .build()
        );
        return questionList;
    }

    private List<FormQuestion> createFormQuestions(Form form, List<Question> questionList){
        List<FormQuestion> formQuestionList = new ArrayList<>();
        for (Question q: questionList){
            formQuestionList.add(
                    FormQuestion.builder()
                            .idForm(form)
                            .idQuestion(q)
                            .createdAt(Instant.now())
                            .build()
            );
        }
        return formQuestionList;
    }

    private Form convertToEntity(FormDto formDTO, Scale scale) {
        Form form = new Form();
        form.setId(formDTO.getId());
        form.setName(formDTO.getName());
        form.setDescription(formDTO.getDescription());

        if (scale != null) {
            form.setScale(scale);
        }

        return form;
    }
}
