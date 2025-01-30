package usersmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.client.RestClient;
import usersmvc.service.JwtService;
import usersmvc.service.MasterUserService;

import java.util.Map;

@Configuration
public class RestConfig {

    @Bean("usersRestClient")
    public RestClient usersRestClient(UsersRestApiConfig usersRestApiConfig, ClientHttpRequestInterceptor requestInterceptor) {
        return RestClient
                .builder()
                .baseUrl(usersRestApiConfig.getBaseUrl())
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .requestInterceptor(requestInterceptor)
                .build();
    }

    @Bean
    public ClientHttpRequestInterceptor requestInterceptor(MasterUserService masterUserService,
                                                           JwtService jwtService) {
        return (r, b, e) -> {
            // put the logged user details into bearer token
            masterUserService
                    .getCurrentMasterUser()
                    .ifPresent(mud -> {
                        String bearerToken = jwtService.generateToken(
                                mud.getUuid().toString(),
                                Map.of(
                                        "roles",
                                        mud.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
                                )
                        );
                        r.getHeaders().setBearerAuth(bearerToken);
                    });

            return e.execute(r, b);
        };
    }

}
