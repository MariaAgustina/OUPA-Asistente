package com.example.amarkosich.oupaasistente.services;

import android.util.Log;

import com.example.amarkosich.oupaasistente.UserManager;
import com.example.amarkosich.oupaasistente.model.UserSession;
import com.example.amarkosich.oupaasistente.model.request.UpdateDeviceTokenRequest;
import com.example.amarkosich.oupaasistente.model.request.UserSessionRequest;
import com.example.amarkosich.oupaasistente.networking.ApiClient;
import com.example.amarkosich.oupaasistente.networking.OupaApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserService {

    private OupaApi oupaApi;

    public UserService() {
        oupaApi = ApiClient.getInstance().getOupaClient();
    }

    public void createUserSession(String email, String password, String deviceToken) {

        UserSessionRequest userSessionRequest = new UserSessionRequest();
        userSessionRequest.session = new UserSessionRequest.Session();
        userSessionRequest.session.email = email;
        userSessionRequest.session.password = password;
        userSessionRequest.session.deviceToken = deviceToken;
        userSessionRequest.session.deviceType = "android";

        oupaApi.createUserSession(userSessionRequest).enqueue(new Callback<UserSession>() {
            @Override
            public void onResponse(Call<UserSession> call, Response<UserSession> response) {
                if (response.code() > 199 && response.code() < 300) {
                    Log.i("Session created", response.body().accessToken);
                    UserManager.getInstance().setUserSession(response.body());
                } else {
                    Log.e("createUserSession", response.message());
                }
            }

            @Override
            public void onFailure(Call<UserSession> call, Throwable t) {
                Log.e("createUserSession", t.getMessage());
            }
        });
    }

    public void updateDeviceToken(String deviceToken) {

        UpdateDeviceTokenRequest.User user = new UpdateDeviceTokenRequest.User();
        user.deviceToken = deviceToken;

        UpdateDeviceTokenRequest updateDeviceTokenRequest = new UpdateDeviceTokenRequest();
        updateDeviceTokenRequest.user = user;

        oupaApi.updateDeviceToken(UserManager.getInstance().getAuthorizationToken(), updateDeviceTokenRequest)
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
