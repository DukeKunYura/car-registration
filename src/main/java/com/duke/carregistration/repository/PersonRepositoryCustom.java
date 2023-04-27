package com.duke.carregistration.repository;

import com.duke.carregistration.entity.Person;
import com.duke.carregistration.filters.PersonFilter;

import java.time.LocalDate;
import java.util.List;

public interface PersonRepositoryCustom {
    List<Person> findPersonsByFilter(PersonFilter filter);
}
