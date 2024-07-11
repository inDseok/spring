package sns.spring.service;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sns.spring.Application;
import sns.spring.dto.BoardDTO;
import sns.spring.entity.BoardEntity;
import sns.spring.repository.BoardRepository;
import sns.spring.repository.LikeRepository;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {
    private BoardRepository boardRepository;
    private LikeRepository  likeRepository;
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    public BoardService(BoardRepository boardRepository) {

        this.boardRepository = boardRepository;
    }
    //@Transactional
    public Long savePost(BoardDTO boardDTO) {
        if (boardDTO.getPost_text() == null) {
            boardDTO.setPost_text("");
        }
        return boardRepository.save(boardDTO.toEntity()).getPost_id();
    }
    public List<BoardDTO> getBoardList() {
        List<BoardEntity> boardList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for(BoardEntity board : boardList) {
            BoardDTO boardDTO = BoardDTO.builder()
                    .id(board.getPost_id())
                    .author(board.getUser_id())
                    .content(board.getPost_text())
                    .createdDate(board.getPost_date())
                    .f_name(board.getFilename())
                    .f_path(board.getFilepath())
                    .build();
            boardDTOList.add(boardDTO);
        }
        return boardDTOList;
    }
    public BoardDTO getMyBoard(long id) //find board info use board id
    {
        BoardEntity board = boardRepository.findById(id).orElse(null);
        return BoardDTO.builder().id(board.getPost_id())
                .id(board.getPost_id())
                .author(board.getUser_id())
                .content(board.getPost_text())
                .createdDate(board.getPost_date())
                .f_name(board.getFilename())
                .f_path(board.getFilepath())
                .build();
    }

    public void write(BoardEntity board, MultipartFile file) throws Exception {
        String projectPath = "C:\\Users\\inseo\\IdeaProjects\\spring\\spring\\src\\main\\resources\\static\\";

        if (!file.isEmpty()) {
            // 파일이 업로드된 경우에만 처리
            String filename = file.getOriginalFilename(); // 파일의 원래 이름 가져오기
            String filepath = projectPath + filename; // 저장할 경로 설정

            // 파일을 저장하고 파일 정보를 엔티티에 설정
            byte[] bytes = file.getBytes();
            Path path = Paths.get(filepath);
            Files.write(path, bytes);

            board.setFilename(filename);
            board.setFilepath(filepath);
        }

        logger.info("filename=" + board.getFilename());
        boardRepository.save(board);
    }

    //게시글리스트처리
    public List<BoardEntity> boardList(){
        //findAll : 테스트보드라는 클래스가 담긴 List를 반환하는것을 확인할수있다
        return boardRepository.findAll();
    }
    @Transactional
    public void deleteById(Long boardId) {
        // 게시글 좋아요 데이터 삭제
        LikeRepository.deleteByPostId(boardId);

        // 게시글 엔티티 삭제
        boardRepository.deleteById(boardId);
    }
}
