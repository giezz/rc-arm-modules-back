package ru.rightcode.anketi.service.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rightcode.anketi.model.Form;
import ru.rightcode.anketi.repository.FormRepository;

import java.util.List;

@Service
public class FormServiceImpl implements FormService{

    @Autowired
    private FormRepository formRepository;


    @Override
    public List<Form> getForms() {
        return formRepository.findAll();
    }
}
