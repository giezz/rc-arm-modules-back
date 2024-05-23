package ru.rightcode.anketi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.anketi.dto.QuestionDto;
import ru.rightcode.anketi.model.FormQuestion;
import ru.rightcode.anketi.model.Question;
import ru.rightcode.anketi.repository.FormQuestionRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j(topic = "FQService")
@Transactional
public class FormQuestionService {
    private final FormQuestionRepository formQuestionRepository;
    private final VariantService variantService;
    private final QuestionService questionService;

    public void save(FormQuestion fq){
        formQuestionRepository.save(fq);
    }

    @Transactional
    public List<FormQuestion> findByFormId(Long id){
        return formQuestionRepository.findByFormId(id);
    }

    @Transactional
    public void deleteByFormId(Long id){
        List<FormQuestion> formQuestionList = findByFormId(id);
        List<Question> questions = formQuestionList.stream().map(FormQuestion::getQuestion).toList();

        formQuestionRepository.deleteByFormId(id);
        questions.forEach(question -> {
            deleteByQuestionId(question.getId());
            variantService.deleteByQuestionId(question.getId());
            questionService.delete(question);
        });
    }

    @Transactional
    public void deleteByQuestionId(Long questionId){
        formQuestionRepository.deleteByQuestionId(questionId);
    }

    @Transactional
    public void deleteByQuestionFormId(Long formId, Long questionId){
        formQuestionRepository.deleteByQuestionFormId(formId, questionId);
    }

    @Transactional
    public void deleteOldQuestions(List<QuestionDto> newQuestionDtoList, List<Question> oldQuestionList, Long savedFormId) {
        List<Question> newQuestions = questionService.toEntityList(newQuestionDtoList);
        List<Question> ostatok = oldQuestionList.stream()
                .filter(question -> !newQuestions.contains(question) && question != null)
                .toList();
        ostatok.forEach(question -> {
            deleteByQuestionFormId(savedFormId, question.getId());
            variantService.deleteByQuestionId(question.getId());
            questionService.deleteById(question.getId());
        });
    }

    @Transactional
    public List<Question> processQuestionsAndVariants(List<QuestionDto> questionDTOs) {
        List<Question> newQuestionList = new ArrayList<>();
        // Проходимся по всем вопросам в DTO и обновляем или создаем соответствующие вопросы и варианты
        for (QuestionDto questionDTO : questionDTOs) {
            Question question;
            // Проверяем, указан ли ID вопроса
            if (questionDTO.getId() != null && questionDTO.getId() > 0) {
                // Если ID указан, получаем существующий вопрос из базы данных
                question = questionService.findById(questionDTO.getId());
                // Обновляем поля существующего вопроса
                question.setContent(questionDTO.getContent());
                question.setType(String.valueOf(questionDTO.getType()));
                question.setRequired(questionDTO.getRequired());
            } else {
                // Если ID не указан, создаем новый вопрос
                question = questionService.toEntity(questionDTO);
                newQuestionList.add(question);
            }

            Question savedQuestion = questionService.save(question);
            // Обрабатываем варианты для вопроса
            if (questionDTO.getVariants() != null) {
                variantService.processVariants(questionDTO.getVariants(), savedQuestion);
            }
        }

        return newQuestionList;
    }
}
