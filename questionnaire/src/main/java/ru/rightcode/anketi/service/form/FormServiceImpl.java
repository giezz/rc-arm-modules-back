package ru.rightcode.anketi.service.form;

import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rightcode.anketi.DTO.FormDTO;
import ru.rightcode.anketi.exception.NotFoundException;
import ru.rightcode.anketi.model.Form;
import ru.rightcode.anketi.model.Scale;
import ru.rightcode.anketi.repository.FormRepository;
import ru.rightcode.anketi.repository.ScaleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@WebService
@RequiredArgsConstructor
public class FormServiceImpl implements FormService{

    @Autowired
    private final FormRepository formRepository;

    @Autowired
    private final ScaleRepository scaleRepository;


    @Override
    public List<FormDTO> getAllForms() {
        List<Form> forms = formRepository.findAll();
        return forms.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FormDTO> getFormByName(String name) {
        List<Form> forms = formRepository.findAllByName(name);
        return forms.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FormDTO getFormById(Long id) {
        Form form = formRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Form not found with id: " + id));
        return convertToDTO(form);
    }

    @Override
    public FormDTO createForm(FormDTO formDTO) {
        Scale scale = validateScaleId(formDTO.getScaleId());
        Form form = convertToEntity(formDTO, scale);
        Form savedForm = formRepository.save(form);
        return convertToDTO(savedForm);
    }

    @Override
    public FormDTO updateForm(FormDTO formDTO) {
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

    private FormDTO convertToDTO(Form form) {
        FormDTO formDTO = new FormDTO();
        formDTO.setId(form.getId());
        formDTO.setName(form.getName());
        formDTO.setDescription(form.getDescription());
        formDTO.setScaleId(form.getScale() != null ? form.getScale().getId() : null);
        return formDTO;
    }

    private Scale validateScaleId(Long scaleId) {
        if (scaleId == null) {
            return null;
        }

        return scaleRepository.findById(scaleId)
                .orElseThrow(() -> new NotFoundException("Scale not found with id: " + scaleId));
    }

    private Form convertToEntity(FormDTO formDTO, Scale scale) {
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
