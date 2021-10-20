package com.style.approval.advice.customException;

public class CSignDataIsExistException extends RuntimeException {
    private String code;

    public CSignDataIsExistException(String msg, Throwable t) {
        super(msg, t);
    }

    public CSignDataIsExistException(ExceptionType code) {
        super(code.name());
        this.code = code.getCode();
    }

    public CSignDataIsExistException() {
        super();
    }
}