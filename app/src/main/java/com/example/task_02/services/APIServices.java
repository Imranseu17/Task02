package com.example.task_02.services;

import com.example.task_02.Models.Data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface APIServices {

    @GET("http://dropbox.sandbox2000.com/intrvw/users.json")
    Call<Data> getUsers();

    @GET("https://randomuser.me/api/portraits/women/{PHOTO_ID}")
    Call<Data> getWomenImage(@Path("PHOTO_ID") String PHOTO_ID);

    @GET("https://randomuser.me/api/portraits/men/{PHOTO_ID}")
    Call<Data> getmenImage(@Path("PHOTO_ID") String PHOTO_ID);




}
