package com.duke.carregistration.repository;

import com.duke.carregistration.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CarRepository extends JpaRepository<Car, UUID> {
    Car findByNumber(String number);
}
