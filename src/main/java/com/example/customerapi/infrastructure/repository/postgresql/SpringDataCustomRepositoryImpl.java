package com.example.customerapi.infrastructure.repository.postgresql;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.customerapi.domain.CustomerSearchCriteria;
import com.example.customerapi.domain.Paging;

public class SpringDataCustomRepositoryImpl implements SpringDataCustomRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public SpringDataCustomRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    @Override
    public Page<CustomerEntity> findAllWithFilters(CustomerSearchCriteria c, Paging paging) {
        CriteriaQuery<CustomerEntity> criteriaQuery = criteriaBuilder.createQuery(CustomerEntity.class);
        Root<CustomerEntity> employeeRoot = criteriaQuery.from(CustomerEntity.class);
        Predicate predicate = getPredicate(c, employeeRoot);
        criteriaQuery.where(predicate);
        setOrder(paging, criteriaQuery, employeeRoot);

        TypedQuery<CustomerEntity> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(paging.getPage() * paging.getPageSize());
        typedQuery.setMaxResults(paging.getPageSize());

        Pageable pageable = getPageable(paging);

        long employeesCount = getEmployeesCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, employeesCount);
    }

    private Predicate getPredicate(CustomerSearchCriteria criteria,
            Root<CustomerEntity> customerRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(criteria.getName())) {
            predicates.add(
                    criteriaBuilder.like(customerRoot.get("custName"),
                            "%" + criteria.getName() + "%"));
        }
        if (Objects.nonNull(criteria.getAddress())) {
            predicates.add(
                    criteriaBuilder.like(customerRoot.get("custAddress"),
                            "%" + criteria.getAddress() + "%"));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(Paging paging,
            CriteriaQuery<CustomerEntity> criteriaQuery,
            Root<CustomerEntity> customerRoot) {
        
        if (Objects.isNull(paging.getSortBy())) {
            paging.setSortBy("custId");
        }
        if (paging.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(customerRoot.get(paging.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(customerRoot.get(paging.getSortBy())));
        }
    }

    private Pageable getPageable(Paging paging) {
        Sort sort = Sort.by(paging.getSortDirection(), paging.getSortBy());
        return PageRequest.of(paging.getPage(), paging.getPageSize(), sort);
    }

    private long getEmployeesCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<CustomerEntity> countRoot = countQuery.from(CustomerEntity.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

}
