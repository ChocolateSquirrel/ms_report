package report.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Data
public class Patient {

    private static final String BIRTHDATE_PATTERN = "yyyy-MM-dd";

    private int patientId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
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
