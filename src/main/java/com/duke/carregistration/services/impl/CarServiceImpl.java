package com.duke.carregistration.services.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import com.duke.carregistration.dto.CarDto;
import com.duke.carregistration.dto.PersonDto;
import com.duke.carregistration.entity.Person;
import com.duke.carregistration.mappers.CarMapper;
import com.duke.carregistration.services.CarService;
import org.springframework.stereotype.Service;
import com.duke.carregistration.exceptions.ServerException;

import com.duke.carregistration.dto.CarWithPersonDto;
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

    public CarDto getCarByNumber(String number) {
        Car car = carRepository.findByNumber(number);
        return carMapper.toDto(car);
    }

    public void addCar(CarDto dto){
        Car car = carMapper.toEntity(dto);
        carRepository.save(car);
    }

    public void updateCar(String number, CarDto dto) {
        Car carExists = carRepository.findByNumber(number);
        Car carNewExist = carRepository.findByNumber(dto.getNumber());
        if (carNewExist == null) {
            carExists.setNumber(dto.getNumber());
            carExists.setBrand(dto.getBrand());
            carExists.setModel(dto.getModel());
            carExists.setColor(dto.getColor());
            carRepository.save(carExists);
        } else {
            throw new ServerException();
        }
    }

}
