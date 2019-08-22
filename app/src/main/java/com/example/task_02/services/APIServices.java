package com.example.task_02.services;

import com.example.task_02.Models.Data;
import com.example.task_02.Models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface APIServices {

    @GET("/intrvw/users.json")
    Call<Data> getUsers();

}
