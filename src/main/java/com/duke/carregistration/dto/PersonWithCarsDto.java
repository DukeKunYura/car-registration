package com.duke.carregistration.dto;

import com.duke.carregistration.entity.Car;
import com.duke.carregistration.entity.Person;
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

    public void setCars(List<Car> cars2) {
        List<CarDto> carsDtos = new ArrayList<>();
        for (Car car : cars2) {
            carsDtos.add(car.toDto(car));
        }
        this.cars = carsDtos;

    }
}
