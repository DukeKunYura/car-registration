package com.duke.carregistration.services.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import com.duke.carregistration.dto.CarDto;
import com.duke.carregistration.dto.CarWithPersonsDto;
import com.duke.carregistration.dto.PersonWithCarsDto;
import com.duke.carregistration.entity.Person;
import com.duke.carregistration.mappers.CarMapper;
import com.duke.carregistration.services.CarService;
import org.springframework.stereotype.Service;
import com.duke.carregistration.exceptions.ServerException;

import com.duke.carregistration.entity.Car;
import com.duke.carregistration.repository.CarRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public List<CarDto> getAllCars() {
        List<Car> carsList = carRepository.findAll();
        List<CarDto> carsListDto = new ArrayList<>();
        for (Car car : carsList) {
            carsListDto.add(carMapper.toDto(car));
        }
        return carsListDto;
    }

    public CarDto getCarById(UUID id) {
        Car car = carRepository.findCarById(id);
        return carMapper.toDto(car);
    }

    public CarWithPersonsDto getCarWithPersons(UUID id) {
        Car car = carRepository.findCarByIdWithPersons(id);
        return carMapper.toDtoWithPersons(car);
    }

    public CarDto getCarByNumber(String number) {
        Car car = carRepository.findByNumber(number);
        return carMapper.toDto(car);
    }

    public void addCar(CarDto dto) {
        Car car = carMapper.toEntity(dto);
        carRepository.save(car);
    }

    public void deleteCar(UUID id) {
        Car car = carRepository.findById(id).orElseThrow();
        carRepository.delete(car);
    }

    public void updateCar(UUID id, CarDto dto) {
        Car car = carRepository.findById(id).orElseThrow();
        car.setBrand(dto.getBrand());
        car.setModel(dto.getModel());
        car.setColor(dto.getColor());
        carRepository.save(car);
    }
}