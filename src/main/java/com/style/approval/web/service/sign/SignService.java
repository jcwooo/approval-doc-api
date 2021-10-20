package com.style.approval.web.service.sign;

import com.style.approval.web.model.SignModel;
import com.style.approval.web.model.UserModel;

import java.util.List;

public interface SignService {

    /**
     * 결재 유저 생성
     * @param signModel
     * @return
     */
    SignModel createSignUser(SignModel signModel);

    /**
     * 결재 문서 처리
     * @param signModel
     */
    void updateSign(SignModel signModel);
}
