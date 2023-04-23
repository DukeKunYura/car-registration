package com.duke.carregistration.repository;

import com.duke.carregistration.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
    Person findByPassportNumber(String passportNumber);
    List<Person> findByFirstNameAndSurnameAndPatronymic(String firstName, String surname, String patronymic);
    @Query("select p from Person p left join fetch p.cars where p.id = :id")
    Person findPersonByIdWithCars(UUID id);
    @Query(value = "SELECT COUNT(person_id) AS person_count FROM Persons p", nativeQuery = true)
    Long getPersonsCount();
}
