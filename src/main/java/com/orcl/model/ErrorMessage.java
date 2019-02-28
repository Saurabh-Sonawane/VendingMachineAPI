package com.orcl.model;



public class ErrorMessage {

    private String code;
    private String message;

    public ErrorMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorMessage(ErrorCode errorCode) {
        this.code = errorCode.code;
        this.message = errorCode.message;
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

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
