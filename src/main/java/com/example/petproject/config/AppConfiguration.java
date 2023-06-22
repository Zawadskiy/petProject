package com.example.petproject.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    // TODO: 22.06.2023 Так, формально, можно. Но советую сразу определять узкие классы-конфиги,
    //  иначе со временем будет одна жирная хрень, вбирающая сразу все
    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
