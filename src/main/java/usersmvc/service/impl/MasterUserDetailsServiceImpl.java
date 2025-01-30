package usersmvc.service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import usersmvc.model.entity.MasterUserEntity;
import usersmvc.model.entity.UserRoleEntity;
import usersmvc.model.entity.UserRoleEnum;
import usersmvc.model.user.AppMasterUserDetails;
import usersmvc.repository.MasterUserEntityRepository;

public class MasterUserDetailsServiceImpl implements UserDetailsService {

    private final MasterUserEntityRepository masterUserEntityRepository;

    public MasterUserDetailsServiceImpl(MasterUserEntityRepository masterUserEntityRepository) {
        this.masterUserEntityRepository = masterUserEntityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return masterUserEntityRepository
                .findByEmail(email)
                .map(MasterUserDetailsServiceImpl::map)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User with email " + email + " not found!"));

    }

    private static UserDetails map(MasterUserEntity masterUserEntity) {
        return new AppMasterUserDetails(
                masterUserEntity.getUuid(),
                masterUserEntity.getEmail(),
                masterUserEntity.getPassword(),
                masterUserEntity.getRoles().stream().map(UserRoleEntity::getRole).map(MasterUserDetailsServiceImpl::map).toList(),
                masterUserEntity.getFirstName(),
                masterUserEntity.getLastName());
    }


    private static GrantedAuthority map(UserRoleEnum role) {
        return new SimpleGrantedAuthority("ROLE_" + role);
    }
}
