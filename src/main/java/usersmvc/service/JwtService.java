package usersmvc.service;

import java.util.Map;

public interface JwtService {

    public String generateToken(String userId, Map<String, Object> claims);

}
