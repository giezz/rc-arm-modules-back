package ru.rightcode.anketi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.anketi.dto.ScaleDto;
import ru.rightcode.anketi.exception.NotFoundException;
import ru.rightcode.anketi.mapper.mapstruct.ScaleMapper;
import ru.rightcode.anketi.model.Scale;
import ru.rightcode.anketi.repository.ScaleRepository;

import java.util.List;

@Service
@Slf4j(topic = "scale")
@RequiredArgsConstructor
@Transactional
public class ScaleService {

    private final ScaleRepository scaleRepository;
    private final ScaleMapper scaleMapper;

    @Transactional
    public Scale findById(Long id)  {
        return scaleRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Scale not found"));
    }

    @Transactional
    public List<Scale> getAllByName(String name)  {
        return scaleRepository.findAllByName(name);
    }

    @Transactional
    public List<Scale> getAll()  {
        return scaleRepository.findAll();
    }

    @Transactional
    public Scale save(Scale scale)  {
        return scaleRepository.save(scale);
    }

    @Transactional
    public void delete(Long id) {
        scaleRepository.deleteById(id);
    }

    public List<ScaleDto> getScales() {
        return getAll().stream().map(scaleMapper::toDto).toList();
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

    // toEntity
    public Scale toEntity(ScaleDto scaleDto){
        return scaleMapper.toEntity(scaleDto);
    }
    public ScaleDto toDto(Scale scale) {
        return scaleMapper.toDto(scale);
    }
}
