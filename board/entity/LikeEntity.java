package sns.spring.entity;

import jakarta.persistence.*;
import lombok.*;
import sns.spring.dto.LikeDTO;
import sns.spring.dto.UserDTO;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@Table(name="post_like")
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long postId;

    @Column
    private String userId;

    public static LikeEntity toLikeEntity(LikeDTO likeDTO){
        LikeEntity likeEntity=new LikeEntity();
        likeEntity.setPostId(likeDTO.getPostId());
        likeEntity.setUserId(likeDTO.getUserId());
        return likeEntity;
    }
}
