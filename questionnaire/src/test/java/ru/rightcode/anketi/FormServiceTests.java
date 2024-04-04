package ru.rightcode.anketi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.rightcode.anketi.dto.FormDto;
import ru.rightcode.anketi.dto.QuestionDto;
import ru.rightcode.anketi.dto.ScaleDto;
import ru.rightcode.anketi.dto.VariantDto;
import ru.rightcode.anketi.exception.NotFoundException;
import ru.rightcode.anketi.mapper.mapstruct.FormMapper;
import ru.rightcode.anketi.mapper.mapstruct.ScaleMapper;
import ru.rightcode.anketi.mapper.mapstruct.VariantMapper;
import ru.rightcode.anketi.model.*;
import ru.rightcode.anketi.repository.FormQuestionRepository;
import ru.rightcode.anketi.repository.FormRepository;
import ru.rightcode.anketi.repository.ScaleRepository;
import ru.rightcode.anketi.service.FormService;
import ru.rightcode.anketi.service.QuestionService;
import ru.rightcode.anketi.service.VariantService;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FormServiceTests {

    @InjectMocks
    private FormService formService;

    @Mock
    private QuestionService questionService;

    @Mock
    private FormRepository formRepository;

    @Mock
    private FormQuestionRepository formQuestionRepository;
    @Mock
    private VariantService variantService;

    @Mock
    private ScaleMapper scaleMapper;

    @Mock
    private VariantMapper variantMapper;

    @Mock
    private ScaleRepository scaleRepository;

    @Mock
    private FormMapper formDtoMapper;


    // Создание анкеты без вопросов
    @Test
    public void testCreateFormOutQuestions() {
        ScaleDto scaleDto = scaleDtoCreateData();
        Scale scale = Scale.builder().id(1L).description("ggg").name("fff").build();
        // Создаем тестовые данные
        FormDto formDto = formDtoWithoutIdCreateData(scaleDto, new ArrayList<>());

        // Мокируем вызовы репозиториев и мапперов
        Form saveFormToEntity = new Form(formDto.getId(), formDto.getName(),
                formDto.getDescription(), scale);

        Form savedFormResultMock = new Form(formDto.getId(), formDto.getName(),
                formDto.getDescription(), scale);

        savedFormResultMock.setId(1L);
        FormDto formDtoMockReturn = new FormDto(formDto.getId(), formDto.getName(),
                formDto.getDescription(), formDto.getScaleId(), formDto.getQuestions());
        formDtoMockReturn.setId(savedFormResultMock.getId());

        // Мокируем вызовы репозиториев и мапперов
        when(formRepository.findById(anyLong())).thenReturn(Optional.of(saveFormToEntity));
        when(formRepository.save(eq(saveFormToEntity))).thenReturn(savedFormResultMock);
        when(formDtoMapper.toDto(eq(savedFormResultMock), any())).thenReturn(formDtoMockReturn);

        // Вызываем тестируемый метод
        FormDto result = formService.createForm(formDto);

        // Проверяем, что результат не равен null
        assertNotNull(result);
        System.out.printf(result.getId().toString());
        System.out.printf(result.getName());

        // Проверяем, что был вызван метод save у formRepository один раз
        verify(formRepository, times(1)).save(any(Form.class));
    }

    // Создание анкеты вопросами
    @Test
    public void testINCreateFormQuestions() {
        ScaleDto scaleDto = scaleDtoCreateData();
        Scale scale = Scale.builder().id(1L).description("ggg").name("fff").build();
        List<QuestionDto> questionDtoListCreateData = questionDtoListCreateData();
        // Создаем тестовые данные
        FormDto formDto = formDtoWithoutIdCreateData(scaleDto, questionDtoListCreateData);

        // Мокируем вызовы репозиториев и мапперов
        Form saveFormToEntity = new Form(formDto.getId(), formDto.getName(),
                formDto.getDescription(), scale);

        Form savedFormResultMock = new Form(formDto.getId(), formDto.getName(),
                formDto.getDescription(), scale);

        savedFormResultMock.setId(1L);
        FormDto formDtoMockReturn = new FormDto(formDto.getId(), formDto.getName(),
                formDto.getDescription(), formDto.getScaleId(), formDto.getQuestions());
        formDtoMockReturn.setId(savedFormResultMock.getId());

        // Мокируем вызовы репозиториев и мапперов
        when(formRepository.findById(anyLong())).thenReturn(Optional.of(saveFormToEntity));
        when(formDtoMapper.toEntity(eq(formDto))).thenReturn(saveFormToEntity);
        when(formDtoMapper.toDto(eq(savedFormResultMock), any())).thenReturn(formDtoMockReturn);

        // Вызываем тестируемый метод
        FormDto result = formService.createForm(formDto);

        // Проверяем, что результат не равен null
        assertNotNull(result);
        System.out.println(result.getId().toString());
        System.out.println(result.getName());
        formDto.getQuestions().forEach(questionDto1 -> {
            System.out.println(questionDto1.getContent());
        });

        // Проверяем, что был вызван метод save у formRepository один раз
        verify(formRepository, times(1)).save(any(Form.class));
        verify(questionService, times(formDto.getQuestions().size())).save(any());
        verify(formQuestionRepository, times(formDto.getQuestions().size())).save(any());
    }

    // Изменение анкеты
    @Test
    public void testSaveCreateFormQuestions() {
        ScaleDto scaleDto = scaleDtoCreateData();
        Scale scale = Scale.builder().id(1L).description("ggg").name("fff").build();
        List<QuestionDto> questionDtoListCreateData = questionDtoListCreateData();
        // Создаем тестовые данные
        FormDto formDto = formDtoWithoutIdCreateData(scaleDto, questionDtoListCreateData);
        formDto.setId(1L);
        Form saveFormToEntity = new Form(formDto.getId(), formDto.getName(),
                formDto.getDescription(), scale);

        Form savedFormResultMock = new Form(formDto.getId(), formDto.getName(),
                formDto.getDescription(), scale);

        savedFormResultMock.setId(1L);
        FormDto formDtoMockReturn = new FormDto(formDto.getId(), formDto.getName(),
                formDto.getDescription(), formDto.getScaleId(), formDto.getQuestions());
        formDtoMockReturn.setId(savedFormResultMock.getId());

        // Мокируем вызовы репозиториев и мапперов
        when(formRepository.findById(anyLong())).thenReturn(Optional.of(saveFormToEntity));
        when(formDtoMapper.toEntity(eq(formDto))).thenReturn(saveFormToEntity);
        when(formRepository.save(eq(saveFormToEntity))).thenReturn(savedFormResultMock);
        when(formDtoMapper.toDto(eq(savedFormResultMock), any())).thenReturn(formDtoMockReturn);

        // Создаем тестовые данные
        Form savedForm = formDtoMapper.toEntity(formDto);
        formRepository.save(savedForm);
        savedForm.setName("Updated Form");
        savedForm.setDescription("Updated Description");
        FormDto formDtoUpdated = formDtoMapper.toDto(savedForm, new ArrayList<>());
        questionDtoListCreateData.forEach(questionDto1 -> questionDto1.setContent("Updated Content"));
        formDtoUpdated.setQuestions(questionDtoListCreateData);
        // Сохраняем измененные данные
        FormDto result = formService.createForm(formDto);

        // Проверяем, что результат не равен null
        assertNotNull(result);
        System.out.println(result.getId().toString());
        System.out.println(result.getName());
        formDto.getQuestions().forEach(questionDto1 -> System.out.println(questionDto1.getContent()));

        // Проверяем, что был вызван метод save у formRepository один раз
//        verify(formRepository, times(1)).save(any(Form.class));
        verify(questionService, times(formDto.getQuestions().size())).save(any());
        verify(formQuestionRepository, times(formDto.getQuestions().size())).save(any());
    }

    private ScaleDto scaleDtoCreateData() {
        return ScaleDto.builder().id(1L).description("ggg").name("fff").build();
    }

    private FormDto formDtoWithoutIdCreateData(ScaleDto scaleDto, List<QuestionDto> questionDtoList) {
        return FormDto.builder().name("Test Form").description("Test Description")
                .scaleId(scaleDto)
                .questions(questionDtoList)
                .build();
    }

    private List<QuestionDto> questionDtoListCreateData() {
        Set<VariantDto> variantList = new HashSet<>();
        List<QuestionDto> questionList = new ArrayList<>();
        // Quest 1 with 1 variant
        variantList.add(VariantDto.builder().content("Variant 1")
                .score(2.00)
                .build()
        );
        questionList.add(QuestionDto.builder().content("Question1")
                .variants(variantList)
                .required(false)
                .type(QuestionTypeEnum.SINGLE_CHOICE)
                .build()
        );
        // Quest 2 with 2 variants
        variantList.add(VariantDto.builder().content("Variant 2")
                .score(4.00)
                .build()
        );
        questionList.add(QuestionDto.builder().content("Question2")
                .variants(variantList)
                .required(false)
                .type(QuestionTypeEnum.SINGLE_CHOICE)
                .build()
        );
        // Quest 3 with 3 variants
        variantList.add(VariantDto.builder().content("Variant 3")
                .score(5.00)
                .build()
        );
        questionList.add(QuestionDto.builder().content("Question3")
                .variants(variantList)
                .required(false)
                .type(QuestionTypeEnum.SINGLE_CHOICE)
                .build()
        );
        return questionList;
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
        List<FormDto> result = formService.getListFormDtoByName(name);

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
        FormDto result = formService.getFormDtoById(id);

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

        assertThrows(NotFoundException.class, () -> formService.getFormDtoById(id));

        // Проверяем, что был вызван метод deleteById у formRepository один раз с правильным ID
        verify(formRepository, times(1)).deleteById(id);
    }

}
