package com.duke.carregistration.mappers;

import com.duke.carregistration.dto.PersonDto;
import com.duke.carregistration.dto.PersonWithCarsDto;
import com.duke.carregistration.entity.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonMapper {
    public PersonDto toDto(Person person) {
        PersonDto dto = new PersonDto();
        dto.setId(person.getId());
        dto.setPassportNumber(person.getPassportNumber());
        dto.setFirstName(person.getFirstName());
        dto.setSurname(person.getSurname());
        dto.setPatronymic(person.getPatronymic());
        dto.setBirthDate(person.getBirthDate());
        return dto;
    }

    public PersonWithCarsDto toDtoWithCars(Person person) {
        PersonWithCarsDto dto = new PersonWithCarsDto();
        dto.setId(person.getId());
        dto.setPassportNumber(person.getPassportNumber());
        dto.setFirstName(person.getFirstName());
        dto.setSurname(person.getSurname());
        dto.setPatronymic(person.getPatronymic());
        dto.setBirthDate(person.getBirthDate());
        CarMapper carMapper = new CarMapper();
        dto.setCars(carMapper.toDtoCarsList(person.getCars()));
        return dto;
    }

    public Person toEntity(PersonDto dto) {
        Person person = new Person();
        person.setId(dto.getId());
        person.setPassportNumber(dto.getPassportNumber());
        person.setFirstName(dto.getFirstName());
        person.setSurname(dto.getSurname());
        person.setPatronymic(dto.getPatronymic());
        person.setBirthDate(dto.getBirthDate());
        return person;
    }

    public List<PersonDto> toDtoPersonsList(List<Person> persons) {
        List<PersonDto> personsListDto = new ArrayList<>();
        for (Person person : persons) {
            personsListDto.add(this.toDto(person));
        }
        return personsListDto;
    }
}
