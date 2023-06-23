package com.example.petproject.config;

import com.example.petproject.repository.CustomRepoImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.petproject.repository",
        repositoryBaseClass = CustomRepoImpl.class)
public class JpaConfiguration {
}
