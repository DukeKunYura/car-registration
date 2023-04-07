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
    private UUID personId;

    @PostConstruct
    void addThreePersonalCar() {
        Person person = new Person();
        person.setPassportNumber("202020");
        person.setFirstName("Gustav");
        person.setSurname("Klimt");
        person = personRepository.save(person);
        personId = person.getId();

        Car audi = new Car();
        audi.setBrand("Audi");
        audi.setColor("black");
        audi.setNumber("8888");
        carRepository.save(audi);

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

        person.getCars().add(audi);
        person.getCars().add(mercedes);
        person.getCars().add(chevrolet);

        personRepository.save(person);
    }

    @Test
    void checkAddedCars() {
        Person person = personRepository.findById(personId).orElseThrow();
        person.getCars().size();
        System.out.println("person.getCars().size() = " + person.getCars().size());
        assertEquals(3, person.getCars().size());
    }
}
