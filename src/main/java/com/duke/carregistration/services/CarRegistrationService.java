package com.duke.carregistration.services;

import com.duke.carregistration.entity.Car;
import com.duke.carregistration.entity.Person;
import com.duke.carregistration.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarRegistrationService {
    private final PersonRepository personRepository;
    public void registrationCar(String passportNumber, Car car) {
        Person person = personRepository.findByPassportNumber(passportNumber);
        person.addCar(car);
        personRepository.save(person);
    }
}
