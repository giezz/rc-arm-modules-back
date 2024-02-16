package ru.rightcode.anketi.service.form;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rightcode.anketi.dto.FormDto;
import ru.rightcode.anketi.exception.NotFoundException;
import ru.rightcode.anketi.model.Form;
import ru.rightcode.anketi.model.FormQuestion;
import ru.rightcode.anketi.model.Question;
import ru.rightcode.anketi.model.Scale;
import ru.rightcode.anketi.repository.FormQuestionRepository;
import ru.rightcode.anketi.repository.FormRepository;
import ru.rightcode.anketi.repository.QuestionRepository;
import ru.rightcode.anketi.repository.ScaleRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FormServiceImpl{

    @Autowired
    private final FormRepository formRepository;

    @Autowired
    private final ScaleRepository scaleRepository;

    @Autowired
    private final QuestionRepository questionRepository;

    @Autowired
    private final FormQuestionRepository formQuestionRepository;


    public List<FormDto> getAllForms() {
        List<Form> forms = formRepository.findAll();
        return forms.stream()
                .map((Form form) -> convertToDTO(form, null))
                .collect(Collectors.toList());
    }


    public List<FormDto> getFormByName(String name) {
        List<Form> forms = formRepository.findAllByName(name);
        return forms.stream()
                .map((Form form) -> convertToDTO(form, null))
                .collect(Collectors.toList());
    }


    public FormDto getFormById(Long id) {
        Form form = formRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Form not found with id: " + id));
        List<FormQuestion> formQuestionList = formQuestionRepository.findFormQuestionsByIdForm(form);
        List<Question> questions = new ArrayList<>();
        for (FormQuestion fq : formQuestionList){
            if (fq.getIdForm().getId().equals(form.getId())){
                questions.add(fq.getIdQuestion());
            }
        }
        return convertToDTO(form, questions);
    }


    public List<Question> getQuestionsByForm(FormDto formDTO) {
        Scale scale = validateScaleId(formDTO.getScaleId());
        Form form = convertToEntity(formDTO, scale);
        List<FormQuestion> formQuestion =
                formQuestionRepository.findFormQuestionsByIdForm(form);
        List<Question> questionList = new ArrayList<>();
        for (FormQuestion fq : formQuestion) {
            questionList.add(fq.getIdQuestion());
        }
        return questionList;
    }



    public List<FormQuestion> createForm(FormDto formDTO) {
        Scale scale = validateScaleId(formDTO.getScaleId());
        Form form = convertToEntity(formDTO, scale);
        List<FormQuestion> formQuestionList = new ArrayList<>();

        List<Question> questions = formDTO.getQuestions();
        if (questions == null) {
            throw new NotFoundException("Form required List<Question>");
        }
        for (Question question : questions) {
            if (!questionRepository.existsById(question.getId())) {
                questionRepository.save(question);
            }
            FormQuestion formQuestion1 = FormQuestion.builder()
                    .idForm(form)
                    .idQuestion(question)
                    .createdAt(Instant.now())
                    .build();

            formQuestionList.add(formQuestion1);
        }
        formQuestionRepository.saveAll(formQuestionList);
        formRepository.save(form);

        return formQuestionList;
    }


    public FormDto updateForm(FormDto formDTO) {
        Scale scale = validateScaleId(formDTO.getScaleId());

        Form existingForm = formRepository.findById(formDTO.getId())
                .orElseThrow(() -> new NotFoundException("Form not found with id: " + formDTO.getId()));

        // Update fields
        existingForm.setName(formDTO.getName());
        existingForm.setDescription(formDTO.getDescription());
        existingForm.setScale(scale);

        Form updatedForm = formRepository.save(existingForm);
        return convertToDTO(updatedForm, null);
    }


    public void deleteForm(Long id) {
        formRepository.deleteById(id);
    }

    private Scale validateScaleId(Long scaleId) {
        if (scaleId == null) {
            return null;
        }

        return scaleRepository.findById(scaleId)
                .orElseThrow(() -> new NotFoundException("Scale not found with id: " + scaleId));
    }


    private FormDto convertToDTO(Form form, List<Question> questions) {
        FormDto formDTO = FormDto.builder()
                .id(form.getId())
                .name(form.getName())
                .description(form.getDescription())
                .scaleId(form.getScale() != null ? form.getScale().getId() : null)
                .questions(questions)
                .build();
        return formDTO;
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
