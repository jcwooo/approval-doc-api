package com.style.approval.advice;

import com.style.approval.advice.customException.*;
import com.style.approval.web.model.common.response.CommonResult;
import com.style.approval.web.service.response.impl.ResponseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {
    private final ResponseServiceImpl responseService;
    private final MessageSource messageSource;


    //유저 로그인  실패
    @ExceptionHandler(CUserLoginFailureException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult userLoginFailureException() {
        return responseService.getFailResult(Integer.valueOf(getMessage("userLoginFailure.code")), getMessage("userLoginFailure.msg"));
    }

    //유저 ID 중복 에러
    @ExceptionHandler(CUserExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult userIdExistException() {
        return responseService.getFailResult(Integer.valueOf(getMessage("userIdExist.code")), getMessage("userIdExist.msg"));
    }

    //유저 ID 없음 에러
    @ExceptionHandler(CUserIdIsNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonResult userIdIsNotExistException() {
        return responseService.getFailResult(Integer.valueOf(getMessage("userIdIsNotExist.code")), getMessage("userIdIsNotExist.msg"));
    }

    //결재 문서 ID 없음 에러
    @ExceptionHandler(CDocIdIsNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonResult docIdIsNotExistException() {
        return responseService.getFailResult(Integer.valueOf(getMessage("documentIdIsNotExist.code")), getMessage("documentIdIsNotExist.msg"));
    }

    //결재 데이터 중복 에러
    @ExceptionHandler(CSignDataIsExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult signDataIsExistException() {
        return responseService.getFailResult(Integer.valueOf(getMessage("signDataIsExist.code")), getMessage("signDataIsExist.msg"));
    }

    // 결재자 중복 에러
    @ExceptionHandler(CSignUserExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult signUserExistException() {
        return responseService.getFailResult(Integer.valueOf(getMessage("signUserExist.code")), getMessage("signUserExist.msg"));
    }

    // 결재번호 중복 에러
    @ExceptionHandler(CSignNoExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult signNoExistException() {
        return responseService.getFailResult(Integer.valueOf(getMessage("signNoExist.code")), getMessage("signNoExist.msg"));
    }

    // 만료 된 결재문서 에러
    @ExceptionHandler(CDocumentExpiredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult documentExpiredException() {
        return responseService.getFailResult(Integer.valueOf(getMessage("documentExpired.code")), getMessage("documentExpired.msg"));
    }

    // 결재 차례 에러
    @ExceptionHandler(CSignNoIsNotUserTurnException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult signNoIsNotUserTurnException() {
        return responseService.getFailResult(Integer.valueOf(getMessage("signNoIsNotUserTurn.code")), getMessage("signNoIsNotUserTurn.msg"));
    }

    // 결재 대기문서 에러
    @ExceptionHandler(CSignUserNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult signUserNotExistException() {
        return responseService.getFailResult(Integer.valueOf(getMessage("signNoIsNotUserTurn.code")), getMessage("signNoIsNotUserTurn.msg"));
    }

    // 결재 대기문서 에러
    @ExceptionHandler(CNotSignUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult NotSignUserException() {
        return responseService.getFailResult(Integer.valueOf(getMessage("notSignUser.code")), getMessage("notSignUser.msg"));
    }

    // 결재 정보 없음 에러
    @ExceptionHandler(CSignDataIsNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult signDataIsNotExistException() {
        return responseService.getFailResult(Integer.valueOf(getMessage("signDataIsNotExist.code")), getMessage("signDataIsNotExist.msg"));
    }


    // code정보에 해당하는 메시지를 조회합니다.
    private String getMessage(String code) {
        return getMessage(code, null);
    }

    // code정보, 추가 argument로 현재 locale에 맞는 메시지를 조회
    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }


}
