package com.example.amarkosich.oupaasistente.networking;

import com.example.amarkosich.oupaasistente.model.UserSession;
import com.example.amarkosich.oupaasistente.model.request.UpdateDeviceTokenRequest;
import com.example.amarkosich.oupaasistente.model.request.UserSessionRequest;
import com.example.amarkosich.oupaasistente.pillbox.services.PillResponse;
import com.example.amarkosich.oupaasistente.pillbox.services.PillSerialized;
import com.example.amarkosich.oupaasistente.pillbox.services.PillTakenSerialized;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OupaApi {

    @PATCH("/user")
    Call<Void> updateDeviceToken(@Header("Authorization") String accessToken, @Body UpdateDeviceTokenRequest updateDeviceTokenRequest);

    @POST("/users/sessions")
    Call<UserSession> createUserSession(@Body UserSessionRequest sessionRequest);

    @POST("/emergency_alarm")
    Call<Void> createSOSAlert(@Header("Authorization") String accessToken);

    @POST("/personal_medicine_reminder")
    Call<PillResponse> createPill(@Header("Authorization") String accessToken, @Header("Content-Type") String content_type, @Body PillSerialized pillSerialized);

    @GET("/personal_medicine_reminder")
    Call<ArrayList<PillResponse>> getPillsForToday(@Header("Authorization") String accessToken);

    @PUT("/personal_medicine_reminder/{id}")
    Call<PillResponse> drinkedPill(@Header("Authorization") String accessToken, @Path("id") String id, @Body PillTakenSerialized pillSerialized);

}