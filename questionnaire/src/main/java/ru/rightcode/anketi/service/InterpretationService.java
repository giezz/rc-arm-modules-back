package ru.rightcode.anketi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.anketi.dto.InterpretationDto;
import ru.rightcode.anketi.dto.interpretation.ScaleInterpretationsResponse;
import ru.rightcode.anketi.mapper.mapstruct.InterpretationMapper;
import ru.rightcode.anketi.mapper.mapstruct.ScaleMapper;
import ru.rightcode.anketi.model.Interpretation;
import ru.rightcode.anketi.model.Scale;
import ru.rightcode.anketi.repository.InterpretationRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j(topic = "interpretation")
@Transactional
public class InterpretationService {
//    private final ScaleMapper scaleMapper;
//    private final InterpretationRepository interpretationRepository;
    private final ScaleService scaleService;

    private final InterpretationMapper interpretationMapper;

    @Transactional
    public ScaleInterpretationsResponse getById(Long id) {
        return interpretationMapper.toResponse(scaleService.findById(id));
    }

    public List<ScaleInterpretationsResponse> search(String description) {
        return scaleService.getAllByName(description).stream().map(interpretationMapper::toResponse).toList();
    }

    public List<ScaleInterpretationsResponse> getAll() {
        return scaleService.getAll().stream().map(interpretationMapper::toResponse).toList();
    }

    @Transactional
    public ScaleInterpretationsResponse create(ScaleInterpretationsResponse scaleInterpretationsResponse) {
        Scale scale = interpretationMapper.toEntity(scaleInterpretationsResponse);
        Scale savedScale = scaleService.save(scale);
        return interpretationMapper.toResponse(savedScale);
    }

    @Transactional
    public ScaleInterpretationsResponse update(Long id, ScaleInterpretationsResponse newScaleInterpretationsResponse) {
        Scale scale  = scaleService.findById(id);
        scale.setDescription(newScaleInterpretationsResponse.description());
        scale.setName(newScaleInterpretationsResponse.name());
        scale.setInterpretations(List.of(newScaleInterpretationsResponse.interpretations()));
        return interpretationMapper.toResponse(scaleService.save(scale));
    }

    @Transactional
    public void delete(Long id) {
        scaleService.delete(id);
    }
}
