package com.duke.carregistration.controllers;

import com.duke.carregistration.dto.PersonDto;
import com.duke.carregistration.dto.PersonWithCarsDto;
import com.duke.carregistration.entity.Car;
import com.duke.carregistration.entity.Person;
import com.duke.carregistration.services.CarRegistrationService;
import com.duke.carregistration.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Person> getAll() {
        return personService.getAllPersons();
    }

    @GetMapping(value = "person_with_cars")
    public PersonWithCarsDto findPersonWithCarsWithPassportNumber(
            @RequestParam(name = "passport", required = false) String passportNumber) {
        return personService.getPersonWithCarsByPassport(passportNumber);
    }

    @GetMapping(value = "person_with")
    public PersonDto findWithPassportNumber(@RequestParam(name = "passport", required = false) String passportNumber) {
        return personService.getByPassport(passportNumber);
    }

    @GetMapping(value = "entity_person_with")
    public Person findWithPassportNumberEntity(
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

}
