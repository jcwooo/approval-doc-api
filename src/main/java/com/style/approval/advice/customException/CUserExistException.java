package com.style.approval.advice.customException;

public class CUserExistException extends RuntimeException {
    private String code;

    public CUserExistException(String msg, Throwable t) {
        super(msg, t);
    }

    public CUserExistException(ExceptionType code) {
        super(code.name());
        this.code = code.getCode();
    }

    public CUserExistException() {
        super();
    }
}