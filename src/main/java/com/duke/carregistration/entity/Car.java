package com.duke.carregistration.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;
@Entity
@Table(name = "cars")
@Data
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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
    private  String model;
    @Column(name = "color")
    private  String color;
    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car )) return false;
        return id != null && id.equals(((Car) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
