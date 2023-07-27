package com.example.petproject.config.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// TODO: 28.07.2023 ObjectMapperConfig, JacksonConfig... не?)
public class JsonConfig {
    // TODO: 28.07.2023 вот прям вообще голый без какой-то настройки?)
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
