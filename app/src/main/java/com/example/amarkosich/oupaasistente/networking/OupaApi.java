package com.example.amarkosich.oupaasistente.networking;

import com.example.amarkosich.oupaasistente.contacts.services.ContactResponse;
import com.example.amarkosich.oupaasistente.contacts.services.ContactSerialized;
import com.example.amarkosich.oupaasistente.model.UserLogged;
import com.example.amarkosich.oupaasistente.model.UserSession;
import com.example.amarkosich.oupaasistente.model.request.UpdateDeviceTokenRequest;
import com.example.amarkosich.oupaasistente.model.request.UserSessionRequest;
import com.example.amarkosich.oupaasistente.pillbox.services.PillResponse;
import com.example.amarkosich.oupaasistente.pillbox.services.PillSerialized;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OupaApi {

    @PATCH("/user")
    Call<Void> updateDeviceToken(@Header("Authorization") String accessToken, @Body UpdateDeviceTokenRequest updateDeviceTokenRequest);

    @POST("/users/sessions")
    Call<UserSession> createUserSession(@Body UserSessionRequest sessionRequest);

    @GET("/users/logged_in")
    Call<UserLogged> getUserLogged(@Header("Authorization") String accessToken);

    @POST("/contacts")
    Call<ContactResponse> createContact(@Header("Authorization") String accessToken, @Header("Content-Type") String s, @Body ContactSerialized contactSerialized);

    @GET("/contacts")
    Call<ArrayList<ContactResponse>> getContacts(@Header("Authorization") String accessToken);

    @GET("/users/associated_oupas")
    Call<List<UserLogged>> getOupas(@Header("Authorization") String accessToken);

    @GET("/users/{user_id}/personal_medicine_reminder")
    Call<ArrayList<PillResponse>> getPillsForToday(@Header("Authorization") String accessToken, @Path("user_id") String oupaUserId);

    @POST("/personal_medicine_reminder")
    Call<PillResponse> createPill(@Header("Authorization") String accessToken, @Header("Content-Type") String content_type, @Body PillSerialized pillSerialized);
}