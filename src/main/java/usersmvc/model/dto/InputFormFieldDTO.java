package usersmvc.model.dto;

public class InputFormFieldDTO {
    private final String pattern;

    public String getPattern() {
        return pattern;
    }

    InputFormFieldDTO(Builder builder) {
        this.pattern = builder.pattern;
    }

    public static class Builder {
        private String pattern;

        public Builder pattern(String pattern) {
            this.pattern = pattern;
            return this;
        }

        public InputFormFieldDTO build() {
            return new InputFormFieldDTO(this);
        }
    }
}
