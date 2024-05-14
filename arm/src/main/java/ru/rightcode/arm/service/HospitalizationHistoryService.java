package ru.rightcode.arm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.rightcode.arm.dto.response.EpicrisisResponse;
import ru.rightcode.arm.dto.response.HospitalizationHistoryResponse;
import ru.rightcode.arm.exceptions.PatientNotFoundException;
import ru.rightcode.arm.repository.PatientRepository;

@RequiredArgsConstructor
@Service
public class HospitalizationHistoryService {

    private final RestTemplate restTemplate;
    private final PatientRepository patientRepository;


    public HospitalizationHistoryResponse[] getPatientHospitalizationHistory(Long patientCode) {
        return restTemplate.getForObject("/hosp-history/{patientCode}", HospitalizationHistoryResponse[].class, patientCode);
    }

    @Transactional(readOnly = true)
    public Object getEpicrisises(Long patientCode, Long hospHistoryId) {
        patientRepository.findByPatientCode(patientCode).orElseThrow(() -> new PatientNotFoundException(patientCode));
        return restTemplate.getForObject("/hosp-history/{id}/epicrisises", EpicrisisResponse[].class, hospHistoryId);
    }
}
