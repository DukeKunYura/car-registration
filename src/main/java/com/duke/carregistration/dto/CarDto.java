package com.duke.carregistration.dto;

import java.util.UUID;

import com.duke.carregistration.entity.Car;
import lombok.Data;

@Data
public class CarDto {
    private UUID id;
    private String number;
    private String brand;
    private String model;
    private String color;

    public Car toEntity(CarDto dto) {
        Car car = new Car();
        car.setId(dto.getId());
        car.setNumber(dto.getNumber());
        car.setBrand(dto.getBrand());
        car.setModel(dto.getModel());
        car.setColor(dto.getColor());
        return car;
    }
}
