package ru.rightcode.anketi.service.scale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rightcode.anketi.model.Scale;
import ru.rightcode.anketi.repository.ScaleRepository;

import java.util.List;

@Service
public class ScaleServiceImpl implements ScaleService{

    @Autowired
    private ScaleRepository scaleRepository;

    @Override
    public List<Scale> getScales() {
        return scaleRepository.findAll();
    }
}
