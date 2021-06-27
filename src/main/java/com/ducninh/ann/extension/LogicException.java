package com.ducninh.ann.extension;

public class LogicException extends Exception {
    private ErrorCode errorCode;

    public LogicException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public LogicException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public LogicException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
