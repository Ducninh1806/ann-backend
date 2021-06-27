package com.ducninh.ann.extension;

import java.util.Date;

public class ErrorDetails {

    private Date timestamp;

    private String errorCode;

    private String message;

    public ErrorDetails() {
    }

    public ErrorDetails(Date timestamp, String errorCode, String message) {
        this.timestamp = timestamp;
        this.errorCode = errorCode;
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorDetails{" +
                "timestamp=" + timestamp +
                ", errorCode='" + errorCode + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
