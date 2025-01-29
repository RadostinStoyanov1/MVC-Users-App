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
    public boolean isPhoneNumberUnique(String phone) {
        BooleanResultDTO booleanResultDTO = usersRestClient
                .get()
                .uri(usersRestApiConfig.getBaseUrl() + "/by-phone?pattern={phone}", phone)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(BooleanResultDTO.class);

        return booleanResultDTO.getData();
    }

    @Override
    public boolean isEmailUnique(String email) {
        BooleanResultDTO booleanResultDTO = usersRestClient
                .get()
                .uri(usersRestApiConfig.getBaseUrl() + "/by-email?pattern={email}", email)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(BooleanResultDTO.class);
        return booleanResultDTO.getData();
    }

    @Override
    public List<UserSummaryDTO> getAllUsersSummary() {
        LOGGER.info("Getting all users...");
        String emptyPattern = "";

        return getAllUsersAsUserDTO(emptyPattern)
                .stream()
                .map(userDTO -> modelMapper.map(userDTO, UserSummaryDTO.class))
                .toList();
    }

    @Override
    public List<UserSummaryDTO> getAllSearchedUsersSummary(String pattern) {
        LOGGER.info("Getting all users...");

        return getAllUsersAsUserDTO(pattern)
                .stream()
                .map(userDTO -> modelMapper.map(userDTO, UserSummaryDTO.class))
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
    public UserDTO getUserById(Long id) {
        return usersRestClient
                .get()
                .uri(usersRestApiConfig.getBaseUrl() + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(s -> s.isSameCodeAs(HttpStatusCode.valueOf(404)),
                        (req, resp) -> {
                            throw new UserNotFoundException("User with chosen id does not exist", id);
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
                            throw new UserNotFoundException("User with chosen id does not exist", updateUserDTO.getId());
                        })
                .body(UserDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
        usersRestClient
                .delete()
                .uri(usersRestApiConfig.getBaseUrl() + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(s -> s.isSameCodeAs(HttpStatusCode.valueOf(404)),
                        (req, resp) -> {
                            throw new UserNotFoundException("User with chosen id does not exist", id);
                        })
                .body(BooleanResultDTO.class);
    }
}
