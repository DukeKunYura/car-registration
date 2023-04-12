package com.duke.carregistration.repository;

import com.duke.carregistration.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
    Person findByPassportNumber(String passportNumber);

    @Query("select p from Person p left join fetch p.cars where p.id = :id")
    Person findPersonByIdWithCars(UUID id);
}
