package com.example.amarkosich.oupaasistente.contacts.services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactSerialized {
    @Expose
    @SerializedName("contact")
    public ContactS contact;

    public static class ContactS {
        @Expose
        @SerializedName("name")
        public String name;

        @SerializedName("phone_number")
        public String phoneNumber;

        @SerializedName("picture")
        public String picture;
    }
}
