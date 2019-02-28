package com.orcl.model;

public enum ErrorCode {

    ERROR_CODE_0001("ERROR_CODE_0001","At least one coin details should be present"),
    ERROR_CODE_0002("ERROR_CODE_0002","Coin name must be provided."),
    ERROR_CODE_0003("ERROR_CODE_0003","Sum value must be provided."),
    ERROR_CODE_0004("ERROR_CODE_0004","Change not available."),

    ;

    public String code;
    public String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
