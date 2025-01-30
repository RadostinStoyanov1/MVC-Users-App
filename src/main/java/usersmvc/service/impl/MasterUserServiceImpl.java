package usersmvc.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import usersmvc.model.user.AppMasterUserDetails;
import usersmvc.service.MasterUserService;

import java.util.Optional;

@Service
public class MasterUserServiceImpl implements MasterUserService {
    @Override
    public Optional<AppMasterUserDetails> getCurrentMasterUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof AppMasterUserDetails appMasterUserDetails) {
            return Optional.of(appMasterUserDetails);
        }
        return Optional.empty();
    }
}
