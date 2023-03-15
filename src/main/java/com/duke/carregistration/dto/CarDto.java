package com.duke.carregistration.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class CarDto {
    private UUID id;
    private String number;
    private String brand;
    private String model;
    private String color;
}
