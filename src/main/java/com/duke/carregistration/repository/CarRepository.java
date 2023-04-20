package com.duke.carregistration.repository;

import com.duke.carregistration.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CarRepository extends JpaRepository<Car, UUID> {
    Car findByNumber(String number);
    Car findCarById(UUID id);
    @Query("select c from Car c left join fetch c.persons where c.id = :id")
    Car findCarByIdWithPersons(UUID id);
    @Query(value = "SELECT COUNT(car_id) AS car_count FROM Cars c", nativeQuery = true)
    Long getCarsCount();
}
