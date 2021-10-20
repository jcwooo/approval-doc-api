package com.style.approval.advice.customException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {
    SERVER_ERROR("-9999", "serverError.msg"),
    USER_LOGIN_FAILURE("-1001", "userLoginFailure.msg"),
    USER_ID_EXIST("-1002", "userIdExist.msg"),
    USER_ID_IS_NOT_EXIST("-1003", "userIdIsNotExist.msg"),
    DOCUMENT_ID_IS_NOT_EXIST("-1004", "documentIdIsNotExist.msg"),
    SIGN_DATA_IS_EXIST("-1005","signDataIsExist.msg"),
    SIGN_USER_EXIST("-1006","signUserExist.msg"),
    SIGN_NO_EXIST("-1007","signNoExist.msg"),
    DOCUMENT_EXPIRED("-1008","documentExpired.msg"),
    SIGN_NO_IS_NOT_USER_TURN("-1009","signNoIsNotUserTurn.msg"),
    SIGN_USER_NOT_EXIST("-1010","signUserNotExist.msg"),
    NOT_SIGN_USER("-1011","notSignUser.msg"),
    SIGN_DATA_IS_NOT_EXIST("-1012","signDataIsNotExist.msg");







    private static final Map<String, ExceptionType> enumMap = new HashMap<>(values().length);

    static {
        Stream.of(values()).forEach(v -> enumMap.put(v.getCode(), v));
    }

    private final String code;
    private final String message;

    public static ExceptionType codeOf(String code) {
        return Optional.ofNullable(enumMap.get(code)).orElseThrow(IllegalArgumentException::new);
    }
}
