package com.duke.carregistration.entity;

import com.duke.carregistration.dto.CarDto;
import com.duke.carregistration.dto.PersonDto;
import com.duke.carregistration.dto.PersonWithCarsDto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "persons")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person {
    @Id
    @Column(name = "person_id")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    @EqualsAndHashCode.Include
    private UUID id;
    @Column(name = "passport_number")
    private String passportNumber;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "surname")
    private String surname;
    @Column(name = "patronymic")
    private String patronymic;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Car> cars = new ArrayList<>();

    public void addCar(Car car) {
        cars.add(car);
        car.setPerson(this);
    }

    public void removeCar(Car car) {
        cars.remove(car);
        car.setPerson(null);
    }

    public PersonDto toDto(Person person) {
        PersonDto dto = new PersonDto();
        dto.setId(person.getId());
        dto.setPassportNumber(person.getPassportNumber());
        dto.setFirstName(person.getFirstName());
        dto.setSurname(person.getSurname());
        dto.setPatronymic(person.getPatronymic());
        return dto;
    }

    public PersonWithCarsDto toDtoWithCars(Person person) {
        PersonWithCarsDto dto = new PersonWithCarsDto();
        dto.setId(person.getId());
        dto.setPassportNumber(person.getPassportNumber());
        dto.setFirstName(person.getFirstName());
        dto.setSurname(person.getSurname());
        dto.setPatronymic(person.getPatronymic());
        dto.setCars(person.getCars());
        return dto;
    }
}
