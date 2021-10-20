package com.style.approval.web.model.common.response.enumResponse;

import java.util.List;

public enum CommonResponse {
    SUCCESS(200, "성공하였습니다.");

    int code;
    String msg;
    List response;

    CommonResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List getList() {
        return response;
    }
}
