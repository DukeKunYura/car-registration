package com.duke.carregistration.mappers;
import com.duke.carregistration.dto.PersonDto;
import com.duke.carregistration.dto.PersonWithCarsDto;
import com.duke.carregistration.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
    public PersonDto toDto(Person person) {
        PersonDto dto = new PersonDto();
        dto.setId(person.getId());
        dto.setPassportNumber(person.getPassportNumber());
        dto.setFirstName(person.getFirstName());
        dto.setSurname(person.getSurname());
        dto.setPatronymic(person.getPatronymic());
        return dto;
    }

    public PersonWithCarsDto toDtoWithCars(Person person) {
        PersonWithCarsDto dto = new PersonWithCarsDto();
        dto.setId(person.getId());
        dto.setPassportNumber(person.getPassportNumber());
        dto.setFirstName(person.getFirstName());
        dto.setSurname(person.getSurname());
        dto.setPatronymic(person.getPatronymic());
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
        return person;
    }
}
