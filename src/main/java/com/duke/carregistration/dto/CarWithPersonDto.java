package com.duke.carregistration.dto;

import java.util.UUID;
import lombok.Data;

@Data
public class CarWithPersonDto {
    private UUID id;
    private String number;
    private String brand;
    private String model;
    private String color;
    private PersonDto person;

    public void setPerson(PersonDto dto) {
        this.person = dto;
    }
}
