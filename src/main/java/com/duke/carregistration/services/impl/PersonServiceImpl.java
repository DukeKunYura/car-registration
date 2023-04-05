package com.duke.carregistration.services.impl;

import com.duke.carregistration.dto.PersonDto;
import com.duke.carregistration.dto.PersonWithCarsDto;
import com.duke.carregistration.entity.Person;
import com.duke.carregistration.exceptions.ServerException;
import com.duke.carregistration.mappers.PersonMapper;
import com.duke.carregistration.repository.PersonRepository;
import com.duke.carregistration.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public List<PersonDto> getAllPersons() {
        List<Person> personList = personRepository.findAll();
        List<PersonDto> personsListDto = new ArrayList<>();
        for (Person person : personList) {
            personsListDto.add(personMapper.toDto(person));
        }
        return personsListDto;
    }

    public PersonDto getByPassport(String passportNumber) {
        Person person = personRepository.findByPassportNumber(passportNumber);
        return personMapper.toDto(person);
    }

    public PersonWithCarsDto getPersonWithCarsByPassport(String passportNumber) {
        Person person = personRepository.findByPassportNumber(passportNumber);
        return personMapper.toDtoWithCars(person);
    }

    public void addPerson(PersonDto dto) {
//        Person personExists = personRepository.findByPassportNumber(dto.getPassportNumber());
//        if (personExists == null) {
//            Person person = personMapper.toEntity(dto);
//            personRepository.save(person);
//        } else {
//            throw new ServerException();
//        }
        Person person = personMapper.toEntity(dto);
        personRepository.save(person);
    }

    public void updatePerson(String passportNumber, PersonDto dto) {
        Person personExists = personRepository.findByPassportNumber(passportNumber);
            personExists.setPassportNumber(dto.getPassportNumber());
            personExists.setFirstName(dto.getFirstName());
            personExists.setSurname(dto.getSurname());
            personExists.setPatronymic(dto.getPatronymic());
            personRepository.save(personExists);
    }

    public void deletePersonWithPassportNumber(String passportNumber) {
        Person person = personRepository.findByPassportNumber(passportNumber);
        personRepository.delete(person);
    }
}
