package com.duke.carregistration.repository.impl;

import com.duke.carregistration.entity.Person;
import com.duke.carregistration.filters.PersonFilter;
import com.duke.carregistration.repository.PersonRepositoryCustom;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

public class PersonRepositoryCustomImpl implements PersonRepositoryCustom {
    @Autowired
    EntityManagerFactory emf;
    @Override
    public List<Person> findPersonsByFilter(PersonFilter filter) {
        if (filter.isEmpty()) {
            return Collections.emptyList();
        }
        String sql = " select p from Person p where 1=1 "
                + (filter.getFirstName() == null ? "" : "and p.firstName = :firstName ")
                + (filter.getSurname() == null ? "" : "and p.surname = :surname ")
                + (filter.getPatronymic() == null ? "" : "and p.patronymic = :patronymic ")
                + (filter.getBirthDate() == null ? "" : "and p.birthDate = :birthDate ")
                + (filter.getMinAgeDate() == null ? "" : "and p.birthDate <= :minAgeDate ")
                + (filter.getMaxAgeDate() == null ? "" : "and p.birthDate >= :maxAgeDate ");

        final var em = emf.createEntityManager();
        final var query = em.createQuery(sql, Person.class);

        if (filter.getFirstName() != null) {
            query.setParameter("firstName", filter.getFirstName());
        }
        if (filter.getSurname() != null) {
            query.setParameter("surname", filter.getSurname());
        }
        if (filter.getPatronymic() != null) {
            query.setParameter("patronymic", filter.getPatronymic());
        }
        if (filter.getBirthDate() != null) {
            query.setParameter("birthDate", filter.getBirthDate());
        }
        if (filter.getMinAgeDate() != null) {
            query.setParameter("minAgeDate", filter.getMinAgeDate());
        }
        if (filter.getMaxAgeDate() != null) {
            query.setParameter("maxAgeDate", filter.getMaxAgeDate());
        }
        return query.getResultList();
    }
}
