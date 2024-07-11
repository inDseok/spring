package sns.spring.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import sns.spring.entity.UserEntity;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {
    private String id;

    private String pw;

    private String name;

    private Integer age;

    private String gender;

    public static UserDTO toUserDTO(UserEntity userEntity){
        UserDTO userDTO= new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setPw(userEntity.getPw());
        userDTO.setName(userEntity.getName());
        userDTO.setAge(userEntity.getAge());
        userDTO.setGender(userEntity.getGender());
        return userDTO;
    }
}
