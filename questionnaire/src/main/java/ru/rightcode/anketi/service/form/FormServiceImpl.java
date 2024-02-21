package ru.rightcode.anketi.service.form;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.anketi.dto.FormDto;
import ru.rightcode.anketi.dto.QuestionDto;
import ru.rightcode.anketi.dto.VariantDto;
import ru.rightcode.anketi.exception.NotFoundException;
import ru.rightcode.anketi.mapper.FormDtoMapper;
import ru.rightcode.anketi.mapper.QuestionDtoMapper;
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

    @Autowired
    private final FormDtoMapper formDtoMapper;

    private final QuestionDtoMapper questionDtoMapper;


    public List<FormDto> getAllForms() {
        List<Form> forms = formRepository.findAll();
        return forms.stream()
                .map(formDtoMapper::toDto)
                .collect(Collectors.toList());
    }


    public List<FormDto> getFormByName(String name) {
        List<Form> forms = formRepository.findAllByName(name);
        return forms.stream()
                .map(formDtoMapper::toDto)
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
        return formDtoMapper.toDto(form);
    }

    public FormDto createForm(FormDto formDTO) {
        Form form = formDtoMapper.toEntity(formDTO);
        List<FormQuestion> formQuestionList = new ArrayList<>();

        List<QuestionDto> questionDtos = formDTO.questions();
        List<Question> questionList = new ArrayList<>();
        if (questionDtos == null) {
            throw new NotFoundException("Form required List<Question>");
        }
        for (QuestionDto question : questionDtos) {
            Question q = questionConvertToEntity(question);

            questionList.add(q);
            FormQuestion formQuestion1 = FormQuestion.builder()
                    .idForm(form)
                    .idQuestion(q)
                    .createdAt(Instant.now())
                    .build();

            formQuestionList.add(formQuestion1);
        }
        Form form1 = formRepository.save(form);
        formQuestionRepository.saveAll(formQuestionList);

        return formDtoMapper.toDto(form1);
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


    public Question questionConvertToEntity(QuestionDto questionDto) {
        Long questionId = questionDto.getId();
        if (questionId != null) {
            return questionRepository.findById(questionId)
                    .orElseThrow(()->
                            new NotFoundException("Question not found with id: "+ questionId)
                    );
        }
        // TODO: toEntity уже сделаны, необходимо сохранить данные если они новые
        List<VariantDto> variantDtoList = questionDto.getVariants();
        List<Variant> variantList = new ArrayList<>();
        Question question = Question.builder()
                .content(questionDto.getContent())
                .build();
        if (variantDtoList != null) {
            for (VariantDto variantDto : variantDtoList) {
                // TODO: Необходимо сначала собрать все данные,
                //  только потом отправлять на бд с проверкой есть ли они
                variantList.add(variantConvertToEntity(variantDto, question));
            }
        }
        Question question1 = questionDtoMapper.toEntity(questionDto);
        Question q = questionRepository.save(questionDtoMapper.toEntity(questionDto));
        variantRepository.saveAll(variantList);
        return q;
    }

    public Variant variantConvertToEntity(VariantDto variantDto, Question question){
        Long variantId = variantDto.getId();
        if (variantId != null) {
            Optional<Variant> optionalVariant = variantRepository.findById(variantId);
            if (optionalVariant.isPresent()) {
                return optionalVariant.get();
            }
        }

        return Variant.builder()
                .content(variantDto.getContent())
                .score(variantDto.getScore())
                .question_id(question)
                .build();
    }

    public VariantDto variantConvertToDto(Variant variant){
        Optional<Variant> variant1 = variantRepository.findById(variant.getId());
        if (variant1.isPresent()){
            Variant object = variant1.get();
            return VariantDto.builder()
                    .id(object.getId())
                    .content(object.getContent())
                    .score(object.getScore())
                    .build();
        }else{
            Variant newVariant = variantRepository.save(variant);
            return VariantDto.builder()
                    .id(newVariant.getId())
                    .content(newVariant.getContent())
                    .score(newVariant.getScore())
                    .build();
        }
    }
}
