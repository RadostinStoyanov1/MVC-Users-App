package usersmvc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "users-rest.api")
public class UsersRestApiConfig {
    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public UsersRestApiConfig setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }
}
