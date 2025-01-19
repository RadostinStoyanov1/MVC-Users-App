package usersmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class RestConfig {
    @Bean("usersRestClient")
    public RestClient usersRestClient(UsersRestApiConfig usersRestApiConfig) {
        return RestClient
                .builder()
                .baseUrl(usersRestApiConfig.getBaseUrl())
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
