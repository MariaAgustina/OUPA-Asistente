package com.example.amarkosich.oupaasistente.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateDeviceTokenRequest {

    @Expose
    @SerializedName("user")
    public User user;

    public static class User {
        @Expose
        @SerializedName("device_token")
        public String deviceToken;
    }
}
