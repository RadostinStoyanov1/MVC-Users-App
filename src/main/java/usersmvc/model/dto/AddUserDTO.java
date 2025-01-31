package usersmvc.model.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class AddUserDTO {
    @NotEmpty
    @Size(min = 2, max = 30)
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 30)
    private String lastName;

    @NotNull
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Pattern(regexp = "[0-9]+", message = "Phone number must contain 10 digits only")
    @Size(min = 10, max = 10)
    private String phoneNumber;

    @Email
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public AddUserDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public AddUserDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public AddUserDTO setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public AddUserDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public AddUserDTO setEmail(String email) {
        this.email = email;
        return this;
    }
}
