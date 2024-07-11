package sns.spring.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sns.spring.dto.UserDTO;

@Entity
@Getter
@Setter
@Table(name="usertbl")
public class UserEntity {
        @Id
        @Column
        private String id;
        @Column
        private String pw;
        @Column
        private String name;
        @Column
        private  Integer age;
        @Column
        private String gender;

        public static UserEntity toUserEntity(UserDTO userDTO){
                UserEntity userEntity=new UserEntity();
                userEntity.setId(userDTO.getId());
                userEntity.setPw(userDTO.getPw());
                userEntity.setName(userDTO.getName());
                userEntity.setAge(userDTO.getAge());
                userEntity.setGender(userDTO.getGender());
                return userEntity;
        }
}
