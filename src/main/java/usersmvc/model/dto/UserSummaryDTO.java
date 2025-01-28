package usersmvc.model.dto;

import java.time.LocalDate;

public class UserSummaryDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public Long getId() {
        return id;
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
}
