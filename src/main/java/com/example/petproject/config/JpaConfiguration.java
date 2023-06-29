package com.example.petproject.config;

import com.example.petproject.repository.CustomRepoImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
// TODO: 29.06.2023 разве это обязательно?
@EnableJpaRepositories(basePackages = "com.example.petproject.repository",
        repositoryBaseClass = CustomRepoImpl.class)
public class JpaConfiguration {
}
