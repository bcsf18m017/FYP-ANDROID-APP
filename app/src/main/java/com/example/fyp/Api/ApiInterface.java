package com.example.fyp.Api;

import com.example.fyp.Entities.Login;
import com.example.fyp.Entities.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("/api/authentication/login")
    Call<User> postLogin(@Body Login login);
}
