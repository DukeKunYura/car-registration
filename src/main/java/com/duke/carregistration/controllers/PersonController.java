package com.duke.carregistration.controllers;

import com.duke.carregistration.dto.CarDto;
import com.duke.carregistration.dto.PersonDto;
import com.duke.carregistration.dto.PersonWithCarsDto;
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
@CrossOrigin(origins = "http://localhost:8085")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;
    private final CarRegistrationService carRegistrationService;
    @GetMapping(value = "persons")
    public  List<PersonDto> getAllPersons() { return personService.getAllPersons(); }
    @GetMapping(value = "person_with_cars")
    public PersonWithCarsDto getPersonWithCarsByPassportNumber(
            @RequestParam(name = "passport", required = false) String passportNumber) {
        return personService.getPersonWithCarsByPassport(passportNumber);
    }
    @GetMapping(value = "person")
    public PersonDto getPersonByPassportNumber(@RequestParam(name = "passport", required = false) String passportNumber) {
        return personService.getByPassport(passportNumber);
    }
    @DeleteMapping(value = "delete_person")
    public void deletePersonByPassport(@RequestParam(name = "passport") String passportNumber) {
        personService.deletePersonWithPassportNumber(passportNumber);
    }
    @SneakyThrows
    @PostMapping(path = "person",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> addPerson(@RequestBody PersonDto newPerson) {

        PersonDto personDto = newPerson;
        personService.addPerson(personDto);
        if (personDto == null) {
            throw new ServerException("invalid_person");
        } else {
            return new ResponseEntity<>(personDto, HttpStatus.CREATED);
        }
    }
    @SneakyThrows
    @PostMapping(path = "registration_car",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarDto> registrationCar(@RequestParam(name = "passport") String passportNumber,
                                 @RequestBody CarDto newCar) {
        carRegistrationService.registrationCar(passportNumber, newCar);
        if (newCar == null) {
            throw new ServerException("invalid_car");
        } else {
            return new ResponseEntity<>(newCar, HttpStatus.CREATED);
        }
    }
    @DeleteMapping(value = "removal_car")
    public void removeCarByNumber(@RequestParam(name = "passport") String passportNumber,
                                  @RequestParam(name = "number") String number) {
        carRegistrationService.removalFromRegisterCar(passportNumber, number);
    }
}
