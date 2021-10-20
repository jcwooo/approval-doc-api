package com.style.approval.web.service.user;

import com.style.approval.web.model.DocumentModel;
import com.style.approval.web.model.UserModel;
import com.style.approval.web.model.common.response.ListResult;

import java.util.List;

public interface UserService {
    //유저 로그인
    UserModel getUser(UserModel userModel);

    //유저 생성(테스트에 필요)
    UserModel saveUser(UserModel userModel);
}
