package com.duke.carregistration.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import com.duke.carregistration.dto.CarDto;
import com.duke.carregistration.dto.CarWithPersonDto;

import java.util.UUID;

@Entity
@Table(name = "cars")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Car {
    @Id
    @Column(name = "car_id")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    @EqualsAndHashCode.Include
    private UUID id;
    @Column(name = "number")
    private String number;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
    @Column(name = "color")
    private String color;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public CarDto toDto(Car car) {
        CarDto dto = new CarDto();
        dto.setId(car.getId());
        dto.setNumber(car.getNumber());
        dto.setBrand(car.getBrand());
        dto.setModel(car.getModel());
        dto.setColor(car.getColor());
        return dto;
    }

    public CarWithPersonDto toDtoWithPerson(Car car) {
        CarWithPersonDto dto = new CarWithPersonDto();
        dto.setId(car.getId());
        dto.setNumber(car.getNumber());
        dto.setBrand(car.getBrand());
        dto.setModel(car.getModel());
        dto.setColor(car.getColor());
        dto.setPerson(car.getPerson());
        return dto;
    }
}
