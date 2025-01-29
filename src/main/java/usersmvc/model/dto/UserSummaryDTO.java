package usersmvc.model.dto;

import java.time.LocalDate;
import java.util.UUID;

public class UserSummaryDTO {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public UUID getUuid() {
        return uuid;
    }

    UserSummaryDTO(Builder builder) {
        this.uuid = builder.uuid;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.birthDate = builder.birthDate;
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

    public static class Builder {
        private UUID uuid;
        private String firstName;
        private String lastName;
        private LocalDate birthDate;

        public Builder uuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder birthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public UserSummaryDTO build() {
            return new UserSummaryDTO(this);
        }
    }
}
