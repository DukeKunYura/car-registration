package com.duke.carregistration.repository;

import com.duke.carregistration.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID>, PersonRepositoryCustom {
    Person findByPassportNumber(String passportNumber);
    List<Person> findByFirstNameAndSurnameAndPatronymicAndBirthDateBetween(
            String firstName, String surname, String patronymic, LocalDate startDate, LocalDate endDate);
    List<Person> findByFirstNameAndSurnameAndPatronymic(
            String firstName, String surname, String patronymic);
    List<Person> findByFirstNameAndSurnameAndBirthDateBetween(
            String firstName, String surname, LocalDate startDate, LocalDate endDate);
    List<Person> findByFirstNameAndPatronymicAndBirthDateBetween(
            String firstName, String patronymic, LocalDate startDate, LocalDate endDate);
    List<Person> findBySurnameAndPatronymicAndBirthDateBetween(
            String surname, String patronymic, LocalDate startDate, LocalDate endDate);
    List<Person> findByFirstNameAndBirthDateBetween(String firstName, LocalDate startDate, LocalDate endDate);
    List<Person> findBySurnameAndBirthDateBetween(String surname, LocalDate startDate, LocalDate endDate);
    List<Person> findByPatronymicAndBirthDateBetween(String patronymic, LocalDate startDate, LocalDate endDate);
    List<Person> findByBirthDateBetween(LocalDate startDate, LocalDate endDate);
    List<Person> findByFirstNameAndSurname(String firstName, String surname);
    List<Person> findByFirstNameAndPatronymic(String firstName, String patronymic);
    List<Person> findBySurnameAndPatronymic(String surname, String patronymic);
    List<Person> findByFirstName(String firstName);
    List<Person> findBySurname(String surname);
    List<Person> findByPatronymic(String patronymic);
    @Query("select p from Person p left join fetch p.cars where p.id = :id")
    Person findPersonByIdWithCars(UUID id);
    @Query(value = "SELECT COUNT(person_id) AS person_count FROM Persons p", nativeQuery = true)
    Long getPersonsCount();
    @Query("SELECT p FROM Person p WHERE (:firstName is null or p.firstName = :firstName) " +
            "and (:surname is null or p.surname = :surname) " +
            "and (:patronymic is null or p.patronymic = :patronymic) ")
    List<Person> findPersonsByParamsAndAge2(@Param("firstName") String firstName,
                                        @Param("surname") String surname,
                                        @Param("patronymic") String patronymic);

    @Query("SELECT p FROM Person p WHERE (:firstName is null or p.firstName = :firstName) " +
            "and (:surname is null or p.surname = :surname) " +
            "and (:patronymic is null or p.patronymic = :patronymic) " +
            "and (:minAge is null or p.birthDate >= :minAge)" +
            "and (:maxAge is null or p.birthDate <= :maxAge)")
    List<Person> findPersonsByParamsAndAge(@Param("minAge") LocalDate minAgeDate, @Param("maxAge") LocalDate maxAgeDate,
                                           @Param("firstName") String firstName,
                                           @Param("surname") String surname,
                                           @Param("patronymic") String patronymic);

}
