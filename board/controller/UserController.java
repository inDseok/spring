package sns.spring.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sns.spring.Application;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sns.spring.dto.UserDTO;
import sns.spring.service.UserService;


@Controller

public class UserController {
    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user/save")
    public String saveForm(){
        return "save";
    }


    @PostMapping("/user/save")
    public String save(@ModelAttribute UserDTO userDTO){
        logger.info("UserController.save");
        logger.info("userDTO="+userDTO);
        String hashedPassword = BCrypt.hashpw(userDTO.getPw(), BCrypt.gensalt());
        userDTO.setPw(hashedPassword);
        userService.save(userDTO);
        return "index";
    }

    @GetMapping("/user/login")
    public  String loginForm(){
        return "login";
    }
    @PostMapping("/user/login")
    public String login(@ModelAttribute UserDTO userDTO, HttpSession session){
        UserDTO loginResult=userService.login(userDTO);
        if(loginResult!=null){
            session.setAttribute("loginid",loginResult.getId());
            session.setAttribute("loginname",loginResult.getName());
            return "redirect:../user/list";
        }
        else{
            return "login";
        }
    }
}
