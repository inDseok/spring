package sns.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sns.spring.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
}
