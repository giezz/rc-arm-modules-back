package ru.rightcode.medcart.service.epicrisis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rightcode.medcart.model.Epicrisis;
import ru.rightcode.medcart.model.Patient;
import ru.rightcode.medcart.repository.EpicrisisRepository;

import java.util.List;

@Service
public class EpicrisisServiceImpl implements EpicrisisService {

    @Autowired
    private EpicrisisRepository epicrisisRepository;

    @Override
    public List<Epicrisis> getEpicrisises(Long patientCode) {
        return epicrisisRepository.findAllByPatientPatientCode(patientCode);
    }
}
