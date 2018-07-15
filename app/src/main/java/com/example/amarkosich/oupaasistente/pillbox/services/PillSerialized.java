package com.example.amarkosich.oupaasistente.pillbox.services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PillSerialized {
    @Expose
    @SerializedName("personal_medicine_reminder")
    public Personal_medicine_reminder personal_medicine_reminder;

    public static class Personal_medicine_reminder {
        @Expose
        @SerializedName("name")
        public String name;
        @Expose
        @SerializedName("notes")
        public String notes;
        @Expose
        @SerializedName("date")
        public String date;
        @Expose
        @SerializedName("time")
        public String time;
    }
}
