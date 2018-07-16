package com.example.amarkosich.oupaasistente.networking;

import com.example.amarkosich.oupaasistente.model.UserLogged;
import com.example.amarkosich.oupaasistente.model.UserSession;
import com.example.amarkosich.oupaasistente.model.request.UpdateDeviceTokenRequest;
import com.example.amarkosich.oupaasistente.model.request.UserSessionRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface OupaApi {

    @PATCH("/user")
    Call<Void> updateDeviceToken(@Header("Authorization") String accessToken, @Body UpdateDeviceTokenRequest updateDeviceTokenRequest);

    @POST("/users/sessions")
    Call<UserSession> createUserSession(@Body UserSessionRequest sessionRequest);

    @GET("/users/logged_in")
    Call<UserLogged> getUserLogged(@Header("Authorization") String accessToken);

    @GET("/users/associated_oupas")
    Call<List<UserLogged>> getOupas(@Header("Authorization") String accessToken);

}