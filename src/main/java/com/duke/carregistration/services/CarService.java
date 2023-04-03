package com.duke.carregistration.services;

import java.util.List;
import java.util.ArrayList;
import com.duke.carregistration.dto.CarDto;
import com.duke.carregistration.mappers.CarMapper;
import org.springframework.stereotype.Service;
import com.duke.carregistration.exceptions.ServerException;

import com.duke.carregistration.dto.CarWithPersonDto;
import com.duke.carregistration.entity.Car;
import com.duke.carregistration.repository.CarRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public List<CarWithPersonDto> getAllCarsWithPerson() {
        List<Car> carsList = carRepository.findAll();
        List<CarWithPersonDto> carsListDto = new ArrayList<>();
        for (Car car : carsList) {
            carsListDto.add(carMapper.toDtoWithPerson(car));
        }
        return carsListDto;
    }

    public CarDto getCarByNumber(String number) {
        Car car = carRepository.findByNumber(number);
        return carMapper.toDto(car);
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
