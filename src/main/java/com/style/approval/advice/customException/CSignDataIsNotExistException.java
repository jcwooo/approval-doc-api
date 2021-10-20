package com.style.approval.advice.customException;

public class CSignDataIsNotExistException extends RuntimeException {
    private String code;

    public CSignDataIsNotExistException(String msg, Throwable t) {
        super(msg, t);
    }

    public CSignDataIsNotExistException(ExceptionType code) {
        super(code.name());
        this.code = code.getCode();
    }

    public CSignDataIsNotExistException() {
        super();
    }
}