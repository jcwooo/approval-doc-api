package com.style.approval.advice.customException;

public class CSignUserExistException extends RuntimeException {
    private String code;

    public CSignUserExistException(String msg, Throwable t) {
        super(msg, t);
    }

    public CSignUserExistException(ExceptionType code) {
        super(code.name());
        this.code = code.getCode();
    }

    public CSignUserExistException() {
        super();
    }
}