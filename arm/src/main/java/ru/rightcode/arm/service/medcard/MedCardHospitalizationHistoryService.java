package ru.rightcode.arm.service.medcard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.rightcode.arm.dto.response.EpicrisisResponse;
import ru.rightcode.arm.dto.response.HospitalizationHistoryResponse;

@Service
public class MedCardHospitalizationHistoryService {

    private final RestTemplate restTemplate;
    private final MedCardRequestHandlerService requestHandlerService;

    @Autowired
    public MedCardHospitalizationHistoryService(@Qualifier("medCardRestTemplate") RestTemplate restTemplate,
                                               MedCardRequestHandlerService requestHandlerService) {
        this.restTemplate = restTemplate;
        this.requestHandlerService = requestHandlerService;
    }

    public ResponseEntity<?> getPatientHospitalizationHistory(Long patientCode) {
        return requestHandlerService.executeRequest(() -> restTemplate.getForEntity(
                "/hosp-history/{patientCode}",
                HospitalizationHistoryResponse[].class,
                patientCode
        ));
    }

    public ResponseEntity<?> getEpicrisises(Long hospHistoryId) {
        return requestHandlerService.executeRequest(() -> restTemplate.getForEntity(
                "/hosp-history/{id}/epicrisises",
                EpicrisisResponse[].class,
                hospHistoryId
        ));
    }
}
