package sns.spring.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import sns.spring.dto.BoardDTO;
import sns.spring.dto.UserDTO;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name="posttbl")
@Data
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;
    @Column(length = 10, nullable = false)
    private String user_id;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String post_text;
    @CreatedDate
    private LocalDateTime post_date=LocalDateTime.now();
    private String filename;//파일이름
    private String filepath;//파일경로

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<LikeEntity> postLikes;
    @Builder
    public BoardEntity(Long id, String author, String content,LocalDateTime createdDate,String f_name,String f_path) {
        this.post_id = id;
        this.user_id = author;
        this.post_text = content;
        this.post_date=createdDate;
        this.filename=f_name;
        this.filepath=f_path;
    }
}
