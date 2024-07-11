package sns.spring.dto;
import lombok.*;
import sns.spring.entity.CommentEntity;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommentDTO {
    private Long comment_id;
    private Long post_id;
    private String user_id;
    private String comment_text;

    public static CommentDTO toCommentDTO(CommentEntity commentEntity, Long post_id){
        CommentDTO commentDTO=new CommentDTO();
        commentDTO.setComment_id(commentEntity.getComment_id());
        commentDTO.setPost_id(post_id);
        commentDTO.setUser_id(commentEntity.getUser_id());
        commentDTO.setComment_text(commentEntity.getComment_text());
        return commentDTO;
    }
}
