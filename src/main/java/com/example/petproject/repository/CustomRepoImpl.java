package com.example.petproject.repository;

import com.example.petproject.exception.ObjectNotFoundException;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class CustomRepoImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomRepo<T, ID> {

    private Class<T> type;

    public CustomRepoImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
    }

    public CustomRepoImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);

//        this.type = entityInformation.getJavaType();
    }

    @Override
    public T findByIdCustom(ID id) {
        return findById(id).orElseThrow(() -> new ObjectNotFoundException(type.getName(), id.toString()));
    }
}
