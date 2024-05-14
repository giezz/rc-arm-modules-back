package ru.rightcode.arm.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.rightcode.arm.ArmApplication;

@Configuration
public class BeansConfiguration {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .rootUri(ArmApplication.MEDCART_API_URL)
                .build();
    }
}
