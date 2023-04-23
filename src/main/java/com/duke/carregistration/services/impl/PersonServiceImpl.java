package com.duke.carregistration.services.impl;

import com.duke.carregistration.dto.PersonDto;
import com.duke.carregistration.dto.PersonWithCarsDto;
import com.duke.carregistration.entity.Person;
import com.duke.carregistration.mappers.PersonMapper;
import com.duke.carregistration.repository.PersonRepository;
import com.duke.carregistration.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public List<PersonDto> getAllPersons() {
        List<Person> personsList = personRepository.findAll();
        List<PersonDto> personsListDto = new ArrayList<>();
        for (Person person : personsList) {
            personsListDto.add(personMapper.toDto(person));
        }
        return personsListDto;
    }

    public Long getAllPersonsCount() {
        return personRepository.getPersonsCount();
    }

    public List<PersonDto> getPersonsWithParams(PersonDto dto) {
        String firstName = dto.getFirstName();
        String surname = dto.getSurname();
        String patronymic = dto.getPatronymic();
        List<Person> personsList = personRepository.findByFirstNameAndSurnameAndPatronymic(firstName, surname, patronymic);
        List<PersonDto> personsListDto = new ArrayList<>();
        for (Person person : personsList) {
            personsListDto.add(personMapper.toDto(person));
        }
        return personsListDto;
    }

    public PersonDto getPersonById(UUID id) {
        Person person = personRepository.findById(id).orElseThrow();
        return personMapper.toDto(person);
    }

    public PersonWithCarsDto getPersonWithCars(UUID id) {
        Person person = personRepository.findPersonByIdWithCars(id);
        return personMapper.toDtoWithCars(person);
    }

    public PersonDto getByPassport(String passportNumber) {
        Person person = personRepository.findByPassportNumber(passportNumber);
        return personMapper.toDto(person);
    }

    public void addPerson(PersonDto dto) {
        Person person = personMapper.toEntity(dto);
        personRepository.save(person);
    }

    public void deletePerson(UUID id) {
        Person person = personRepository.findById(id).orElseThrow();
        personRepository.delete(person);
    }

    public void updatePerson(UUID id, PersonDto dto) {
        Person person = personRepository.findById(id).orElseThrow();
        person.setFirstName(dto.getFirstName());
        person.setSurname(dto.getSurname());
        person.setPatronymic(dto.getPatronymic());
        personRepository.save(person);
    }
}
