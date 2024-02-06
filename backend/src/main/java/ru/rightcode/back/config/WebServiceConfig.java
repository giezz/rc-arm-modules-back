package ru.rightcode.back.config;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.rightcode.anketi.service.answer.AnswerService;
import ru.rightcode.anketi.service.form.FormService;
import ru.rightcode.anketi.service.question.QuestionService;
import ru.rightcode.anketi.service.scale.ScaleService;
import ru.rightcode.anketi.service.variant.VariantService;

@Configuration
public class WebServiceConfig {

    /*@Bean
    public PatientService patientWebService() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(PatientWebService.class);
        factory.setAddress("http://localhost:8081/medcart/patient");
        return (PatientWebService) factory.create();
    }*/

    @Bean
    public FormService formService(){
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(FormService.class);
        factoryBean.setAddress("http://localhost:8081/anketi/form");
        return (FormService) factoryBean.create();
    }

    @Bean
    public QuestionService questionService(){
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(QuestionService.class);
        factoryBean.setAddress("http://localhost:8081/anketi/question");
        return (QuestionService) factoryBean.create();
    }

    @Bean
    public VariantService variantService(){
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(VariantService.class);
        factoryBean.setAddress("http://localhost:8081/anketi/variant");
        return (VariantService) factoryBean.create();
    }

    @Bean
    public AnswerService answerService(){
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(AnswerService.class);
        factoryBean.setAddress("http://localhost:8081/anketi/answer");
        return (AnswerService) factoryBean.create();
    }

    @Bean
    public ScaleService scaleService(){
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(ScaleService.class);
        factoryBean.setAddress("http://localhost:8081/anketi/scale");
        return (ScaleService) factoryBean.create();
    }
}
