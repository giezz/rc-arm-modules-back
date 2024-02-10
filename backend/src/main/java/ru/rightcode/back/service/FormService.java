package ru.rightcode.back.service;

import org.springframework.stereotype.Service;
import ru.rightcode.back.model.Form;
import ru.rightcode.back.model.Scale;
import ru.rightcode.back.repository.FormRepository;
import ru.rightcode.back.repository.ScaleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FormService {
    private final FormRepository formRepository;

    private final ScaleRepository scaleRepository;

    public FormService(FormRepository formRepository, ScaleRepository scaleRepository) {
        this.formRepository = formRepository;
        this.scaleRepository = scaleRepository;
    }

    public List<Form> findByScaleId(Long id){
        Optional<Scale> scale = scaleRepository.findById(id);
        return scale.map(Scale::getForms).orElse(null);
    }

    public List<Form> findByName(String name){
        return formRepository.findAllByName(name);
    }
}
