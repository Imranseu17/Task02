package com.example.task_02.utils;

public enum UserStatus {

    USERS_SUCCESS(1,701),
    IMAGE_SUCCESS(2,702),
    IMAGE_ERROR(3,805),
    USERS_ERROR(4,806);

    private int key;
    private int code;

    UserStatus(int key, int code) {
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

    public static UserStatus getByCode(int code){
        for(UserStatus rs :UserStatus.values()){
            if(rs.code==code)return rs;
        }

        return null;


    }
}

