package com.style.approval.advice.customException;

public class CSignUserNotExistException extends RuntimeException {
    private String code;

    public CSignUserNotExistException(String msg, Throwable t) {
        super(msg, t);
    }

    public CSignUserNotExistException(ExceptionType code) {
        super(code.name());
        this.code = code.getCode();
    }

    public CSignUserNotExistException() {
        super();
    }
}