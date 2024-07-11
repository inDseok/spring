package sns.spring.controller;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sns.spring.Application;
import sns.spring.dto.LikeDTO;
import sns.spring.service.LikeService;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    // 특정 게시물에 대한 '좋아요' 추가
    @PostMapping
    public ResponseEntity<String> addLike(@RequestBody LikeDTO likeDTO, HttpSession session) {
        // 클라이언트에서 userId를 전달하지 않은 경우 세션에서 userId를 가져와 설정
        if (likeDTO.getUserId() == null) {
            String loggedInUserId = (String) session.getAttribute("loginid");
            likeDTO.setUserId(loggedInUserId);
        }

        likeService.addLike(likeDTO);
        logger.info("1111111" + likeDTO.getUserId());
        return ResponseEntity.ok("Like added successfully");
    }

    // 특정 게시물의 '좋아요' 개수 조회
    @GetMapping("/count/{postId}")
    public ResponseEntity<Long> getLikesCount(@PathVariable(name="postId") Long postId) {
        Long count = likeService.getLikesCount(postId);
        logger.info("333333333" +count);
        return ResponseEntity.ok(count);
    }
}
