package usersmvc.service;

import usersmvc.model.dto.AddUserDTO;
import usersmvc.model.dto.UpdateUserDTO;
import usersmvc.model.dto.UserDTO;
import usersmvc.model.dto.UserSummaryDTO;

import java.util.List;
import java.util.UUID;

public interface UserEntityService {

    public List<UserSummaryDTO> getAllUsersSummary();
    public List<UserSummaryDTO> getAllSearchedUsersSummary(String pattern);
    public List<UserDTO> getAllUsersAsUserDTO(String pattern);
    public UserDTO getUserByUuid(UUID id);
    public void addUser(AddUserDTO addUserDTO);
    public void updateUser(UpdateUserDTO updateUserDTO);
    public void deleteUser(UUID id);

}
