package ru.rightcode.medcart.service.epicrisis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.medcart.model.Epicrisis;
import ru.rightcode.medcart.repository.EpicrisisRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class EpicrisisServiceImpl implements EpicrisisService {

    private final EpicrisisRepository epicrisisRepository;

    @Override
    public List<Epicrisis> getEpicrisises(Long hospHistoryId) {
        return epicrisisRepository.findAllByHospitalizationHistoryId(hospHistoryId);
    }
}
