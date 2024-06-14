package ru.rightcode.anketi;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.rightcode.anketi.dto.interpretation.ScaleInterpretationsResponse;
import ru.rightcode.anketi.mapper.mapstruct.InterpretationMapper;
import ru.rightcode.anketi.model.Interpretation;
import ru.rightcode.anketi.model.Scale;
import ru.rightcode.anketi.repository.InterpretationRepository;
import ru.rightcode.anketi.repository.ScaleRepository;
import ru.rightcode.anketi.service.InterpretationService;
import ru.rightcode.anketi.service.ScaleService;

import java.math.BigDecimal;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InterpretationServiceTest {

    @Autowired
    private InterpretationService interpretationService;
    @Autowired
    private InterpretationMapper interpretationMapper;
    @Autowired
    private ScaleService scaleService;

    @Autowired
    private InterpretationRepository interpretationRepository;
    @Autowired
    private ScaleRepository scaleRepository;

    private static Long createdScaleId;

    @Test
    @Order(1)
    @Transactional
    public void testCreateScaleWithInterpretations() {
        // Вызов метода create
        ScaleInterpretationsResponse scaleInterpretationsResponse = createResponse();
        ScaleInterpretationsResponse createdResponse = interpretationService.create(scaleInterpretationsResponse);

        // Сохранение id созданной шкалы для использования в следующих тестах
        createdScaleId = createdResponse.id();

        // Проверка результатов
        assertNotNull(createdResponse);
        assertNotNull(createdResponse.id());
        assertEquals("Test Scale", createdResponse.name());
        assertEquals("This is a test scale", createdResponse.description());
        assertEquals(2, createdResponse.interpretations().length);

        // Проверка, что интерпретации связаны с правильной шкалой
        List<Interpretation> savedInterpretations = interpretationRepository.findAllByScaleId(createdScaleId);
        assertEquals(2, savedInterpretations.size());
        savedInterpretations.forEach(interpr -> assertEquals(createdResponse.id(), interpr.getScale().getId()));
    }

    @Test
    @Order(2)
    @Transactional
    public void testUpdateScaleWithInterpretations() {
        assertNotNull(createdScaleId, "Scale ID должен быть установлен после теста создания");
        System.out.printf("Scale ID: %s \n", createdScaleId);
        // Сначала загружаем существующую шкалу
        ScaleInterpretationsResponse re = interpretationService.create(createResponse());
        createdScaleId = re.id();
        Scale existingScale = scaleService.findById(createdScaleId);

        // Обновляем интерпретации
        Interpretation updatedInterpretation1 = new Interpretation();
        updatedInterpretation1.setId(existingScale.getInterpretations().get(0).getId());
        updatedInterpretation1.setMinValue(new BigDecimal("0.00"));
        updatedInterpretation1.setMaxValue(new BigDecimal("5.00"));
        updatedInterpretation1.setDescription("Very Low");

        Interpretation newInterpretation = new Interpretation();
        newInterpretation.setMinValue(new BigDecimal("20.01"));
        newInterpretation.setMaxValue(new BigDecimal("30.00"));
        newInterpretation.setDescription("High");

        ScaleInterpretationsResponse updateResponse = new ScaleInterpretationsResponse(
                createdScaleId,
                "Updated Scale",
                "Updated description",
                new Interpretation[]{updatedInterpretation1, newInterpretation}
        );

        // Вызов метода update
        ScaleInterpretationsResponse updatedScaleResponse = interpretationService.update(createdScaleId, updateResponse);

        // Проверка результатов
        assertNotNull(updatedScaleResponse);
        assertEquals("Updated Scale", updatedScaleResponse.name());
        assertEquals("Updated description", updatedScaleResponse.description());
        assertEquals(2, updatedScaleResponse.interpretations().length);

        // Проверка, что интерпретации обновлены и связаны с правильной шкалой
        List<Interpretation> savedInterpretations = interpretationRepository.findAllByScaleId(createdScaleId);
        assertEquals(2, savedInterpretations.size());
        savedInterpretations.forEach(interpr -> assertEquals(updatedScaleResponse.id(), interpr.getScale().getId()));

        // Проверка, что интерпретации правильно обновлены
        Interpretation savedUpdatedInterpretation1 = savedInterpretations.stream()
                .filter(interpr -> interpr.getId().equals(updatedInterpretation1.getId()))
                .findFirst()
                .orElse(null);

        assertNotNull(savedUpdatedInterpretation1);
        assertEquals(new BigDecimal("0.00"), savedUpdatedInterpretation1.getMinValue());
        assertEquals(new BigDecimal("5.00"), savedUpdatedInterpretation1.getMaxValue());
        assertEquals("Very Low", savedUpdatedInterpretation1.getDescription());

        Interpretation savedNewInterpretation = savedInterpretations.stream()
                .filter(interpr -> interpr.getDescription().equals("High"))
                .findFirst()
                .orElse(null);

        assertNotNull(savedNewInterpretation);
        assertEquals(new BigDecimal("20.01"), savedNewInterpretation.getMinValue());
        assertEquals(new BigDecimal("30.00"), savedNewInterpretation.getMaxValue());
        assertEquals("High", savedNewInterpretation.getDescription());
    }

    @Test
    @Order(3)
    @Transactional
    public void testDeleteScaleWithInterpretations() {
        assertNotNull(createdScaleId, "Scale ID должен быть установлен после теста создания");

        // Удаление шкалы
        scaleService.delete(createdScaleId);

        // Проверка, что шкала и интерпретации удалены
        assertFalse(scaleRepository.existsById(createdScaleId));
        assertTrue(interpretationRepository.findAllByScaleId(createdScaleId).isEmpty());
    }

    private ScaleInterpretationsResponse createResponse() {
        // Создание интерпретаций
        Interpretation interpretation1 = new Interpretation();
        interpretation1.setMinValue(new BigDecimal("0.00"));
        interpretation1.setMaxValue(new BigDecimal("10.00"));
        interpretation1.setDescription("Low");

        Interpretation interpretation2 = new Interpretation();
        interpretation2.setMinValue(new BigDecimal("10.01"));
        interpretation2.setMaxValue(new BigDecimal("20.00"));
        interpretation2.setDescription("Medium");

        // Создание ScaleInterpretationsResponse
        return new ScaleInterpretationsResponse(
                null,
                "Test Scale",
                "This is a test scale",
                new Interpretation[]{interpretation1, interpretation2}
        );
    }
}
