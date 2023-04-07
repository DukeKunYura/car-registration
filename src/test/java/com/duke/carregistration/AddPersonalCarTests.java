package com.duke.carregistration;

import com.duke.carregistration.entity.Car;
import com.duke.carregistration.entity.Person;
import com.duke.carregistration.repository.CarRepository;
import com.duke.carregistration.repository.PersonRepository;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class AddPersonalCarTests {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    CarRepository carRepository;
    private UUID klimtId, daliId, audiId;

    @PostConstruct
    void addThreePersonalCar() {
        Person klimt = new Person();
        klimt.setPassportNumber("202020");
        klimt.setFirstName("Gustav");
        klimt.setSurname("Klimt");
        klimt = personRepository.save(klimt);
        klimtId = klimt.getId();

        Person dali = new Person();
        dali.setPassportNumber("202020");
        dali.setFirstName("Salvador");
        dali.setSurname("Dali");
        dali = personRepository.save(dali);
        daliId = dali.getId();

        Car audi = new Car();
        audi.setBrand("Audi");
        audi.setColor("black");
        audi.setNumber("8888");
        carRepository.save(audi);
        audiId = audi.getId();

        Car mercedes = new Car();
        mercedes.setBrand("Mercedes");
        mercedes.setColor("white");
        mercedes.setNumber("9000");
        carRepository.save(mercedes);

        Car chevrolet = new Car();
        chevrolet.setBrand("Chevrolet");
        chevrolet.setColor("blue");
        chevrolet.setNumber("5050");
        carRepository.save(chevrolet);

        klimt.getCars().add(audi);
        klimt.getCars().add(mercedes);
        klimt.getCars().add(chevrolet);

        dali.getCars().add(audi);

        personRepository.save(klimt);
        personRepository.save(dali);
    }

    @Test
    void checkAddedCars() {
        Person person = personRepository.findById(klimtId).orElseThrow();
        person.getCars().size();
        assertEquals(3, person.getCars().size());

        Car car = carRepository.findCarById(audiId);
        assertEquals(2, car.getPersons().size());

    }
}
