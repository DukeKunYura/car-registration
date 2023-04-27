package com.duke.carregistration.filters;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class PersonFilter {
    private UUID id;
    private String passportNumber;
    private String firstName;
    private String surname;
    private String patronymic;
    private LocalDate birthDate;
    private LocalDate minAgeDate;
    private LocalDate maxAgeDate;

    public boolean isEmpty() {
        return this.id == null
                && this.passportNumber == null
                && this.firstName == null
                && this.surname == null
                && this.patronymic == null
                && this.birthDate == null
                && this.minAgeDate == null
                && this.maxAgeDate == null;
    }
}
