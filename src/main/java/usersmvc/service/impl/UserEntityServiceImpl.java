package usersmvc.service.impl;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import usersmvc.config.UsersRestApiConfig;
import usersmvc.model.dto.BooleanResultDTO;
import usersmvc.service.UserEntityService;

@Service
public class UserEntityServiceImpl implements UserEntityService {
    private static final String UNIQUE = "UNIQUE";
    private static final String USED = "USED";

    private final RestClient usersRestClient;
    private final UsersRestApiConfig usersRestApiConfig;

    public UserEntityServiceImpl(RestClient usersRestClient, UsersRestApiConfig usersRestApiConfig) {
        this.usersRestClient = usersRestClient;
        this.usersRestApiConfig = usersRestApiConfig;
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
}
