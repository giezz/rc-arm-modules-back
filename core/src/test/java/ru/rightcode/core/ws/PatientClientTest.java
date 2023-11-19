package ru.rightcode.core.ws;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.rightcode.medcart.service.patient.GetAllPatientsRequest;
import ru.rightcode.medcart.service.patient.GetAllPatientsResponse;
import ru.rightcode.medcart.service.patient.PatientResponse;
import ru.rightcode.medcart.service.patient.PatientService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatientClientTest {

    @Autowired
    private PatientService patientService;

    @Test
    public void patientClient_test() {
        final GetAllPatientsResponse allPatients = patientService.getAllPatients(new GetAllPatientsRequest());
        final List<PatientResponse> patients = allPatients.getReturn();

        for (PatientResponse patient : patients) {
            System.out.println(patient.getFirstName());
        }
    }
}