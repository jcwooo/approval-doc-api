package com.style.approval.web.service.response;

import com.style.approval.web.model.common.response.CommonResult;
import com.style.approval.web.model.common.response.ListResult;
import com.style.approval.web.model.common.response.SingleResult;
import java.util.List;

/**
 * ResponseService
 */

public interface ResponseService {
    <T> SingleResult getSingleResult(T data);

    <T> ListResult<T> getListResult(List<T> list);

    CommonResult getSuccessResult();

    <T> CommonResult<T> getFailResult(T code, T msg);

    void setSuccessResultList(CommonResult result);

    void setSuccessResultData(CommonResult result);
}
