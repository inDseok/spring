package sns.spring.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import sns.spring.entity.LikeEntity;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LikeDTO {
    private Long id;
    private Long postId;
    private String userId;

    public LikeDTO(Long id, Long postId, String userId) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
    }

    // LikeEntity를 LikeDTO로 변환하는 메서드
    public static LikeDTO toLikeDTO(LikeEntity likeEntity, Long likeCount) {
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setId(null);
        likeDTO.setPostId(likeEntity.getPostId());
        likeDTO.setUserId(likeEntity.getUserId());
        return likeDTO;
    }
}
