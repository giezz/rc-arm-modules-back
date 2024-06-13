package ru.rightcode.anketi;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.rightcode.anketi.dto.FormDto;
import ru.rightcode.anketi.mapper.mapstruct.FormMapper;
import ru.rightcode.anketi.model.*;
import ru.rightcode.anketi.service.FormService;
import ru.rightcode.anketi.service.ScaleService;

import java.util.*;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FormServiceTests {
    @Autowired private FormService formService;
    @Autowired private ScaleService scaleService;
    @Autowired private FormMapper formMapper;

    private static Long savedFormId;

    @Test
    @Order(1)
    public void createFormTests() {
        Scale scale = getScaleById(1L);
        Form form = createForm(scale);
        List<Question> questions = createQuestions("createdQuestion1", "createdQuestion2");

        FormDto createdFormDto = formService.createForm(formMapper.toDto(form, questions));

        assertNotNull(createdFormDto);
        assertEquals(2, createdFormDto.getQuestions().size());

        savedFormId = createdFormDto.getId();
    }

    @Test
    @Order(2)
    public void updateFormTests()  {
        FormDto formDto = formService.getFormDtoById(savedFormId);
        Scale scale = getScaleById(formDto.getScaleId().getId());
        Form updatedForm = createForm(scale);
        List<Question> questions = createQuestions("newQuestion1", "newQuestion2");

        FormDto createdFormDto = formService.updateForm(formDto.getId(), formMapper.toDto(updatedForm, questions));

        assertNotNull(createdFormDto);
        assertEquals(4, createdFormDto.getQuestions().size());
    }

    @Test
    @Order(3)
    public void deleteFormTests()  {
        formService.deleteForm(savedFormId);
//        formService.deleteForm(7L);
//        formService.deleteForm(8L);
    }

    private Form createForm(Scale scale){
        Form form = new Form();

        form.setName("test");
        form.setDescription("test");
        form.setScale(scale);
        return form;
    }

    private Scale getScaleById(Long id){
        return scaleService.findById(id);
    }

    private List<Question> createQuestions(String contentQuestion, String contentQuestion2){
        List<Question> questionList = new ArrayList<>();

        Question question = new Question();
        question.setContent(contentQuestion + " -- Test");
        question.setType(QuestionTypeEnum.SINGLE_CHOICE.toString());
        question.setVariants(createVariantsSingleChoice());

        Question question2 = new Question();
        question2.setContent(contentQuestion + " -- Test");
        question2.setType(QuestionTypeEnum.MULTIPLE_CHOICE.toString());
        question2.setVariants(createVariantsMultiChoice());

        questionList.add(question);
        questionList.add(question2);
        return questionList;
    }

    private Set<Variant> createVariantsSingleChoice(){
        Set<Variant> variantList = new LinkedHashSet<>();

        Variant variant  = new Variant();
        variant.setContent("Variant 1 -- Single Choice");
        variant.setScore(2.00);

        Variant variant2  = new Variant();
        variant2.setContent("Variant 2 -- Single Choice");
        variant2.setScore(4.00);

        variantList.add(variant);
        variantList.add(variant2);
        return variantList;
    }

    private Set<Variant> createVariantsMultiChoice(){
        Set<Variant> variantList = new LinkedHashSet<>();

        Variant variant  = new Variant();
        variant.setContent("Variant 3 -- Multi Choice");
        variant.setScore(6.00);

        Variant variant2  = new Variant();
        variant2.setContent("Variant 4 -- Multi Choice");
        variant2.setScore(7.00);

        Variant variant3  = new Variant();
        variant3.setContent("Variant 5 -- Multi Choice");
        variant3.setScore(8.00);

        variantList.add(variant);
        variantList.add(variant2);
        variantList.add(variant3);
        return variantList;
    }
}
