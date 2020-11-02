package io.github.bkosaraju.kafkarest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class RestClientConfiguration {

    @Bean
    public RestTemplate restTesmplate() {
        return new RestTemplate();
    }

}