package sns.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sns.spring.dto.LikeDTO;
import sns.spring.entity.LikeEntity;
import sns.spring.repository.LikeRepository;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    @Autowired
    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    // '좋아요' 추가
    public void addLike(LikeDTO likeDTO) {
        LikeEntity likeEntity = LikeEntity.toLikeEntity(likeDTO);
        likeRepository.save(likeEntity);
    }


    // 특정 게시물의 '좋아요' 개수 조회
    public Long getLikesCount(Long postId) {
        return likeRepository.countByPostId(postId);
    }
}
