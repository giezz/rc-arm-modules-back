package ru.rightcode.anketi.service.form;

import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rightcode.anketi.model.Form;
import ru.rightcode.anketi.model.Patient;
import ru.rightcode.anketi.repository.FormRepository;

import java.util.List;

@Service
@WebService
@RequiredArgsConstructor
public class FormServiceImpl implements FormService{

    @Autowired
    private final FormRepository formRepository;


    @Override
    public List<Form> getForms() {
        return formRepository.findAll();
    }

    @Override
    public List<Form> getFormByName(String name) {
        return formRepository.findAllByName(name);
    }

    @Override
    public List<Form> getFormById(Long id) {
        return formRepository.findFormById(id);
    }
}
