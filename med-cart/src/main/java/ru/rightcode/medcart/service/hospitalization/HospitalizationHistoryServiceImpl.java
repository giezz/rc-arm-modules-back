package ru.rightcode.medcart.service.hospitalization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rightcode.medcart.model.HospitalizationHistory;
import ru.rightcode.medcart.repository.HospitalizationHistoryRepository;

import java.util.List;

@Service
public class HospitalizationHistoryServiceImpl implements HospitalizationHistoryService {

    @Autowired
    private HospitalizationHistoryRepository hospitalizationHistoryRepository;

    @Override
    public List<HospitalizationHistory> getHistory(Long patientCode) {
        return hospitalizationHistoryRepository.findAllByPatientPatientCode(patientCode);
    }
}
