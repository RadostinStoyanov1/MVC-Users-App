package usersmvc.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import usersmvc.config.UsersRestApiConfig;
import usersmvc.model.dto.*;
import usersmvc.service.UserEntityService;
import usersmvc.service.exception.ExistingEmailOrPhoneException;
import usersmvc.service.exception.UserNotFoundException;

import java.util.List;
import java.util.UUID;

@Service
public class UserEntityServiceImpl implements UserEntityService {

    private final RestClient usersRestClient;
    private final UsersRestApiConfig usersRestApiConfig;
    private final ModelMapper modelMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(UserEntityServiceImpl.class);

    public UserEntityServiceImpl(RestClient usersRestClient, UsersRestApiConfig usersRestApiConfig, ModelMapper modelMapper) {
        this.usersRestClient = usersRestClient;
        this.usersRestApiConfig = usersRestApiConfig;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserSummaryDTO> getAllUsersSummary() {
        LOGGER.info("Getting all users...");
        String emptyPattern = "";

        return getAllUsersAsUserDTO(emptyPattern)
                .stream()
                .map(this::mapUserDTOToUserSummaryDTO)
                .toList();
    }

    @Override
    public List<UserSummaryDTO> getAllSearchedUsersSummary(String pattern) {
        LOGGER.info("Getting all users...");

        return getAllUsersAsUserDTO(pattern)
                .stream()
                .map(userDTO -> mapUserDTOToUserSummaryDTO(userDTO))
                .toList();
    }

    @Override
    public List<UserDTO> getAllUsersAsUserDTO(String pattern) {
        return usersRestClient
                .get()
                .uri(usersRestApiConfig.getBaseUrl() + "/all?pattern={pattern}", pattern)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
    }

    @Override
    public UserDTO getUserByUuid(UUID id) {

        return usersRestClient
                .get()
                .uri(usersRestApiConfig.getBaseUrl() + "/{id}", id.toString())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(s -> s.isSameCodeAs(HttpStatusCode.valueOf(404)),
                        (req, resp) -> {
                            throw new UserNotFoundException("User with chosen id does not exist");
                        })
                .body(UserDTO.class);
    }

    @Override
    public void addUser(AddUserDTO addUserDTO) {
        usersRestClient
                .post()
                .uri(usersRestApiConfig.getBaseUrl())
                .body(addUserDTO)
                .retrieve()
                .onStatus(s -> s.isSameCodeAs(HttpStatusCode.valueOf(500)),
                        (req, resp) -> {
                            throw new ExistingEmailOrPhoneException("User with chosen email and/or phone number already exists");
                        })
                .body(UserDTO.class);
    }

    @Override
    public void updateUser(UpdateUserDTO updateUserDTO) {
        usersRestClient
                .put()
                .uri(usersRestApiConfig.getBaseUrl())
                .body(updateUserDTO)
                .retrieve()
                .onStatus(s -> s.isSameCodeAs(HttpStatusCode.valueOf(500)),
                        (req, resp) -> {
                            throw new ExistingEmailOrPhoneException("User with chosen email and/or phone number already exists");
                        })
                .onStatus(s -> s.isSameCodeAs(HttpStatusCode.valueOf(404)),
                        (req, resp) -> {
                            throw new UserNotFoundException("User with chosen id does not exist");
                        })
                .body(UserDTO.class);
    }

    @Override
    public void deleteUser(UUID id) {
        usersRestClient
                .delete()
                .uri(usersRestApiConfig.getBaseUrl() + "/{id}", id.toString())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(s -> s.isSameCodeAs(HttpStatusCode.valueOf(404)),
                        (req, resp) -> {
                            throw new UserNotFoundException("User with chosen id does not exist");
                        })
                .body(BooleanResultDTO.class);
    }

    private UserSummaryDTO mapUserDTOToUserSummaryDTO(UserDTO userDTO) {

        return new UserSummaryDTO.Builder()
                .uuid(userDTO.getUuid())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .birthDate(userDTO.getBirthDate())
                .build();
    }
}
