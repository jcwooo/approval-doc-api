package com.style.approval.web.controller;

import com.style.approval.web.model.DocumentModel;
import com.style.approval.web.model.SignModel;
import com.style.approval.web.model.UserModel;
import com.style.approval.web.model.common.response.CommonResult;
import com.style.approval.web.model.common.response.ListResult;
import com.style.approval.web.model.common.response.SingleResult;
import com.style.approval.web.service.document.DocumentService;
import com.style.approval.web.service.response.ResponseService;
import com.style.approval.web.service.sign.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api", produces = "application/json; charset=utf8")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SignController {
    private final ResponseService responseService;
    private final SignService signService;

    /**
     * 결재자 등록
     * @param signModel
     * @return
     */
    @PostMapping("/createSignUser")
    public SingleResult createSignUser(@RequestBody SignModel signModel){
        SignModel resultModel = signService.createSignUser(signModel);
        return responseService.getSingleResult(resultModel);
    }

    //결재 문서 처리
    @PatchMapping("/processSign")
    public CommonResult processSign(@RequestBody SignModel signModel) {
        signService.updateSign(signModel);
        return responseService.getSuccessResult();
    }



}
