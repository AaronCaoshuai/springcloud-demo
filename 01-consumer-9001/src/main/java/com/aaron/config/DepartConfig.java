package com.aaron.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Java Config Bean
 */
@Configuration
public class DepartConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
