package com.example.amarkosich.oupaasistente.services;

import android.os.UserManager;
import android.support.annotation.NonNull;

import com.example.amarkosich.oupaasistente.App;
import com.example.amarkosich.oupaasistente.UserSessionManager;
import com.example.amarkosich.oupaasistente.model.UserLogged;
import com.example.amarkosich.oupaasistente.model.UserSession;
import com.example.amarkosich.oupaasistente.model.request.UpdateDeviceTokenRequest;
import com.example.amarkosich.oupaasistente.model.request.UserSessionRequest;
import com.example.amarkosich.oupaasistente.networking.ApiClient;
import com.example.amarkosich.oupaasistente.networking.OupaApi;
import com.google.firebase.iid.FirebaseInstanceId;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserService {


    private OupaApi oupaApi;

    public UserService() {
        oupaApi = ApiClient.getInstance().getOupaClient();
    }

    public Call<UserSession> createUserSession(String email, String password) {

        String deviceToken = FirebaseInstanceId.getInstance().getToken();

        UserSessionRequest userSessionRequest = getUserSessionRequest(email, password, deviceToken);

        return oupaApi.createUserSession(userSessionRequest);
    }

    public Call<UserLogged> getUserLogged(String accessToken) {
        return oupaApi.getUserLogged(accessToken);
    }

    @NonNull
    private UserSessionRequest getUserSessionRequest(String email, String password, String deviceToken) {
        UserSessionRequest userSessionRequest = new UserSessionRequest();
        userSessionRequest.session = new UserSessionRequest.Session();
        userSessionRequest.session.email = email;
        userSessionRequest.session.password = password;
        userSessionRequest.session.deviceToken = deviceToken;
        userSessionRequest.session.deviceType = "android";
        return userSessionRequest;
    }

    public void updateDeviceToken(String deviceToken) {

        UpdateDeviceTokenRequest.User user = new UpdateDeviceTokenRequest.User();
        user.deviceToken = deviceToken;

        UpdateDeviceTokenRequest updateDeviceTokenRequest = new UpdateDeviceTokenRequest();
        updateDeviceTokenRequest.user = user;

        String accessToken = new UserSessionManager(App.getContext()).getAuthorizationToken();
        oupaApi.updateDeviceToken(accessToken, updateDeviceTokenRequest)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
    }
}
