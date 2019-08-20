package com.example.task_02.utils;

public enum ErrorCode {

    ERRORCODE500(1, 500),
    ERRORCODE400(2, 400);



    private int key;
    private int code;

    ErrorCode(int key, int code) {
        this.key = key;
        this.code = code;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static ErrorCode getByCode(int code) {
        for (ErrorCode rs : ErrorCode.values()) {
            if (rs.getCode()== code) return rs;
        }

        return null;
    }
}
