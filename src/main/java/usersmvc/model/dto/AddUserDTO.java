package usersmvc.model.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import usersmvc.validation.annotations.UniqueEmail;
import usersmvc.validation.annotations.UniquePhone;

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
    @UniquePhone
    private String phoneNumber;

    @Email(regexp = ".*@.*")
    @UniqueEmail
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public AddUserDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AddUserDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public AddUserDTO setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public AddUserDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AddUserDTO setEmail(String email) {
        this.email = email;
        return this;
    }
}
