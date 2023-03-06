package com.duke.carregistration.controllers;

import com.duke.carregistration.entity.Person;
import com.duke.carregistration.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/")
@RequiredArgsConstructor
public class PersonController {
    private final PersonRepository personRepository;
    @GetMapping(value = "persons-hou")
    public String getAllUsers() {
        return "hou hou";
    }
    @GetMapping(value = "persons")
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }
    @GetMapping(value = "add_person")
    public Person addPerson(@RequestParam(name = "passport", required = false) String passportNumber,
                            @RequestParam(name = "name", required = false) String firstName,
                            @RequestParam(name = "surname", required = false) String surname) {
        Person person = new Person();
        person.setPassportNumber(passportNumber);
        person.setFirstName(firstName);
        person.setSurname(surname);
        personRepository.save(person);
        return  person;
    }

}
