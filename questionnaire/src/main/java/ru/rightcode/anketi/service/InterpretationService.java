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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j(topic = "interpretation")
@Transactional
public class InterpretationService {
//    private final ScaleMapper scaleMapper;
    private final InterpretationRepository interpretationRepository;
    private final ScaleService scaleService;

    private final InterpretationMapper interpretationMapper;

    @Transactional
    public ScaleInterpretationsResponse getById(Long id) {
        List<Interpretation> interpretations  = interpretationRepository.findAllByScaleId(id);
        return interpretationMapper.toResponse(scaleService.findById(id), interpretations);
    }

    @Transactional
    public List<ScaleInterpretationsResponse> search(String name) {
        List<Interpretation> interpretations  = interpretationRepository.findAllByScaleName(name);
        return scaleService.getAllByName(name).stream().map(
                (Scale scale) -> interpretationMapper.toResponse(scale, interpretations))
                .toList();
    }

    @Transactional
    public List<ScaleInterpretationsResponse> getAll() {
        return scaleService.getAll().stream().map(
                (Scale scale) -> interpretationMapper.toResponse(
                        scale, null))
                .toList();
    }

    @Transactional
    public ScaleInterpretationsResponse create(ScaleInterpretationsResponse scaleInterpretationsResponse) {
        Scale scale = interpretationMapper.toEntity(scaleInterpretationsResponse);
        Scale savedScale = scaleService.save(scale);
        List<Interpretation> newInterpretations = List.of(scaleInterpretationsResponse.interpretations());
        for (Interpretation newInterpr : newInterpretations) {
            newInterpr.setScale(savedScale);
        }
        List<Interpretation> interpretations = interpretationRepository.saveAll(newInterpretations);
        return interpretationMapper.toResponse(savedScale, interpretations);
    }

    @Transactional
    public ScaleInterpretationsResponse update(Long id, ScaleInterpretationsResponse newScaleInterpretationsResponse) {
        Scale scale = scaleService.findById(id);
        scale.setDescription(newScaleInterpretationsResponse.description());
        scale.setName(newScaleInterpretationsResponse.name());
        Scale savedScale = scaleService.save(scale);

        List<Interpretation> newInterpretations = List.of(newScaleInterpretationsResponse.interpretations());
        List<Interpretation> oldInterpretations = savedScale.getInterpretations();

        // Map of old interpretations by their ID for quick lookup
        Map<Long, Interpretation> oldInterpretationsMap = oldInterpretations.stream()
                .collect(Collectors.toMap(Interpretation::getId, Function.identity()));

        List<Interpretation> toSave = new ArrayList<>();
        List<Interpretation> toDelete = new ArrayList<>(oldInterpretations);

        for (Interpretation newInterpr : newInterpretations) {
            if (newInterpr.getId() != null && oldInterpretationsMap.containsKey(newInterpr.getId())) {
                // Update existing interpretation
                Interpretation oldInterpr = oldInterpretationsMap.get(newInterpr.getId());
                oldInterpr.setMinValue(newInterpr.getMinValue());
                oldInterpr.setMaxValue(newInterpr.getMaxValue());
                oldInterpr.setDescription(newInterpr.getDescription());
                toSave.add(oldInterpr);
                toDelete.remove(oldInterpr);
            } else {
                // New interpretation
                newInterpr.setScale(savedScale);
                toSave.add(newInterpr);
            }
        }

        // Delete interpretations that are not in the new list
        interpretationRepository.deleteAll(toDelete);
        // Save all updated and new interpretations
        List<Interpretation> savedInterpretations = interpretationRepository.saveAll(toSave);

        return interpretationMapper.toResponse(savedScale, savedInterpretations);
    }

    @Transactional
    public void delete(Long id) {
        scaleService.delete(id);
    }
}
