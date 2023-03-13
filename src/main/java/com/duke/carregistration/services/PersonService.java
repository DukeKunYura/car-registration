package com.duke.carregistration.services;

import com.duke.carregistration.dto.PersonDto;
import com.duke.carregistration.entity.Person;
import com.duke.carregistration.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public String hou() {
        return "hou hou";
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public PersonDto getByPassport(String passportNumber) {
        Person person = personRepository.findByPassportNumber(passportNumber);
        return person.toDto(person);
    }

    public void addPerson(PersonDto dto) {
        Person person = dto.toEntity(dto);
        personRepository.save(person);
    }

}
