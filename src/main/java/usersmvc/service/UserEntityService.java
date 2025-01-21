package usersmvc.service;

import usersmvc.model.dto.UserDTO;
import usersmvc.model.dto.UserSummaryDTO;

import java.util.List;

public interface UserEntityService {
    public boolean isPhoneNumberUnique(String username);
    public boolean isEmailUnique(String email);
    public List<UserSummaryDTO> getALlUsersSummary();
    public List<UserDTO> getAllUsersAsUserDTO(String pattern);
}
