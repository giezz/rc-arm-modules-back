package ru.rightcode.anketi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import ru.rightcode.anketi.dto.FormDto;
import ru.rightcode.anketi.dto.QuestionDto;
import ru.rightcode.anketi.dto.VariantDto;
import ru.rightcode.anketi.exception.NotFoundException;
import ru.rightcode.anketi.model.Form;
import ru.rightcode.anketi.model.FormQuestion;
import ru.rightcode.anketi.model.Question;
import ru.rightcode.anketi.model.Scale;
import ru.rightcode.anketi.repository.*;
import ru.rightcode.anketi.service.form.FormServiceImpl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    private VariantRepository variantRepository;

    @Mock
    private FormQuestionRepository formQuestionRepository;

    @InjectMocks
    private FormServiceImpl formService;

    @Test
    public void testGetFormById() {
        // Mock data
        Form form = new Form(1L, "Form1", "Description1", new Scale());

        Mockito.when(formRepository.findById(1L)).thenReturn(Optional.of(form));

        // Call the service method
        FormDto formDto = formService.getFormById(1L);

        // Verify that the findById() method was called once on formRepository with argument 1L
        Mockito.verify(formRepository, Mockito.times(1)).findById(1L);

        // Assertions
        assertEquals(form.getId(), formDto.getId());
        assertEquals(form.getName(), formDto.getName());
        assertEquals(form.getDescription(), formDto.getDescription());
    }

    @Test
    public void testGetFormById_NotFound() {
        // Mock data
        Mockito.when(formRepository.findById(1L)).thenReturn(Optional.empty());

        // Assertions
        assertThrows(NotFoundException.class, () -> formService.getFormById(1L));

        // Verify that the findById() method was called once on formRepository with argument 1L
        Mockito.verify(formRepository, Mockito.times(1)).findById(1L);
    }

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

        Mockito.verify(formRepository, Mockito.times(1)).findAll();
        // Проверка результатов
        // напишите проверки, чтобы убедиться, что результат соответствует ожидаемому
        // например, проверьте, что результат содержит ожидаемое количество элементов
        assertEquals("Ожидается определенное количество форм", forms.size(), result.size());
    }



    @Test
    public void testCreateForm() {
        // Создание данных для теста
        List<Form> forms = createFormData();
        List<QuestionDto> questions = createQuestions();
        Form form = forms.get(0); // Берем первую форму из списка

        // Создаем объект formDto на основе данных form и questions
        FormDto formDto = FormDto.builder()
                .id(form.getId())
                .name(form.getName())
                .description(form.getDescription())
                .scaleId(form.getScale().getId())
                .questions(questions)
                .build();

        // Мокируем вызовы репозиториев
        Mockito.when(formRepository.save(Mockito.any(Form.class))).thenReturn(form);
        Mockito.when(scaleRepository.findById(1L)).thenReturn(Optional.of(form.getScale()));
        Mockito.when(questionRepository.existsById(Mockito.anyLong())).thenReturn(true);

        // Вызов сервисного метода
        Form formId = formService.createForm(formDto);

        // Проверка вызова методов репозиториев
        // проверяет, был ли вызван метод saveAll один раз с аргументом любого списка на объекте formQuestionRepository
        Mockito.verify(formQuestionRepository, Mockito.times(1)).saveAll(Mockito.anyList());
        // проверяет, был ли вызван метод save один раз с аргументом любого объекта Form на объекте formRepository
        Mockito.verify(formRepository, Mockito.times(1)).save(Mockito.any(Form.class));

        // Другие проверки, если необходимо
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

    private List<QuestionDto> createQuestions() {
        List<VariantDto> variantList = new ArrayList<>();
        List<QuestionDto> questionList = new ArrayList<>();
        // Quest 1 with 1 variant
        variantList.add(VariantDto.builder().id(1L).content("Variant 1")
                .score(2.00)
                .build()
        );
        questionList.add(QuestionDto.builder().id(1L).content("Question1")
                .variants(variantList)
                .build()
        );
        // Quest 2 with 2 variants
        variantList.add(VariantDto.builder().id(2L).content("Variant 2")
                .score(4.00)
                .build()
        );
        questionList.add(QuestionDto.builder().id(2L).content("Question2")
                .variants(variantList)
                .build()
        );
        // Quest 3 with 3 variants
        variantList.add(VariantDto.builder().id(3L).content("Variant 3")
                .score(5.00)
                .build()
        );
        questionList.add(QuestionDto.builder().id(3L).content("Question3")
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
}
