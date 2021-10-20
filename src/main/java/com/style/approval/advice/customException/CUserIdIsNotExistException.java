package com.style.approval.advice.customException;

public class CUserIdIsNotExistException extends RuntimeException {
    private String code;

    public CUserIdIsNotExistException(String msg, Throwable t) {
        super(msg, t);
    }

    public CUserIdIsNotExistException(ExceptionType code) {
        super(code.name());
        this.code = code.getCode();
    }

    public CUserIdIsNotExistException() {
        super();
    }
}