package sns.spring.dto;
import lombok.*;
import sns.spring.entity.BoardEntity;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDTO {
    private Long post_id;
    private String user_id;
    private String post_text;
    private LocalDateTime post_date=LocalDateTime.now();
    private String filename;//파일이름
    private String filepath;//파일경로
    public BoardEntity toEntity() {
        BoardEntity build = BoardEntity.builder()
                .id(post_id)
                .author(user_id)
                .content(post_text)
                .createdDate(post_date)
                .f_name(filename)
                .f_path(filepath)
                .build();
        return build;
    }
    @Builder
    public BoardDTO(Long id, String author,  String content,LocalDateTime createdDate,String f_name,String f_path) {
        this.post_id = id;
        this.user_id = author;
        this.post_text = content;
        this.post_date = createdDate;
        this.filename=f_name;
        this.filepath=f_path;
    }
}
