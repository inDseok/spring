package sns.spring.service;

import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import sns.spring.dto.UserDTO;
import sns.spring.entity.UserEntity;
import sns.spring.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void save(UserDTO userDTO) {
        //1.dto -> entity 변환
        //repository의 save메서드 호출(조건, entity 객체를 넘겨야함)
        UserEntity userEntity = UserEntity.toUserEntity(userDTO);
        userRepository.save(userEntity);
    }
    public UserDTO login(UserDTO userDTO) {
        Optional<UserEntity> byUserId = userRepository.findById(userDTO.getId());
        if(byUserId.isPresent()){
            UserEntity userEntity = byUserId.get();
            // BCrypt를 사용하여 입력된 비밀번호와 해시된 비밀번호를 비교
            if(BCrypt.checkpw(userDTO.getPw(), userEntity.getPw())){
                // 비밀번호 일치
                // entity를 dto로 변환
                UserDTO dto= UserDTO.toUserDTO(userEntity);
                return dto;
            }
            else{
                // 비밀번호 불일치
                return null;
            }
        }
        else{
            // 아이디 없음
            return null;
        }
    }
}
