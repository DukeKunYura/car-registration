package com.duke.carregistration.dto;

import com.duke.carregistration.entity.Person;
import lombok.Data;

import java.util.UUID;

@Data
public class PersonDto {
    private UUID id;
    private String passportNumber;
    private String firstName;
    private String surname;
    private String patronymic;

    public Person toEntity(PersonDto dto) {
        Person person = new Person();
        person.setId(dto.getId());
        person.setPassportNumber(dto.getPassportNumber());
        person.setFirstName(dto.getFirstName());
        person.setSurname(dto.getSurname());
        person.setPatronymic(dto.getPatronymic());
        return person;
    }
}
