package usersmvc.service;

public interface UserEntityService {
    public boolean isPhoneNumberUnique(String username);
    public boolean isEmailUnique(String email);
}
