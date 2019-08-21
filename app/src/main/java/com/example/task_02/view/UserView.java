package com.example.task_02.view;

import com.example.task_02.Models.Data;

public interface UserView {

    public void onSuccess(Data data);
    public void onError(String error, int code);

}
