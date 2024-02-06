package ru.rightcode.anketi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.provider.HibernateUtils;
import ru.rightcode.anketi.dto.FormDto;
import ru.rightcode.anketi.model.Answer;
import ru.rightcode.anketi.model.Question;
import ru.rightcode.anketi.model.Variant;

import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootTest
class AnketiApplicationTests {

    @Test
    void contextLoads() {

    }

    @Test
    void formQuestion() {

    }

    @Test
    void getQuestion(){
        List<Variant> variantList = new ArrayList<>();
        variantList.add(Variant.builder()
                .id(1L)
                .score(10.0)
                .content("TEST DTO BACKEDN")
                .build()
        );

        List<Question> questionList = new ArrayList<>();
        questionList.add(Question.builder()
                .id(1L)
                .content("Quest1")
                .variants(variantList)
                .build());
        System.out.println(questionList);
    }

    @Test
    void createForm() {
        List<Variant> variantList = new ArrayList<>();
        variantList.add(Variant.builder()
                        .id(1L)
                        .score(10.0)
                        .content("TEST DTO BACKEDN")
                .build()
        );

        List<Question> questionList = new ArrayList<>();
        questionList.add(Question.builder()
                .id(1L)
                .content("Quest1")
                .variants(variantList)
                .build());

        FormDto formDTO = FormDto.builder()
                .id(1L)
                .name("TestDTO")
                .description("TEST FROM DTO BACKEND")
                .questions(questionList)
                .build();


    }
}
