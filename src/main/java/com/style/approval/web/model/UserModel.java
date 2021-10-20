package com.style.approval.web.model;

import com.style.approval.web.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

/**
 * UserModel
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private String userId; //유저 ID
    private String password; //패스워드


    //유저객체생성
    public static UserModel of(UserEntity user) {
        return new UserModel(user.getId(), user.getPassword());
    }
}
