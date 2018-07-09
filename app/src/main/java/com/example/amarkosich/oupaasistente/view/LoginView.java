package com.example.amarkosich.oupaasistente.view;


import com.example.amarkosich.oupaasistente.model.UserLogged;

public interface LoginView {
    void validateEmail();
    void validatePassword();
    void showProgress(boolean showLoading);
    void onError();
    void onSuccess(UserLogged body, String accessToken);
}
