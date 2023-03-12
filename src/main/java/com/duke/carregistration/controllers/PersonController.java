package com.duke.carregistration.controllers;

import com.duke.carregistration.dto.PersonDto;
import com.duke.carregistration.entity.Person;
import com.duke.carregistration.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;
    @GetMapping(value = "persons-hou")
    public String getHou() {
        return personService.hou();
    }
    @GetMapping(value = "persons")
    public List<Person> getAll() {
        return personService.getAllPersons();
    }
    @GetMapping(value = "person_with")
    public PersonDto findWithPassportNumber(@RequestParam(name = "passport", required = false) String passportNumber) {
        return personService.getByPassport(passportNumber);
    }
    @GetMapping(value = "add_person")
    public PersonDto addPerson(@RequestParam(name = "passport", required = false) String passportNumber,
                               @RequestParam(name = "name", required = false) String firstName,
                               @RequestParam(name = "surname", required = false) String surname) {
        PersonDto dto = new PersonDto();
        dto.setPassportNumber(passportNumber);
        dto.setFirstName(firstName);
        dto.setSurname(surname);
        personService.addPerson(dto);
        return  dto;
    }

}
