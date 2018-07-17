package com.example.amarkosich.oupaasistente.pillbox.services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PillTakenSerialized {

    @Expose
    @SerializedName("personal_medicine_reminder")
    public Personal_medicine_reminder personal_medicine_reminder;

    public static class Personal_medicine_reminder {
        @Expose
        @SerializedName("taken")
        public boolean taken;

    }
}
