package com.example.amarkosich.oupaasistente.measurements.services;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MeasurementSerialized {
    @Expose
    @SerializedName("measurement")
    public Measurement measurement;

    public static class Measurement {
        @Expose
        @SerializedName("measurement_type")
        public String measurement_type;
        @Expose
        @SerializedName("value")
        public String value;
        @Expose
        @SerializedName("date")
        public String date;
        @Expose
        @SerializedName("notes")
        public String notes;
    }
}
