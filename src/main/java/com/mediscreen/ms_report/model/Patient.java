package com.mediscreen.ms_report.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Data
public class Patient {

    private static final String BIRTHDATE_PATTERN = "yyyy-MM-dd";

    private int patientId;
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Pattern(regexp = "^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$")
    private String dateOfBirth;

    @NotNull
    private Sex sex;

    private String address;
    private String phone;

    public Patient() { }

    public Patient(String firstName, String lastName, String dateOfBirth, Sex sex, String address, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
    }

    public int getAge(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(BIRTHDATE_PATTERN);
        LocalDate birth = LocalDate.parse(dateOfBirth, formatter);
        LocalDate now = LocalDate.now();
        return Period.between(birth, now).getYears();
    }
}
