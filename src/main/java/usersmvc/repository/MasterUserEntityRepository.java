package usersmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import usersmvc.model.entity.MasterUserEntity;

import java.util.Optional;

public interface MasterUserEntityRepository extends JpaRepository<MasterUserEntity, Long> {
    Optional<MasterUserEntity> findByEmail(String email);
}
