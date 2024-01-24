package ru.rightcode.anketi.service.form;

import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rightcode.anketi.dto.FormDto;
import ru.rightcode.anketi.exception.NotFoundException;
import ru.rightcode.anketi.model.Form;
import ru.rightcode.anketi.model.FormQuestion;
import ru.rightcode.anketi.model.Question;
import ru.rightcode.anketi.model.Scale;
import ru.rightcode.anketi.repository.FormRepository;
import ru.rightcode.anketi.repository.QuestionRepository;
import ru.rightcode.anketi.repository.ScaleRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@WebService
@RequiredArgsConstructor
public class FormServiceImpl implements FormService{

    @Autowired
    private final FormRepository formRepository;

    @Autowired
    private final ScaleRepository scaleRepository;

    @Autowired
    private final QuestionRepository questionRepository;


    @Override
    public List<FormDto> getAllForms() {
        List<Form> forms = formRepository.findAll();
        return forms.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FormDto> getFormByName(String name) {
        List<Form> forms = formRepository.findAllByName(name);
        return forms.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FormDto getFormById(Long id) {
        Form form = formRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Form not found with id: " + id));
        return convertToDTO(form);
    }

    @Override
    public List<FormQuestion> createForm(FormDto formDTO) {
        Scale scale = validateScaleId(formDTO.getScaleId());
        Form form = convertToEntity(formDTO, scale);
        List<FormQuestion> formQuestionList = new ArrayList<>();

        List<Question> questions = formDTO.getQuestions();
        if (questions == null){
            throw new NotFoundException("Form required List<Question>");
        }
        for (Question question : questions) {
            if (!questionRepository.existsById(question.getId())){
                questionRepository.save(question);
            }
            FormQuestion formQuestion1 = FormQuestion.builder()
                    .idForm(form)
                    .idQuestion(question)
                    .createdAt(Instant.now())
                    .build();

            formQuestionList.add(formQuestion1);
        }

        formRepository.save(form);

        return formQuestionList;
    }

    @Override
    public FormDto updateForm(FormDto formDTO) {
        Scale scale = validateScaleId(formDTO.getScaleId());

        Form existingForm = formRepository.findById(formDTO.getId())
                .orElseThrow(() -> new NotFoundException("Form not found with id: " + formDTO.getId()));

        // Update fields
        existingForm.setName(formDTO.getName());
        existingForm.setDescription(formDTO.getDescription());
        existingForm.setScale(scale);

        Form updatedForm = formRepository.save(existingForm);
        return convertToDTO(updatedForm);
    }

    @Override
    public void deleteForm(Long id) {
        formRepository.deleteById(id);
    }

    private FormDto convertToDTO(Form form) {
        FormDto formDTO = FormDto.builder()
                .id(form.getId())
                .name(form.getName())
                .description(form.getDescription())
                .scaleId(form.getScale() != null ? form.getScale().getId() : null)
                .questions(null)
                .build();
        return formDTO;
    }

    private Scale validateScaleId(Long scaleId) {
        if (scaleId == null) {
            return null;
        }

        return scaleRepository.findById(scaleId)
                .orElseThrow(() -> new NotFoundException("Scale not found with id: " + scaleId));
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
