package com.style.approval.web.controller;

import com.style.approval.ApprovalApplication;
import com.style.approval.web.model.UserModel;
import com.style.approval.web.model.common.response.SingleResult;
import com.style.approval.web.service.response.ResponseService;
import com.style.approval.web.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api", produces = "application/json; charset=utf8")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApprovalController {
    private final ResponseService responseService;
    private final UserService userService;

    public static void main(String[] args) {
        String str1 = "abcdefg";
        String str2 = "gcb";
        String targetStr = str1 + str1;
        if(targetStr.contains(str2)){
            System.out.println("true");
        }else{
            System.out.println("false");
        }

    }
}
