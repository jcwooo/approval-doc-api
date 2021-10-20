package com.style.approval.advice.customException;

public class CUserLoginFailureException extends RuntimeException {
    private String code;

    public CUserLoginFailureException(String msg, Throwable t) {
        super(msg, t);
    }

    public CUserLoginFailureException(ExceptionType code) {
        super(code.name());
        this.code = code.getCode();
    }

    public CUserLoginFailureException() {
        super();
    }
}