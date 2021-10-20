package com.style.approval.advice.customException;

public class CDocumentExpiredException extends RuntimeException {
    private String code;

    public CDocumentExpiredException(String msg, Throwable t) {
        super(msg, t);
    }

    public CDocumentExpiredException(ExceptionType code) {
        super(code.name());
        this.code = code.getCode();
    }

    public CDocumentExpiredException() {
        super();
    }
}