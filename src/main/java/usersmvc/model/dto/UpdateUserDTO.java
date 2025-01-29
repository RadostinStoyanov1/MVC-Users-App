package usersmvc.model.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

public class UpdateUserDTO {
    @NotNull
    private UUID uuid;

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

    @Pattern(regexp = "[0-9]+")
    @Size(min = 10, max = 10)
    private String phoneNumber;

    @Email
    private String email;

    public UUID getUuid() {
        return uuid;
    }

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

    public UpdateUserDTO setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public UpdateUserDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UpdateUserDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UpdateUserDTO setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public UpdateUserDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UpdateUserDTO setEmail(String email) {
        this.email = email;
        return this;
    }
}
