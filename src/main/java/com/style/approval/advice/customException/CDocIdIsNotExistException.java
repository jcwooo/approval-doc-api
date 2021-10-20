package com.style.approval.advice.customException;

public class CDocIdIsNotExistException extends RuntimeException {
    private String code;

    public CDocIdIsNotExistException(String msg, Throwable t) {
        super(msg, t);
    }

    public CDocIdIsNotExistException(ExceptionType code) {
        super(code.name());
        this.code = code.getCode();
    }

    public CDocIdIsNotExistException() {
        super();
    }
}