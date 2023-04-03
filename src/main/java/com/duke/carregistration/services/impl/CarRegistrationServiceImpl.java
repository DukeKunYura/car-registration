package com.duke.carregistration.services.impl;

import com.duke.carregistration.dto.CarDto;
import com.duke.carregistration.entity.Car;
import com.duke.carregistration.entity.Person;
import com.duke.carregistration.mappers.CarMapper;
import com.duke.carregistration.repository.CarRepository;
import com.duke.carregistration.repository.PersonRepository;
import com.duke.carregistration.exceptions.ServerException;
import com.duke.carregistration.services.CarRegistrationService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarRegistrationServiceImpl implements CarRegistrationService {
    private final PersonRepository personRepository;
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public void registrationCar(String passportNumber, CarDto dto) {
        Car carExist = carRepository.findByNumber(dto.getNumber());
        if (carExist == null) {
            Person person = personRepository.findByPassportNumber(passportNumber);
            person.addCar(carMapper.toEntity(dto));
            personRepository.save(person);
        } else {
            throw new ServerException();
        }
    }

    public void removalFromRegisterCar(String passportNumber, String number) {
        Person person = personRepository.findByPassportNumber(passportNumber);
        Car car = carRepository.findByNumber(number);
        person.removeCar(car);
        personRepository.save(person);
    }
}
