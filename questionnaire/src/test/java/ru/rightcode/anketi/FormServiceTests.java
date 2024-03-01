package ru.rightcode.anketi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.anketi.dto.FormDto;
import ru.rightcode.anketi.dto.QuestionDto;
import ru.rightcode.anketi.dto.VariantDto;
import ru.rightcode.anketi.mapper.FormDtoMapper;
import ru.rightcode.anketi.mapper.QuestionDtoMapper;
import ru.rightcode.anketi.model.Form;
import ru.rightcode.anketi.model.Question;
import ru.rightcode.anketi.model.Scale;
import ru.rightcode.anketi.repository.*;
import ru.rightcode.anketi.service.form.FormServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FormServiceTests {

    @InjectMocks
    private FormServiceImpl formService;

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
    private ScaleRepository scaleRepository;

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
        scaleRepository.save(scale);
        // Мокируем вызовы репозиториев и мапперов
        when(scaleRepository.findById(1L)).thenReturn(Optional.of(scale));

        Form toEntity = new Form();
        toEntity.setId(formDto.getId());
        toEntity.setName(formDto.getName());
        toEntity.setDescription(formDto.getDescription());
        toEntity.setScale(scaleRepository.findById(formDto.getScaleId()).orElse(null));

        when(formDtoMapper.toEntity(formDto)).thenReturn(toEntity);
//        when(questionRepository.findById(anyLong())).thenReturn(Optional.ofNullable(any(Question.class)));
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

    // Не работает, возвращает из formDtoMapper.toEntity(formDto) = null
    // Создание анкеты со списком id вопросов
    @Test
    @Transactional
    public void testCreateFormIdsQuestions() {
        // Создаем тестовые данные
        // questionRepository.saveAll(questionList.stream().map(questionDtoMapper::toEntity).collect(Collectors.toList()));
        List<QuestionDto> idsQuestionDtos = idsQuestionDtoListCreateData();
        List<Long> idsQuest = idsQuestionDtos.stream().map(QuestionDto::getId).toList();
        FormDto formDto = FormDto.builder().name("Test Form")
                .description("Test Description")
                .scaleId(1L)
                .questions(idsQuestionDtos)
                .build();

        Scale scale = Scale.builder().id(1L).description("ggg").name("fff")
                .build();
        scaleRepository.save(scale);
        // Мокируем вызовы репозиториев и мапперов
        when(scaleRepository.findById(1L)).thenReturn(Optional.of(scale));

        Form toEntity = new Form();
        toEntity.setId(formDto.getId());
        toEntity.setName(formDto.getName());
        toEntity.setDescription(formDto.getDescription());
        toEntity.setScale(scale);

        Optional<Scale> scale1 = scaleRepository.findById(scale.getId());
        Optional<Question> question = questionRepository.findById(1L);
        assertTrue(scale1.isPresent());
        assertTrue(question.isPresent());
//        when(questionRepository.findById(anyLong())).thenReturn(Optional.ofNullable(any(Question.class)));
        when(formDtoMapper.toEntity(formDto)).thenReturn(toEntity);
        when(questionRepository.findById(anyLong())).thenReturn(Optional.ofNullable(any(Question.class)));

//        when(questionRepository.findById(anyLong())).thenReturn(Optional.ofNullable(any(Question.class)));
        when(formRepository.findById(anyLong())).thenReturn(Optional.ofNullable(any(Form.class)));

        // Вызываем тестируемый метод
        FormDto result = formService.createForm(formDto);

        // Проверяем, что результат не равен null
        assertNotNull(result);

        // Проверяем, что был вызван метод save у formRepository один раз
        verify(formRepository, times(1)).save(any(Form.class));

        // Проверяем, что был вызван метод saveAll у formQuestionRepository один раз
        verify(formQuestionRepository, times(1)).saveAll(anyList());
    }

    private FormDto formDtoCreateData(Long scaleId, List<QuestionDto> questionDtos){
        return FormDto.builder().name("Test Form")
                .description("Test Description")
                .scaleId(scaleId)
                .questions(questionDtos)
                .build();
    }

    private List<QuestionDto> questionDtoListCreateData() {
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

    private List<QuestionDto> idsQuestionDtoListCreateData(){
        List<QuestionDto> questionDtos = new ArrayList<>();
        for (QuestionDto questionDto : questionDtoListCreateData()) {
            questionDtos.add(QuestionDto.builder()
                    .id(questionDto.getId())
                    .build());
        }
        return questionDtos;
    }
}
