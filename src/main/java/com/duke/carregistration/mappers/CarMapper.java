package com.duke.carregistration.mappers;

import com.duke.carregistration.dto.CarDto;
import com.duke.carregistration.dto.CarWithPersonsDto;
import com.duke.carregistration.entity.Car;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarMapper {
    public CarDto toDto(Car car) {
        CarDto dto = new CarDto();
        dto.setId(car.getId());
        dto.setNumber(car.getNumber());
        dto.setBrand(car.getBrand());
        dto.setModel(car.getModel());
        dto.setColor(car.getColor());
        return dto;
    }

    public CarWithPersonsDto toDtoWithPersons(Car car) {
        CarWithPersonsDto dto = new CarWithPersonsDto();
        dto.setId(car.getId());
        dto.setNumber(car.getNumber());
        dto.setBrand(car.getBrand());
        dto.setModel(car.getModel());
        dto.setColor(car.getColor());
        PersonMapper personMapper = new PersonMapper();
        dto.setPersons(personMapper.toDtoPersonsList(car.getPersons()));
        return dto;
    }

    public Car toEntity(CarDto dto) {
        Car car = new Car();
        car.setId(dto.getId());
        car.setNumber(dto.getNumber());
        car.setBrand(dto.getBrand());
        car.setModel(dto.getModel());
        car.setColor(dto.getColor());
        return car;
    }

    public List<CarDto> toDtoCarsList(List<Car> cars) {
        List<CarDto> carsListDto = new ArrayList<>();
        for (Car car : cars) {
            carsListDto.add(this.toDto(car));
        }
        return carsListDto;
    }
}
