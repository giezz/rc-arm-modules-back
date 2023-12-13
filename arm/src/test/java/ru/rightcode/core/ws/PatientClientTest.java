package ru.rightcode.core.ws;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.rightcode.medcart.service.patient.*;

import java.util.List;

@SpringBootTest
class PatientClientTest {

    @Autowired
    private PatientWebService patientWebService;

    @Test
    public void patientClientTest() {
        final GetAllPatientsResponse allPatients = patientWebService.getAllPatients(new GetAllPatientsRequest());
        final List<PatientResponse> patients = allPatients.getReturn();

        for (PatientResponse patient : patients) {
            System.out.println(patient.getFirstName());
        }
    }

    @Test
    public void optionalParamsTest() {
        GetPatientTestRequest request = new GetPatientTestRequest();
        request.setPatientCode(1005L);
        GetPatientTestResponse testResponse = patientWebService.getPatientTest(request);
    }
}