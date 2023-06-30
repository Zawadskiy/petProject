package com.example.petproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
// TODO: 29.06.2023 Repository. Сокращения - такое
public interface CustomRepo<T, ID> extends JpaRepository<T, ID> {
    // TODO: 29.06.2023 findByIdEx? по аналогии, метод, возвращающий null можно объявить как findByIdNoEx, если нужен
    T findByIdCustom(ID id);
}
