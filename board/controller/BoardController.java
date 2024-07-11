package sns.spring.controller;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sns.spring.Application;
import sns.spring.dto.BoardDTO;
import sns.spring.entity.BoardEntity;
import sns.spring.service.BoardService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller

public class BoardController {
    private BoardService boardService;
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    @GetMapping("/user/list")
    public String list(Model model) {
        List<BoardDTO> boardDTOList = boardService.getBoardList();
        model.addAttribute("postList", boardDTOList);
        return "list";
    }

    @GetMapping("/user/mypost")
    public String mypost(Model model, HttpSession session) {
        String loginId = (String) session.getAttribute("loginid");

        List<BoardDTO> boardDTOList = boardService.getBoardList().stream()
                .filter(boardDTO -> loginId.equals(boardDTO.getUser_id()))
                .collect(Collectors.toList());

        model.addAttribute("postList", boardDTOList);
        return "mypost";
    }

    @GetMapping("/user/search")
    public String search(@RequestParam(name = "search") String searchId, Model model) {
        List<BoardDTO> boardDTOList = boardService.getBoardList().stream()
                .filter(boardDTO -> searchId.equals(boardDTO.getUser_id()))
                .collect(Collectors.toList());

        model.addAttribute("postList", boardDTOList);
        return "list";
    }


    @GetMapping("/user/post")
    public String post() {
        return "post";
    }
    @PostMapping("/user/post")
    public String write(BoardDTO boardDTO, @RequestParam("file") MultipartFile file) throws Exception {
        BoardEntity board = boardDTO.toEntity();
        boardService.write(board, file);
        logger.info("BoardDTO=" + boardDTO);
        return "redirect:../user/list";
    }
    @GetMapping("/user/correction")
    public String correct(@RequestParam("post_id") Long id, Model model) {
        BoardDTO boardDTOList=boardService.getMyBoard(id);
        String my_id=id.toString();
        logger.info(my_id);
        model.addAttribute("postList",boardDTOList);
        return "correction";
    }

    @PostMapping("/user/correction")
    @Transactional
    public String update(@RequestParam("post_id") Long id, @RequestParam("file") MultipartFile file, BoardDTO boardDTO) {
        Long my_id = boardDTO.getPost_id();
        BoardDTO myBoard = boardService.getMyBoard(my_id);

        if (myBoard != null) {
            myBoard.setUser_id(boardDTO.getUser_id());
            myBoard.setPost_text(boardDTO.getPost_text());

            // 파일이 업로드된 경우에만 파일 이름 저장
            if (!file.isEmpty()) {
                String originalFilename = file.getOriginalFilename();
                myBoard.setFilename(originalFilename);
            }

            myBoard.setPost_date(LocalDateTime.now());
            boardService.savePost(myBoard);
        }

        return "redirect:/user/mypost";
    }

    @GetMapping("/user/delete")
    public String delete(@RequestParam("post_id") Long id) {
        boardService.deleteById(id);
        return "redirect:/user/mypost";
    }
    @GetMapping("/user/comment")
    public String comment() {
        return "comment";
    }
}
