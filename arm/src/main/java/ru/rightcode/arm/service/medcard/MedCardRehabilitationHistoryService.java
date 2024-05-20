package ru.rightcode.arm.service.medcard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.rightcode.arm.dto.request.CreateMedCardRehabRecordRequest;

@Service
public class MedCardRehabilitationHistoryService {

    private final RestTemplate restTemplate;
    private final MedCardRequestHandlerService requestHandlerService;

    @Autowired
    public MedCardRehabilitationHistoryService(@Qualifier("medCardRestTemplate") RestTemplate restTemplate,
                                               MedCardRequestHandlerService requestHandlerService) {
        this.restTemplate = restTemplate;
        this.requestHandlerService = requestHandlerService;
    }

    public ResponseEntity<?> getPatientRehabHistory(Long patientCode) {
        return requestHandlerService.executeRequest(() -> restTemplate.getForEntity(
                "/rehab-history/{patientCode}",
                String.class,
                patientCode
        ));
    }

    public void createRehabHistoryRecord(CreateMedCardRehabRecordRequest request) {
        requestHandlerService.executeRequest(() -> restTemplate.postForEntity("/rehab-history",
                request,
                Void.class
        ));
    }

}
