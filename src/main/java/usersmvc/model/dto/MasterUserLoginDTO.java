package usersmvc.model.dto;

public class MasterUserLoginDTO {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public MasterUserLoginDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public MasterUserLoginDTO setPassword(String password) {
        this.password = password;
        return this;
    }

}
