package sns.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sns.spring.entity.LikeEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    static void deleteByPostId(Long boardId) {
    }

    // 특정 게시물과 사용자에 대한 좋아요를 찾습니다.
    Optional<LikeEntity> findByPostIdAndUserId(Long postId, String userId);

    // 특정 게시물의 좋아요 수를 계산합니다.
    long countByPostId(Long postId);

}
