package com.example.petproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomRepo<T, ID> extends JpaRepository<T, ID> {

    T findByIdCustom(ID id);
}
