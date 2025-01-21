package usersmvc.service;

import usersmvc.model.dto.AddUserDTO;
import usersmvc.model.dto.UserDTO;
import usersmvc.model.dto.UserSummaryDTO;

import java.util.List;

public interface UserEntityService {
    public boolean isPhoneNumberUnique(String username);
    public boolean isEmailUnique(String email);
    public List<UserSummaryDTO> getAllUsersSummary();
    public List<UserSummaryDTO> getAllSearchedUsersSummary(String pattern);
    public List<UserDTO> getAllUsersAsUserDTO(String pattern);
    public UserDTO getUserById(Long id);
    public void register(AddUserDTO addUserDTO);

}
