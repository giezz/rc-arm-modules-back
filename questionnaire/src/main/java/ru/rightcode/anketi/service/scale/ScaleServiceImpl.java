package ru.rightcode.anketi.service.scale;

import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rightcode.anketi.model.Scale;
import ru.rightcode.anketi.repository.ScaleRepository;

import java.util.List;

@Service
@WebService
@RequiredArgsConstructor
public class ScaleServiceImpl implements ScaleService {

    @Autowired
    private final ScaleRepository scaleRepository;

    @Override
    public List<Scale> getScales() {
        return scaleRepository.findAll();
    }

//    @Override
//    public List<Scale> getScaleById(Long id) {
//        return scaleRepository.findAllById(id);
//    }
//
//    @Override
//    public List<Scale> getScaleByName(String name) {
//        return scaleRepository.findAllByName(name);
//    }
}
