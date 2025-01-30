package usersmvc.service;

import usersmvc.model.user.AppMasterUserDetails;

import java.util.Optional;

public interface MasterUserService {

    Optional<AppMasterUserDetails> getCurrentMasterUser();

}
