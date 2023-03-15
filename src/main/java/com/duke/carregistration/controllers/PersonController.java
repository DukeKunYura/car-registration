package com.duke.carregistration.controllers;

import com.duke.carregistration.dto.PersonDto;
import com.duke.carregistration.dto.PersonWithCarsDto;
import com.duke.carregistration.entity.Car;
import com.duke.carregistration.entity.Person;
import com.duke.carregistration.services.CarRegistrationService;
import com.duke.carregistration.services.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.List;

@RestController("/")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;
    private final CarRegistrationService carRegistrationService;

    @GetMapping(value = "hou")
    public String getHou() {
        return personService.hou();
    }

    @GetMapping(value = "persons")
    public List<Person> getAllEntity() {
        return personService.getAllPersons();
    }

    @GetMapping(value = "person_with_cars")
    public PersonWithCarsDto getPersonWithCarsByPassportNumber(
            @RequestParam(name = "passport", required = false) String passportNumber) {
        return personService.getPersonWithCarsByPassport(passportNumber);
    }

    @GetMapping(value = "person_with")
    public PersonDto getPersonByPassportNumber(@RequestParam(name = "passport", required = false) String passportNumber) {
        return personService.getByPassport(passportNumber);
    }

    @GetMapping(value = "entity_person_with")
    public Person getPersonEntityByPassportNumber(
            @RequestParam(name = "passport", required = false) String passportNumber) {
        return personService.getByPassportEntity(passportNumber);
    }

    @GetMapping(value = "add_person")
    public PersonDto addPerson(@RequestParam(name = "passport", required = false) String passportNumber,
            @RequestParam(name = "name", required = false) String firstName,
            @RequestParam(name = "surname", required = false) String surname) {
        PersonDto dto = new PersonDto();
        dto.setPassportNumber(passportNumber);
        dto.setFirstName(firstName);
        dto.setSurname(surname);
        personService.addPerson(dto);
        return dto;
    }

    @GetMapping(value = "delete_person")
    public void deletePerson(@RequestParam(name = "passport") String passportNumber) {
        personService.deletePersonWithPassportNumber(passportNumber);
    }

    @GetMapping(value = "registration_car")
    public void registrationCar(@RequestParam(name = "passport") String passportNumber,
            @RequestParam(name = "number") String number,
            @RequestParam(name = "brand") String brand) {
        Car car = new Car();
        car.setNumber(number);
        car.setBrand(brand);
        carRegistrationService.registrationCar(passportNumber, car);
    }

    @SneakyThrows
    @PostMapping(path = "person",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> create(@RequestBody PersonDto newPerson) {

        PersonDto personDto = newPerson;
        personService.addPerson(personDto);
        if (personDto == null) {
            throw new ServerException("invalid_person");
        } else {
            return new ResponseEntity<>(personDto, HttpStatus.CREATED);
        }
    }

}
