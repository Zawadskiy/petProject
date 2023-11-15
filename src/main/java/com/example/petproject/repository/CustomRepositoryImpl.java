package com.example.petproject.repository;

import com.example.petproject.domain.Dormitory;
import com.example.petproject.domain.University;
import com.example.petproject.exception.ObjectNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;


public class CustomRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomRepository<T, ID> {

    private final Class<T> type;
    private final EntityManager entityManager;

    private static final String ADMIN = "ADMIN";
    private static final String UNIVERSITY = "UNIVERSITY";
    private static final String DORMITORY = "DORMITORY";
    private static final String ROOM = "ROOM";
    private static final String STUDENT = "STUDENT";


    public CustomRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.type = entityInformation.getJavaType();
    }

    @Override
    public T findByIdEx(ID id) {
        return findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(type, id));
    }

    @Override
    public Page<T> findAll(Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<T> query = cb.createQuery(type);
        Root<T> root = query.from(type);
        List<Order> orderBy = pageable.getSort()
                .stream()
                .map(order -> cb.asc(root.get(order.getProperty())))
                .toList();

        query.select(root)
                .orderBy(orderBy);

        where(cb, root, query);

        TypedQuery<T> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(pageable.getPageNumber());
        typedQuery.setMaxResults(pageable.getPageSize());

        return PageableExecutionUtils.getPage(typedQuery.getResultList(), pageable,
                () -> executeCountQuery(getCountQuery(null, type)));
    }

    private long executeCountQuery(TypedQuery<Long> query) {

        Objects.requireNonNull(query, "TypedQuery must not be null");

        List<Long> totals = query.getResultList();

        return totals.stream()
                .mapToLong(Long::longValue)
                .sum();
    }

    private void where(CriteriaBuilder cb, Root<T> from, CriteriaQuery<T> query) {

        String role = SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("User doesn't have any role"));

        String[] info = role.split("_");

        switch (info[1]) {

            case ADMIN -> {
            }

            case UNIVERSITY -> {
                buildQueryForUniversityRole(cb, from, query, info[2]);
            }

            case DORMITORY -> {
                buildQueryForDormitoryRole(cb, from, query, info[2]);
            }

            default -> throw new RuntimeException();
        }
    }

    // TODO: 18.08.2023 это что за чудо?)
    private void buildQueryForUniversityRole(CriteriaBuilder cb, Root<T> from, CriteriaQuery<T> query, String universityName) {
        switch (type.getName().toUpperCase()) {
            case UNIVERSITY -> {
                Predicate name = cb.like(cb.upper(from.get("name")), universityName);
                query.where(name);
            }
            case DORMITORY, STUDENT -> {
                long universityId = findUniversityIdByName(universityName);
                query.where(from.get("university").in(universityId));
            }
            case ROOM -> {
                List<Long> dormitories = findDormitoriesByUniversity(findUniversityIdByName(universityName));
                query.where(from.get("dormitory").in(dormitories));
            }
            default -> throw new RuntimeException();
        }
    }

    private void buildQueryForDormitoryRole(CriteriaBuilder cb, Root<T> from, CriteriaQuery<T> query, String dormitoryNumber) {
        switch (type.getName().toUpperCase()) {
            case UNIVERSITY -> {
                throw new RuntimeException();
            }
            case DORMITORY -> {
                query.where(cb.like(from.get("number"), dormitoryNumber));
            }
            case ROOM -> {
                query.where(from.get("dormitory").in(findDormitoryByNumber(dormitoryNumber)));
            }
            case STUDENT -> {
                long dormitory = findDormitoryByNumber(dormitoryNumber);
                query.where(from.get("dormitory").in(dormitory));
            }
            default -> throw new RuntimeException();
        }

    }
    private long findUniversityIdByName(String name) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<University> universityCriteriaQuery = cb.createQuery(University.class);
        Root<University> universityRoot = universityCriteriaQuery.from(University.class);

        Predicate university = cb.like(cb.upper(universityRoot.get(name)), name);
        universityCriteriaQuery.select(universityRoot).where(university);

        TypedQuery<University> universityTypedQuery = entityManager.createQuery(universityCriteriaQuery);

        return universityTypedQuery.getResultList()
                .stream()
                .map(University::getId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException());
    }

    private List<Long> findDormitoriesByUniversity(long id) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Dormitory> dormitoryCriteriaQuery = cb.createQuery(Dormitory.class);
        Root<Dormitory> dormitoryRoot = dormitoryCriteriaQuery.from(Dormitory.class);

        Predicate dormitory = cb.like(dormitoryRoot.get("university"), "%d".formatted(id));
        dormitoryCriteriaQuery.select(dormitoryRoot).where(dormitory);

        TypedQuery<Dormitory> universityTypedQuery = entityManager.createQuery(dormitoryCriteriaQuery);

        return universityTypedQuery.getResultList()
                .stream()
                .map(Dormitory::getId)
                .toList();
    }

    private long findDormitoryByNumber(String number) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Dormitory> dormitoryCriteriaQuery = cb.createQuery(Dormitory.class);
        Root<Dormitory> dormitoryRoot = dormitoryCriteriaQuery.from(Dormitory.class);

        Predicate dormitory = cb.like(dormitoryRoot.get(number), number);
        dormitoryCriteriaQuery.select(dormitoryRoot).where(dormitory);

        TypedQuery<Dormitory> universityTypedQuery = entityManager.createQuery(dormitoryCriteriaQuery);

        return universityTypedQuery.getResultList()
                .stream()
                .map(Dormitory::getId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException());
    }
}
