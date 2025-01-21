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

    public UserSummaryDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserSummaryDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserSummaryDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public UserSummaryDTO setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }
}
