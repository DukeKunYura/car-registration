package com.duke.carregistration.dto;

import lombok.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class PersonWithCarsDto {
    private UUID id;
    private String passportNumber;
    private String firstName;
    private String surname;
    private String patronymic;
    private Date birthDate;
    private List<CarDto> cars;

    public void setCars(List<CarDto> cars) {
        List<CarDto> carsListDto = new ArrayList<>();
        for (CarDto car : cars) {
            carsListDto.add(car);
        }
        this.cars = carsListDto;
    }
}
