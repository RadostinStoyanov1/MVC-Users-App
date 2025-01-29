package usersmvc.model.dto;

import java.time.LocalDate;
import java.util.UUID;

public class UserDTO {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String phoneNumber;
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
}
