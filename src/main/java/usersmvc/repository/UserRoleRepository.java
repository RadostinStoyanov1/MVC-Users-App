package usersmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usersmvc.model.entity.UserRoleEntity;
import usersmvc.model.entity.UserRoleEnum;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    public UserRoleEntity getUserRoleEntityByRole(UserRoleEnum roleEnum);
}
