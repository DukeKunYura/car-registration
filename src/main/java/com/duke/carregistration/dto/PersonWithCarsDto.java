package com.duke.carregistration.dto;

import com.duke.carregistration.entity.Car;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class PersonWithCarsDto {
    private UUID id;
    private String passportNumber;
    private String firstName;
    private String surname;
    private String patronymic;
    private List<CarDto> cars;

    public void setCars(List<Car> cars) {
        List<CarDto> carsListDto = new ArrayList<>();
        for (Car car : cars) {
            carsListDto.add(car.toDto(car));
        }
        this.cars = carsListDto;

    }
}
