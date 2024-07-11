package sns.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sns.spring.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findById(String id);
}
