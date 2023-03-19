package com.duke.carregistration.services;

import com.duke.carregistration.dto.CarDto;
import com.duke.carregistration.entity.Car;
import com.duke.carregistration.entity.Person;
import com.duke.carregistration.repository.CarRepository;
import com.duke.carregistration.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarRegistrationService {
    private final PersonRepository personRepository;
    private final CarRepository carRepository;
    public void registrationCar(String passportNumber, CarDto dto) {
        Person person = personRepository.findByPassportNumber(passportNumber);
        person.addCar(dto.toEntity(dto));
        personRepository.save(person);
    }
    public void removalFromRegisterCar(String passportNumber, String number) {
        Person person = personRepository.findByPassportNumber(passportNumber);
        Car car = carRepository.findByNumber(number);
        person.removeCar(car);
        personRepository.save(person);
    }
}
