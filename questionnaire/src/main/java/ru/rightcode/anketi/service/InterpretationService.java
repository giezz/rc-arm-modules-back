package ru.rightcode.anketi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.anketi.dto.InterpretationDto;
import ru.rightcode.anketi.exception.NotFoundException;
import ru.rightcode.anketi.model.Interpretation;
import ru.rightcode.anketi.repository.InterpretationRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class InterpretationService {
    private final InterpretationRepository interpretationRepository;
    private final ScaleService scaleService;

    public InterpretationDto getById(Long id) {
        Interpretation interpretation = interpretationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Interpretation not found with id: " + id));
        return toDto(interpretation);
    }

    public List<InterpretationDto> search(String description) {
        List<Interpretation> interpretations = interpretationRepository.findAllByScaleName(description);
        return interpretations.stream().map(this::toDto).toList();
    }

    public List<InterpretationDto> getAll() {
        List<Interpretation> interpretations = interpretationRepository.findAll();
        return interpretations.stream().map(this::toDto).toList();
    }

    @Transactional
    public InterpretationDto create(InterpretationDto interpretationDto) {
        if (interpretationDto.getId() != null || interpretationDto.getScale().getId() != null) {
            interpretationDto.setId(null);
            interpretationDto.getScale().setId(null);
        }
        interpretationDto.setScale(scaleService.addScale(interpretationDto.getScale()));
        return toDto(interpretationRepository.save(toEntity(interpretationDto)));
    }

    @Transactional
    public InterpretationDto update(Long id, InterpretationDto newInterpretationDto) {
        InterpretationDto oldInterpretationDto = getById(id);
        oldInterpretationDto.setDescription(newInterpretationDto.getDescription());
        oldInterpretationDto.setScale(scaleService.addScale(newInterpretationDto.getScale()));
        oldInterpretationDto.setMinValue(newInterpretationDto.getMinValue());
        oldInterpretationDto.setMaxValue(newInterpretationDto.getMaxValue());
        return toDto(interpretationRepository.save(toEntity(newInterpretationDto)));
    }

    @Transactional
    public void delete(Long id) {
        interpretationRepository.deleteById(id);
    }

    public Interpretation toEntity(InterpretationDto interpretationDto) {
        return Interpretation.builder()
                .id(interpretationDto.getId())
                .scale(scaleService.toEntity(interpretationDto.getScale()))
                .description(interpretationDto.getDescription())
                .minValue(interpretationDto.getMinValue())
                .maxValue(interpretationDto.getMaxValue())
                .build();
    }

    public InterpretationDto toDto(Interpretation interpretation) {
        return InterpretationDto.builder()
                .id(interpretation.getId())
                .scale(scaleService.toDto(interpretation.getScale()))
                .description(interpretation.getDescription())
                .minValue(interpretation.getMinValue())
                .maxValue(interpretation.getMaxValue())
                .build();
    }
}
