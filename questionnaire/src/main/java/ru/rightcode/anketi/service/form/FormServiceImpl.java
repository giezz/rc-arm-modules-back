package ru.rightcode.anketi.service.form;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.anketi.dto.FormDto;
import ru.rightcode.anketi.dto.QuestionDto;
import ru.rightcode.anketi.dto.VariantDto;
import ru.rightcode.anketi.exception.NotFoundException;
import ru.rightcode.anketi.model.*;
import ru.rightcode.anketi.repository.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class FormServiceImpl{

    @Autowired
    private final FormRepository formRepository;

    @Autowired
    private final ScaleRepository scaleRepository;

    @Autowired
    private final QuestionRepository questionRepository;

    @Autowired
    private final FormQuestionRepository formQuestionRepository;

    @Autowired
    private final VariantRepository variantRepository;


    public List<FormDto> getAllForms() {
        List<Form> forms = formRepository.findAll();
        return forms.stream()
                .map((Form form) -> formConvertToDTO(form, null))
                .collect(Collectors.toList());
    }


    public List<FormDto> getFormByName(String name) {
        List<Form> forms = formRepository.findAllByName(name);
        return forms.stream()
                .map((Form form) -> formConvertToDTO(form, null))
                .collect(Collectors.toList());
    }


    public FormDto getFormById(Long id) {
        Form form = formRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Form not found with id: " + id));
        List<FormQuestion> formQuestionList = formQuestionRepository.findByIdForm(form);
        List<Question> questions = new ArrayList<>();
        for (FormQuestion fq : formQuestionList){
            if (fq.getIdForm().getId().equals(form.getId())){
                questions.add(fq.getIdQuestion());
            }
        }
        return formConvertToDTO(form, questions);
    }


    public List<Question> getQuestionsByForm(FormDto formDTO) {
        Scale scale = validateScaleId(formDTO.getScaleId());
        Form form = formConvertToEntity(formDTO, scale);
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
        Form form = formConvertToEntity(formDTO, scale);
        List<FormQuestion> formQuestionList = new ArrayList<>();

        List<QuestionDto> questions = formDTO.getQuestions();
        if (questions == null) {
            throw new NotFoundException("Form required List<Question>");
        }
        for (QuestionDto question : questions) {
            if (question.getId() != null && !questionRepository.existsById(question.getId())) {

                continue;
            }
            Question q = questionConvertToEntity(question);
            FormQuestion formQuestion1 = FormQuestion.builder()
                    .idForm(form)
                    .idQuestion(q)
                    .createdAt(Instant.now())
                    .build();

            formQuestionList.add(formQuestion1);
        }
        formQuestionRepository.saveAll(formQuestionList);
        formRepository.save(form);

        return formQuestionList;
    }


    /*public FormDto updateForm(FormDto formDTO) {
        Scale scale = validateScaleId(formDTO.getScaleId());

        Form existingForm = formRepository.findById(formDTO.getId())
                .orElseThrow(() -> new NotFoundException("Form not found with id: " + formDTO.getId()));

        // Update fields
        existingForm.setName(formDTO.getName());
        existingForm.setDescription(formDTO.getDescription());
        existingForm.setScale(scale);

        Form updatedForm = formRepository.save(existingForm);
        return formConvertToDTO(updatedForm, null);
    }*/


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


    public FormDto formConvertToDTO(Form form, List<Question> questions) {
        List<QuestionDto> questionDtos = new ArrayList<>();
        if (questions != null && !questions.isEmpty()){
            for (Question q : questions){
                questionDtos.add(questionConvertToDto(q));
            }
        }
        return FormDto.builder()
                .name(form.getName())
                .description(form.getDescription())
                .scaleId(form.getScale() != null ? form.getScale().getId() : null)
                .questions(!questionDtos.isEmpty() ? questionDtos : null)
                .build();
    }

    public Form formConvertToEntity(FormDto formDTO, Scale scale) {
        Form form = new Form();
        form.setName(formDTO.getName());
        form.setDescription(formDTO.getDescription());
        if (scale != null) {
            form.setScale(scale);
        }
        return form;
    }


    public QuestionDto questionConvertToDto(Question question){
        return QuestionDto.builder()
                .id(question.getId())
                .content(question.getContent())
                .build();
    }

    public Question questionConvertToEntity(QuestionDto questionDto) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionDto.getId());
        if (optionalQuestion.isPresent()) {
            return optionalQuestion.get();
        } else {
            List<VariantDto> variantDtoList = questionDto.getVariants();
            List<Variant> variantList = new ArrayList<>();
            if (variantDtoList != null) {
                for (VariantDto variantDto : variantDtoList) {
                    variantList.add(variantConvertToEntity(variantDto));
                }
            }
            Question question = Question.builder()
                    .content(questionDto.getContent())
                    .variants(variantList)
                    .build();
            return questionRepository.save(question);
        }
    }

    public Variant variantConvertToEntity(VariantDto variantDto){
        Optional<Variant> optionalVariant = variantRepository.findById(variantDto.getId());
        if (optionalVariant.isPresent()) {
            return optionalVariant.get();
        } else {
            Variant variant = Variant.builder()
                    .content(variantDto.getContent())
                    .score(variantDto.getScore())
                    .build();
            return variantRepository.save(variant);
        }
    }
}
