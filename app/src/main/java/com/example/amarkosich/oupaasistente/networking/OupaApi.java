package com.example.amarkosich.oupaasistente.networking;

import com.example.amarkosich.oupaasistente.model.UserSession;
import com.example.amarkosich.oupaasistente.model.request.UpdateDeviceTokenRequest;
import com.example.amarkosich.oupaasistente.model.request.UserSessionRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface OupaApi {

    @PATCH("/user")
    Call<Void> updateDeviceToken(@Header("Authorization") String accessToken, @Body UpdateDeviceTokenRequest updateDeviceTokenRequest);

    @POST("/users/sessions")
    Call<UserSession> createUserSession(@Body UserSessionRequest sessionRequest);
}