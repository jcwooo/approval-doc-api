package com.style.approval.advice.customException;

public class CSignNoExistException extends RuntimeException {
    private String code;

    public CSignNoExistException(String msg, Throwable t) {
        super(msg, t);
    }

    public CSignNoExistException(ExceptionType code) {
        super(code.name());
        this.code = code.getCode();
    }

    public CSignNoExistException() {
        super();
    }
}