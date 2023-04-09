package com.duke.carregistration.controllers;

import com.duke.carregistration.dto.CarDto;
import com.duke.carregistration.dto.CarWithPersonDto;
import com.duke.carregistration.dto.PersonDto;
import com.duke.carregistration.dto.PersonWithCarsDto;
import com.duke.carregistration.kit.PairId;
import com.duke.carregistration.services.impl.CarRegistrationServiceImpl;
import com.duke.carregistration.services.impl.CarServiceImpl;
import com.duke.carregistration.services.impl.PersonServiceImpl;
import com.duke.carregistration.exceptions.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@org.springframework.web.bind.annotation.RestController("/")
// @CrossOrigin(origins = "http://localhost:8085")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class RestController {
    private final PersonServiceImpl personService;
    private final CarServiceImpl carService;
    private final CarRegistrationServiceImpl carRegistrationService;

    @GetMapping(value = "persons")
    public List<PersonDto> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping(value = "cars")
    public List<CarDto> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping(value = "person")
    public PersonDto getPersonById(
            @RequestParam(name = "id", required = false) UUID id) {
        return personService.getPersonById(id);
    }

    @GetMapping(value = "car")
    public CarDto getCarById(
            @RequestParam(name = "id", required = false) UUID id) {
        return carService.getCarById(id);
    }

    @GetMapping(value = "person_number")
    public PersonDto getPersonByNumber(
            @RequestParam(name = "number") String number) {
        return personService.getByPassport(number);

    }

    @GetMapping(value = "car_number")
    public CarDto getCarByNumber(
            @RequestParam(name = "number") String number) {
        return carService.getCarByNumber(number);
    }

    @PostMapping(path = "person", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> addPerson(@RequestBody PersonDto newPerson) {
        if (newPerson == null) {
            throw new ServerException();
        } else {
            personService.addPerson(newPerson);
            return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
        }
    }

    @PostMapping(path = "car", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarDto> addCar(@RequestBody CarDto newCar) {
        if (newCar == null) {
            throw new ServerException();
        } else {
            carService.addCar(newCar);
            return new ResponseEntity<>(newCar, HttpStatus.CREATED);
        }
    }

    @PostMapping(path = "registration_car", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PairId> registrationCar(@RequestBody PairId pairId) {
        if (pairId == null) {
            throw new ServerException();
        } else {
            carRegistrationService.registrationCar(pairId);
            return new ResponseEntity<>(pairId, HttpStatus.CREATED);
        }
    }

    @DeleteMapping(value = "delete_person")
    public void deletePersonByPassport(@RequestParam(name = "passport") String passportNumber) {
        personService.deletePersonWithPassportNumber(passportNumber);
    }

    @GetMapping(value = "person_with_cars")
    public PersonWithCarsDto getPersonWithCarsByPassportNumber(
            @RequestParam(name = "passport", required = false) String passportNumber) {
        return personService.getPersonWithCarsByPassport(passportNumber);
    }

    // @PutMapping(path = "person", consumes = MediaType.APPLICATION_JSON_VALUE,
    // produces = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<PersonDto> editPerson(@RequestParam(name = "passport")
    // String passportNumber,
    // @RequestBody PersonDto newPerson) {
    // PersonDto personDto = newPerson;
    // personService.updatePerson(passportNumber, personDto);
    // if (personDto == null) {
    // throw new ServerException();
    // } else {
    // return new ResponseEntity<>(personDto, HttpStatus.CREATED);
    // }
    // }

    // @GetMapping(value = "person_passport")
    // public PersonDto getPersonByPassportNumber(
    // @RequestParam(name = "passport", required = false) String passportNumber) {
    // return personService.getByPassport(passportNumber);
    // }

    // @PostMapping(path = "registration_car", consumes =
    // MediaType.APPLICATION_JSON_VALUE, produces =
    // MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<CarDto> registrationCar(@RequestParam(name =
    // "passport") String passportNumber,
    // @RequestBody CarDto newCar) {
    // carRegistrationService.registrationCar(passportNumber, newCar);
    // if (newCar == null) {
    // throw new ServerException();
    // } else {
    // return new ResponseEntity<>(newCar, HttpStatus.CREATED);
    // }
    // }

    // @PutMapping(path = "car", consumes = MediaType.APPLICATION_JSON_VALUE,
    // produces = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<CarDto> editCar(@RequestParam(name = "number") String
    // number,
    // @RequestBody CarDto newCar) {
    //
    // CarDto carDto = newCar;
    // carService.updateCar(number, carDto);
    // if (carDto == null) {
    // throw new ServerException();
    // } else {
    // return new ResponseEntity<>(carDto, HttpStatus.UPGRADE_REQUIRED);
    // }
    // }

    // @DeleteMapping(value = "removal_car")
    // public void removeCarByNumber(@RequestParam(name = "passport") String
    // passportNumber,
    // @RequestParam(name = "number") String number) {
    // carRegistrationService.removalFromRegisterCar(passportNumber, number);
    // }
}
