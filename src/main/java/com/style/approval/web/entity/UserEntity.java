package com.style.approval.web.entity;

import com.style.approval.web.entity.common.CommonEntity;
import com.style.approval.web.model.UserModel;
import lombok.*;
import javax.persistence.*;
import java.util.List;

/**
 * user(사용자) entity
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity extends CommonEntity {
    @Id
    @Column(name="USER_ID")
    private String id;
    private String password;

    @OneToMany(mappedBy = "user")
    List<DocumentEntity> documentEntityList;

    @OneToMany(mappedBy = "user")
    List<SignEntity> userSignEntityList;

    public UserEntity(String userId, String password) {
        this.id = userId;
        this.password = password;
    }

    public static UserEntity create(UserModel userModel) {
        return new UserEntity(userModel.getUserId(), userModel.getPassword());
    }

}
