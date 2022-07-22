package report.model;

public class Patient {

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
}
