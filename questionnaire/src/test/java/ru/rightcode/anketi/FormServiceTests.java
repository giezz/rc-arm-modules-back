package ru.rightcode.anketi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.rightcode.anketi.dto.FormDto;
import ru.rightcode.anketi.dto.QuestionDto;
import ru.rightcode.anketi.dto.VariantDto;
import ru.rightcode.anketi.exception.NotFoundException;
import ru.rightcode.anketi.mapper.mapstruct.FormMapper;
import ru.rightcode.anketi.model.Form;
import ru.rightcode.anketi.model.Question;
import ru.rightcode.anketi.model.Scale;
import ru.rightcode.anketi.repository.FormRepository;
import ru.rightcode.anketi.repository.ScaleRepository;
import ru.rightcode.anketi.service.form.FormServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FormServiceTests {

    @InjectMocks
    private FormServiceImpl formService;

    @Mock
    private FormRepository formRepository;

    @Mock
    private ScaleRepository scaleRepository;

    @Mock
    private FormMapper formDtoMapper;


    // Создание анкеты без вопросов
    @Test
    public void testCreateFormOutQuestions() {
        // Создаем тестовые данные
        FormDto formDto = FormDto.builder().name("Test Form")
                .description("Test Description")
                .scaleId(1L)
                .questions(new ArrayList<>())
                .build();
        Scale scale = Scale.builder().id(1L).description("ggg").name("fff")
                .build();

        // Мокируем вызовы репозиториев и мапперов
        when(scaleRepository.findById(1L)).thenReturn(Optional.of(scale));

        Form toEntity = new Form();
        toEntity.setId(formDto.getId());
        toEntity.setName(formDto.getName());
        toEntity.setDescription(formDto.getDescription());
        toEntity.setScale(scaleRepository.findById(formDto.getScaleId()).orElse(null));

        when(formDtoMapper.toEntity(formDto)).thenReturn(toEntity);
        when(formRepository.findById(anyLong())).thenReturn(Optional.ofNullable(any(Form.class)));

        // Вызываем тестируемый метод
        FormDto result = formService.createForm(formDto);

        // Проверяем, что результат не равен null
        assertNotNull(result);

        // Проверяем, что был вызван метод save у formRepository один раз
        verify(formRepository, times(1)).save(any(Form.class));

        // Проверяем, что был вызван метод saveAll у formQuestionRepository один раз
//        verify(formQuestionRepository, times(1)).saveAll(anyList());
    }

    @Test
    public void testGetFormByName() {
        // Создаем тестовые данные
        String name = "Test Form";
        Form form1 = new Form();
        form1.setId(1L);
        Form form2 = new Form();
        form2.setId(2L);
        List<Form> forms = List.of(form1, form2);

        // Настройка поведения моков
        when(formRepository.findAllByName(name)).thenReturn(forms);
        when(formDtoMapper.toDto(any(Form.class), anyList())).thenReturn(new FormDto());

        // Вызываем тестируемый метод
        List<FormDto> result = formService.getFormByName(name);

        // Проверяем, что результат не равен null и содержит две формы
        assertNotNull(result);
        assertEquals(2, result.size());

        // Проверяем, что был вызван метод findAllByName у formRepository один раз
        verify(formRepository, times(1)).findAllByName(name);
    }

    @Test
    public void testGetFormById() {
        // Создаем тестовые данные
        Long id = 1L;
        Form form = new Form();
        form.setId(id);
        form.setName("Form1");
        form.setDescription("Description1");

        FormDto formDto = new FormDto();
        formDto.setId(form.getId());
        formDto.setName(form.getName());
        formDto.setDescription(form.getDescription());
        List<Question> questionList = new ArrayList<>();

        when(formRepository.findById(id)).thenReturn(Optional.of(form));
        when(formDtoMapper.toDto(form, questionList)).thenReturn(formDto);

        // Вызываем тестируемый метод
        FormDto result = formService.getFormById(id);

        // Проверяем, что результат не равен null и содержит правильный ID
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(form.getName(), result.getName());
        assertEquals(form.getDescription(), result.getDescription());

        // Проверяем, что был вызван метод findById у formRepository один раз
        verify(formRepository, times(1)).findById(id);
    }

    @Test
    public void testDeleteForm() {
        // Создаем тестовые данные
        Long id = 1L;

        // Вызываем тестируемый метод
        formService.deleteForm(id);

        assertThrows(NotFoundException.class, () -> formService.getFormById(id));

        // Проверяем, что был вызван метод deleteById у formRepository один раз с правильным ID
        verify(formRepository, times(1)).deleteById(id);
    }

    private List<QuestionDto> questionDtoListCreateData() {
        List<VariantDto> variantList = new ArrayList<>();
        List<QuestionDto> questionList = new ArrayList<>();
        // Quest 1 with 1 variant
        variantList.add(VariantDto.builder().content("Variant 1")
                .score(2.00)
                .build()
        );
        questionList.add(QuestionDto.builder().content("Question1")
                .variants(variantList)
                .build()
        );
        // Quest 2 with 2 variants
        variantList.add(VariantDto.builder().content("Variant 2")
                .score(4.00)
                .build()
        );
        questionList.add(QuestionDto.builder().content("Question2")
                .variants(variantList)
                .build()
        );
        // Quest 3 with 3 variants
        variantList.add(VariantDto.builder().content("Variant 3")
                .score(5.00)
                .build()
        );
        questionList.add(QuestionDto.builder().content("Question3")
                .variants(variantList)
                .build()
        );
        return questionList;
    }

}
