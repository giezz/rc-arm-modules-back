package ru.rightcode.medcart.service.hospitalization;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.medcart.model.HospitalizationHistory;
import ru.rightcode.medcart.repository.HospitalizationHistoryRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class HospitalizationHistoryServiceImpl implements HospitalizationHistoryService {

    private final HospitalizationHistoryRepository hospitalizationHistoryRepository;

    @Override
    public List<HospitalizationHistory> getHistory(Long patientCode) {
        return hospitalizationHistoryRepository.findAllByPatientPatientCode(patientCode);
    }
}
