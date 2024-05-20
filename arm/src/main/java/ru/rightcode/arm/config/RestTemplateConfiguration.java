package ru.rightcode.arm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Value("${api.url.medcard.root.v1}")
    private String medCardApiV1Url;

    @Value("${api.url.icf.root.v1}")
    private String icfApiV1Ulr;

    @Bean
    public RestTemplate medCardRestTemplate(RestTemplateBuilder builder) {
        return builder
                .rootUri(medCardApiV1Url)
                .build();
    }

    @Bean
    public RestTemplate icfRestTemplate(RestTemplateBuilder builder) {
        return builder
                .rootUri(icfApiV1Ulr)
                .build();
    }
}
