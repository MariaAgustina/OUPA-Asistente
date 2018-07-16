package com.example.amarkosich.oupaasistente.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UserLogged implements Serializable {

    @Expose
    public String id;

    @Expose
    public String email;

    @Expose
    public String type;

    @Expose
    @SerializedName("first_name")
    public String firstName;

    @Expose
    @SerializedName("last_name")
    public String lastName;

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
