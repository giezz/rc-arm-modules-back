package ru.rightcode.arm.config;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.rightcode.medcart.service.patient.PatientService;

@Configuration
public class WebServiceConfig {

    @Bean
    public PatientService patientWebService() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(PatientService.class);
        factory.setAddress("http://localhost:8081/medcart/patient");
        return (PatientService) factory.create();
    }

}
