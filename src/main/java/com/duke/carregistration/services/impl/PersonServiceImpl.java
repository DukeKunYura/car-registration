package com.duke.carregistration.services.impl;

import com.duke.carregistration.dto.PersonDto;
import com.duke.carregistration.dto.PersonWithCarsDto;
import com.duke.carregistration.entity.Person;
import com.duke.carregistration.exceptions.ServerException;
import com.duke.carregistration.filters.PersonFilter;
import com.duke.carregistration.mappers.PersonMapper;
import com.duke.carregistration.repository.PersonRepository;
import com.duke.carregistration.repository.impl.PersonRepositoryCustomImpl;
import com.duke.carregistration.services.PersonService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final PersonRepositoryCustomImpl personRepositoryCustom;

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
        List<Person> persons = personRepository.findAll(Example.of(personMapper.toEntity(dto)));
        return personMapper.toDtoPersonsList(persons);
    }

    public List<PersonDto> getPersonsWithParamsAndAge(PersonFilter filter) {
        List<Person> persons = personRepositoryCustom.findPersonsByFilter(filter);
        return personMapper.toDtoPersonsList(persons);
    }

    public List<PersonDto> getPersonsWithParamsMonster(PersonDto dto, String age) {
        String firstName = dto.getFirstName();
        String surname = dto.getSurname();
        String patronymic = dto.getPatronymic();
        List<Person> personsList;
        if (firstName != null && surname != null && patronymic != null && age != null) {
            LocalDate startDate = LocalDate.now().minusYears(Integer.parseInt(age) + 1);
            LocalDate endDate = startDate.plusYears(1);
            personsList = personRepository.findByFirstNameAndSurnameAndPatronymicAndBirthDateBetween(
                    firstName, surname, patronymic, startDate, endDate);
        } else if (firstName != null && surname != null && patronymic != null) {
            personsList = personRepository.findByFirstNameAndSurnameAndPatronymic(firstName, surname, patronymic);
        } else if (firstName != null && surname != null && age != null) {
            LocalDate startDate = LocalDate.now().minusYears(Integer.parseInt(age) + 1);
            LocalDate endDate = startDate.plusYears(1);
            personsList = personRepository.findByFirstNameAndSurnameAndBirthDateBetween(firstName, surname, startDate,
                    endDate);
        } else if (firstName != null && patronymic != null && age != null) {
            LocalDate startDate = LocalDate.now().minusYears(Integer.parseInt(age) + 1);
            LocalDate endDate = startDate.plusYears(1);
            personsList = personRepository.findByFirstNameAndPatronymicAndBirthDateBetween(firstName, patronymic,
                    startDate, endDate);
        } else if (surname != null && patronymic != null && age != null) {
            LocalDate startDate = LocalDate.now().minusYears(Integer.parseInt(age) + 1);
            LocalDate endDate = startDate.plusYears(1);
            personsList = personRepository.findBySurnameAndPatronymicAndBirthDateBetween(surname, patronymic, startDate,
                    endDate);
        } else if (firstName != null && age != null) {
            LocalDate startDate = LocalDate.now().minusYears(Integer.parseInt(age) + 1);
            LocalDate endDate = startDate.plusYears(1);
            personsList = personRepository.findByFirstNameAndBirthDateBetween(firstName, startDate, endDate);
        } else if (surname != null && age != null) {
            LocalDate startDate = LocalDate.now().minusYears(Integer.parseInt(age) + 1);
            LocalDate endDate = startDate.plusYears(1);
            personsList = personRepository.findBySurnameAndBirthDateBetween(surname, startDate, endDate);
        } else if (patronymic != null && age != null) {
            LocalDate startDate = LocalDate.now().minusYears(Integer.parseInt(age) + 1);
            LocalDate endDate = startDate.plusYears(1);
            personsList = personRepository.findByPatronymicAndBirthDateBetween(patronymic, startDate, endDate);
        } else if (firstName != null && surname != null) {
            personsList = personRepository.findByFirstNameAndSurname(firstName, surname);
        } else if (firstName != null && patronymic != null) {
            personsList = personRepository.findByFirstNameAndPatronymic(firstName, patronymic);
        } else if (surname != null && patronymic != null) {
            personsList = personRepository.findBySurnameAndPatronymic(surname, patronymic);
        } else if (age != null) {
            LocalDate startDate = LocalDate.now().minusYears(Integer.parseInt(age) + 1);
            LocalDate endDate = startDate.plusYears(1);
            personsList = personRepository.findByBirthDateBetween(startDate, endDate);
        } else if (firstName != null) {
            personsList = personRepository.findByFirstName(firstName);
        } else if (surname != null) {
            personsList = personRepository.findBySurname(surname);
        } else if (patronymic != null) {
            personsList = personRepository.findByPatronymic(patronymic);
        } else {
            personsList = personRepository.findAll();
        }
        List<PersonDto> personsListDto = new ArrayList<>();
        for (Person person : personsList) {
            personsListDto.add(personMapper.toDto(person));
        }
        return personsListDto;
    }

    public int getPersonAge(UUID id) {
        Person person = personRepository.findById(id).orElseThrow();
        LocalDate birthDate = person.getBirthDate();
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
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
