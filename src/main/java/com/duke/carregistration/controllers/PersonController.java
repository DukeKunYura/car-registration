package com.duke.carregistration.controllers;

import com.duke.carregistration.entity.Person;
import com.duke.carregistration.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/")
@RequiredArgsConstructor
public class PersonController {
    @GetMapping(value = "persons")
    public String getAllUsers() {
        return "hou hou";
    }
}
