package com.style.approval.web.controller;

import com.style.approval.web.model.DocumentModel;
import com.style.approval.web.model.SignModel;
import com.style.approval.web.model.UserModel;
import com.style.approval.web.model.common.response.ListResult;
import com.style.approval.web.model.common.response.SingleResult;
import com.style.approval.web.service.response.ResponseService;
import com.style.approval.web.service.sign.SignService;
import com.style.approval.web.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api", produces = "application/json; charset=utf8")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    private final ResponseService responseService;
    private final UserService userService;

    /**
     * 유저 로그인
     * @param userModel
     * @return
     */
    @PostMapping("/userLogin")
    public SingleResult loginUser(@RequestBody UserModel userModel){
        UserModel resultModel = userService.getUser(userModel);
        return responseService.getSingleResult(resultModel);
    }
}
