package com.ducninh.ann.extension;

public enum ErrorCode {

    INTERNAL_SERVER_ERROR("500", "Internal server error"),
    FORBIDDEN("403", "Permission denied"),
    NAME_EXISTED("001", "Name existed"),
    RECORD_NOT_FOUND("002", "RECORD_NOT_FOUND"),
    RECORD_NOT_EXISTED("003", "RECORD_NOT_EXISTED"),
    CODE_EXISTED("004", "Code existed"),
    UPLOAD_FILE_FAILED("005", "Upload file failed"),
    RECORD_EXISTED("006","RECORD_EXISTED"),
    EMAIL_EXISTED("500","EMAIL_EXISTED!"),
    ROLE_INVALID("400","ROLE DOES NOT EXIST!"),
    RECORD_IS_USING("007", " CURRENT LOCATION IS USING"),
    ACCOUNT_EXISTED("500","ACCOUNT_EXISTED!"),




    ;


    private String code;
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
