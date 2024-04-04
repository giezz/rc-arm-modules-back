package ru.rightcode.anketi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.anketi.dto.ScaleDto;
import ru.rightcode.anketi.mapper.mapstruct.ScaleMapper;
import ru.rightcode.anketi.model.Scale;
import ru.rightcode.anketi.repository.ScaleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ScaleService {

    private final ScaleRepository scaleRepository;
    private final ScaleMapper scaleMapper;

    public List<ScaleDto> getScales() {
        return scaleRepository.findAll().stream().map(scaleMapper::toDto).toList();
    }

    // Добавление новой шкалы
    public ScaleDto addScale(ScaleDto scaleDto) {
        Scale savedScale = scaleRepository.save(scaleMapper.toEntity(scaleDto));
        return scaleMapper.toDto(savedScale);
    }

    // Удаление шкалы
    public void deleteScale(Long id) {
        scaleRepository.deleteById(id);
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
