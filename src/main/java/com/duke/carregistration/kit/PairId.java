package com.duke.carregistration.kit;

import lombok.Data;

import java.util.UUID;

@Data
public class PairId {
    private UUID personId;
    private UUID carId;
}
