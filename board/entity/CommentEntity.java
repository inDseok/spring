package sns.spring.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import sns.spring.dto.CommentDTO;
import jakarta.persistence.*;
@Entity
@Getter
@Setter
@Table(name="commenttbl")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    private Long post_id;

    @Column
    private String user_id;

    private String comment_text;
}
