package com.duke.carregistration.controllers;

import com.duke.carregistration.dto.CarDto;
import com.duke.carregistration.dto.CarWithPersonsDto;
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
    public ResponseEntity<PersonDto> getPersonById(
            @RequestParam(name = "id", required = false) UUID id) {
        try {
            PersonDto personDto = personService.getPersonById(id);
            return ResponseEntity.status(HttpStatus.OK).body(personDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value = "car")
    public ResponseEntity<CarDto> getCarById(
            @RequestParam(name = "id", required = false) UUID id) {
        try {
            CarDto carDto = carService.getCarById(id);
            return ResponseEntity.status(HttpStatus.OK).body(carDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value = "person_with_cars")
    public ResponseEntity<PersonWithCarsDto> getPersonWithCars(
            @RequestParam(name = "id", required = false) UUID id) {
        try {
            PersonWithCarsDto person = personService.getPersonWithCars(id);
            return ResponseEntity.status(HttpStatus.OK).body(person);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value = "car_with_persons")
    public ResponseEntity<CarWithPersonsDto> getCarWithPersons(
            @RequestParam(name = "id", required = false) UUID id) {
        try {
            CarWithPersonsDto car = carService.getCarWithPersons(id);
            return ResponseEntity.status(HttpStatus.OK).body(car);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value = "person_number")
    public ResponseEntity<PersonDto> getPersonByNumber(
            @RequestParam(name = "number") String number) {
        try {
            PersonDto personDto = personService.getByPassport(number);
            return ResponseEntity.status(HttpStatus.OK).body(personDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value = "car_number")
    public ResponseEntity<CarDto> getCarByNumber(
            @RequestParam(name = "number") String number) {
        try {
            CarDto carDto = carService.getCarByNumber(number);
            return ResponseEntity.status(HttpStatus.OK).body(carDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
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
    public void deletePerson(@RequestParam(name = "id") UUID id) {
        try {
            personService.deletePerson(id);
        } catch (Exception e) {
            throw new ServerException();
        }
    }

    @DeleteMapping(value = "delete_car")
    public void deleteCar(@RequestParam(name = "id") UUID id) {
        try {
            carService.deleteCar(id);
        } catch (Exception e) {
            throw new ServerException();
        }
    }

    // @GetMapping(value = "person_with_cars")
    // public PersonWithCarsDto getPersonWithCarsByPassportNumber(
    // @RequestParam(name = "passport", required = false) String passportNumber) {
    // return personService.getPersonWithCarsByPassport(passportNumber);
    // }

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
