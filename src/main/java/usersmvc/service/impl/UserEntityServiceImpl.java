package usersmvc.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import usersmvc.config.UsersRestApiConfig;
import usersmvc.model.dto.BooleanResultDTO;
import usersmvc.model.dto.UserDTO;
import usersmvc.model.dto.UserSummaryDTO;
import usersmvc.service.UserEntityService;

import java.util.List;

@Service
public class UserEntityServiceImpl implements UserEntityService {
    private static final String UNIQUE = "UNIQUE";

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
        return UNIQUE.equals(booleanResultDTO.getResult());
    }

    @Override
    public boolean isEmailUnique(String email) {
        BooleanResultDTO booleanResultDTO = usersRestClient
                .get()
                .uri(usersRestApiConfig.getBaseUrl() + "/by-email?pattern={email}", email)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(BooleanResultDTO.class);
        return UNIQUE.equals(booleanResultDTO.getResult());
    }

    @Override
    public List<UserSummaryDTO> getALlUsersSummary() {
        LOGGER.info("Getting all users...");
        String emptyPattern = "";

        return getAllUsersAsUserDTO(emptyPattern)
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
}
