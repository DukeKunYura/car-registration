package com.duke.carregistration.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class PersonDto {
    private UUID id;
    private String passportNumber;
    private String firstName;
    private String surname;
    private String patronymic;
    private Date birthDate;
}
