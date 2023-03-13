package com.duke.carregistration;

import com.duke.carregistration.entity.Car;
import com.duke.carregistration.entity.Person;
import com.duke.carregistration.repository.CarRepository;
import com.duke.carregistration.repository.PersonRepository;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

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
    void addTwoPersonalCar() {
        Person person = new Person();
        person.setPassportNumber("202020");
        person.setFirstName("Gustav");
        person.setSurname("Klimt");
        person = personRepository.save(person);
        personId = person.getId();

        {
            Car car = new Car();
            car.setBrand("Audi");
            car.setColor("black");
            car.setNumber("8888");
            person.addCar(car);
        }
        {
            Car car = new Car();
            car.setBrand("Mercedes");
            car.setColor("white");
            car.setNumber("9000");
            person.addCar(car);
        }
        {
            Car car = new Car();
            car.setBrand("Chevrolet");
            car.setColor("blue");
            car.setNumber("5050");
            person.addCar(car);
        }
        personRepository.save(person);
    }

    @Test
    @Rollback(value = false)
    void checkAddedCars() {
        Person person = personRepository.findById(personId).orElseThrow();
        person.getCars().size();
        System.out.println("person.getCars().size() = " + person.getCars().size());
        assertEquals(3, person.getCars().size());
    }
}
