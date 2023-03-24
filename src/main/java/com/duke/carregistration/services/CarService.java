package com.duke.carregistration.services;

import java.util.List;
import java.util.ArrayList;

import com.duke.carregistration.dto.CarDto;
import org.springframework.stereotype.Service;

import com.duke.carregistration.dto.CarWithPersonDto;
import com.duke.carregistration.entity.Car;
import com.duke.carregistration.repository.CarRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public List<CarWithPersonDto> getAllCarsWithPerson() {
        List<Car> carsList = carRepository.findAll();
        List<CarWithPersonDto> carsListDto = new ArrayList<>();
        for (Car car : carsList) {
            carsListDto.add(car.toDtoWithPerson(car));
        }
        return carsListDto;
    }

    public CarDto getCarByNumber(String number) {
        Car car = carRepository.findByNumber(number);
        return car.toDto(car);
    }
}
