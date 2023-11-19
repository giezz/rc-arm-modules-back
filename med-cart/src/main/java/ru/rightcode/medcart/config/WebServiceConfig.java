package ru.rightcode.medcart.config;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.rightcode.medcart.service.epicrisis.EpicrisisServiceImpl;
import ru.rightcode.medcart.service.hospitalization.HospitalizationHistoryServiceImpl;
import ru.rightcode.medcart.service.patient.PatientServiceImpl;

@Configuration
public class WebServiceConfig {

    @Autowired
    private Bus bus;

    @Bean
    public EndpointImpl patientEndpoint(PatientServiceImpl patientService) {
        EndpointImpl endpoint = new EndpointImpl(bus, patientService);
        endpoint.publish("/patient");
        return endpoint;
    }

    @Bean
    public EndpointImpl hospitalizationHistoryEndpoint(HospitalizationHistoryServiceImpl hospitalizationHistoryService) {
        EndpointImpl endpoint = new EndpointImpl(bus, hospitalizationHistoryService);
        endpoint.publish("/hosp-history");
        return endpoint;
    }

    @Bean
    public EndpointImpl epicrisisEndpoint(EpicrisisServiceImpl epicrisisService) {
        EndpointImpl endpoint = new EndpointImpl(bus, epicrisisService);
        endpoint.publish("/epicrisis");
        return endpoint;
    }
}
