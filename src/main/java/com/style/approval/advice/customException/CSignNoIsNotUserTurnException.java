package com.style.approval.advice.customException;

public class CSignNoIsNotUserTurnException extends RuntimeException {
    private String code;

    public CSignNoIsNotUserTurnException(String msg, Throwable t) {
        super(msg, t);
    }

    public CSignNoIsNotUserTurnException(ExceptionType code) {
        super(code.name());
        this.code = code.getCode();
    }

    public CSignNoIsNotUserTurnException() {
        super();
    }
}