package ru.rightcode.anketi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.anketi.dto.FormDto;
import ru.rightcode.anketi.dto.QuestionDto;
import ru.rightcode.anketi.mapper.VariantDtoMapper;
import ru.rightcode.anketi.model.*;
import ru.rightcode.anketi.repository.*;
import ru.rightcode.anketi.service.form.FormServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GigaFormServiceTests {

    @Mock
    private FormRepository formRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private FormQuestionRepository formQuestionRepository;

    @Mock
    private VariantRepository variantRepository;

    @Mock
    private FormDtoMapper formDtoMapper;

    @Mock
    private QuestionDtoMapper questionDtoMapper;

    @Mock
    private VariantDtoMapper variantDtoMapper;

    @Mock
    private ScaleRepository scaleRepository;

    @InjectMocks
    private FormServiceImpl formService;

    @BeforeEach
    public void setUp() {
        // Устанавливаем ожидаемые ответы для репозиториев
        when(scaleRepository.findById(anyLong())).thenReturn(Optional.of(new Scale()));
        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(new Question()));
        when(formQuestionRepository.findById(anyLong())).thenReturn(Optional.of(new FormQuestion()));
        when(variantRepository.findById(anyLong())).thenReturn(Optional.of(new Variant()));
    }

    // Не работает, возвращает из formDtoMapper.toEntity(formDto) = null
    @Test
    @Transactional
    public void testCreateForm() {
        // Создаем экземпляр FormDto
        FormDto formDto = new FormDto();
        formDto.setName("Новая анкета");
        formDto.setDescription("Описание анкеты");
        formDto.setScaleId(1L);

        // Создаем список вопросов
        List<QuestionDto> questionDtos = new ArrayList<>();
        QuestionDto questionDto = new QuestionDto();
        questionDto.setContent("Вопрос 1");
        questionDtos.add(questionDto);

        // Добавляем вопросы в анкету
        formDto.setQuestions(questionDtos);

        Form toEntity = new Form();
        toEntity.setId(formDto.getId());
        toEntity.setName(formDto.getName());
        toEntity.setDescription(formDto.getDescription());
        toEntity.setScale(scaleRepository.findById(formDto.getScaleId()).orElse(null));

        when(formDtoMapper.toEntity(formDto)).thenReturn(toEntity);

        // Создаем анкету
        FormDto createdFormDto = formService.createForm(formDto);

        // Проверяем, что анкета была создана
        assertNotNull(createdFormDto);
        assertEquals("Новая анкета", createdFormDto.getName());
        assertEquals("Описание анкеты", createdFormDto.getDescription());
//        assertEquals(1L, createdFormDto.getScaleId());
    }
}
