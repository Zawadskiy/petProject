package com.example.petproject.config;

import com.example.petproject.repository.CustomRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.petproject.repository",
        repositoryBaseClass = CustomRepositoryImpl.class)
public class JpaConfiguration {
}
