package com.style.approval.advice.customException;

public class CNotSignUserException extends RuntimeException {
    private String code;

    public CNotSignUserException(String msg, Throwable t) {
        super(msg, t);
    }

    public CNotSignUserException(ExceptionType code) {
        super(code.name());
        this.code = code.getCode();
    }

    public CNotSignUserException() {
        super();
    }
}