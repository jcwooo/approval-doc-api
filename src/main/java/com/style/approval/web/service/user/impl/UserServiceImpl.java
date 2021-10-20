package com.style.approval.web.service.user.impl;

import com.style.approval.advice.customException.CUserExistException;
import com.style.approval.advice.customException.CUserIdIsNotExistException;
import com.style.approval.advice.customException.CUserLoginFailureException;
import com.style.approval.advice.customException.ExceptionType;
import com.style.approval.web.entity.DocumentEntity;
import com.style.approval.web.entity.SignEntity;
import com.style.approval.web.entity.UserEntity;
import com.style.approval.web.model.DocumentModel;
import com.style.approval.web.model.UserModel;
import com.style.approval.web.repository.UserRepo;
import com.style.approval.web.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;


    @Override
    public UserModel getUser(UserModel userModel) {
        UserModel resultModel;
        Optional<UserEntity> isUserExist = userRepo.findByIdAndPassword(userModel.getUserId(), userModel.getPassword());

        if (isUserExist.isPresent()) {
            resultModel = UserModel.of(isUserExist.get());
        } else {
            throw new CUserLoginFailureException(ExceptionType.USER_LOGIN_FAILURE);
        }
        return resultModel;
    }

    @Override
    public UserModel saveUser(UserModel userModel) {
        boolean hasUser = userRepo.existsById(userModel.getUserId());

        if(hasUser) {
            throw new CUserExistException(ExceptionType.USER_ID_EXIST);
        }

        return UserModel.of(userRepo.save(UserEntity.create(userModel)));

    }
}
