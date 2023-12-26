package ru.rightcode.anketi.config;

import lombok.RequiredArgsConstructor;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.rightcode.anketi.service.answer.AnswerServiceImpl;
import ru.rightcode.anketi.service.form.FormServiceImpl;
import ru.rightcode.anketi.service.question.QuestionServiceImpl;
import ru.rightcode.anketi.service.scale.ScaleServiceImpl;
import ru.rightcode.anketi.service.variant.VariantServiceImpl;


@Configuration
@RequiredArgsConstructor
public class WebServiceConfig {

    private final Bus bus;

    @Bean
    public EndpointImpl answerEndpoint(AnswerServiceImpl answerService) {
        EndpointImpl endpoint = new EndpointImpl(bus, answerService);
        endpoint.publish("/answer");
        return endpoint;
    }

    @Bean
    public EndpointImpl formEndpoint(FormServiceImpl formService) {
        EndpointImpl endpoint = new EndpointImpl(bus, formService);
        endpoint.publish("/form");
        return endpoint;
    }

    @Bean
    public EndpointImpl questionEndpoint(QuestionServiceImpl questionService) {
        EndpointImpl endpoint = new EndpointImpl(bus, questionService);
        endpoint.publish("/question");
        return endpoint;
    }

    @Bean
    public EndpointImpl scaleEndpoint(ScaleServiceImpl scaleService) {
        EndpointImpl endpoint = new EndpointImpl(bus, scaleService);
        endpoint.publish("/scale");
        return endpoint;
    }

    @Bean
    public EndpointImpl variantEndpoint(VariantServiceImpl variantService) {
        EndpointImpl endpoint = new EndpointImpl(bus, variantService);
        endpoint.publish("/variant");
        return endpoint;
    }

}
