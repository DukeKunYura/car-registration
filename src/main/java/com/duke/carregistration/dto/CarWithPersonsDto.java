package com.duke.carregistration.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class CarWithPersonsDto {
    private UUID id;
    private String number;
    private String brand;
    private String model;
    private String color;
    private List<PersonDto> persons;

    public void setPersons(List<PersonDto> persons) {
        List<PersonDto> personsListDto = new ArrayList<>();
        for (PersonDto person : persons) {
            personsListDto.add(person);
        }
        this.persons = personsListDto;
    }
}
