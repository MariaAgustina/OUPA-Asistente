package com.example.amarkosich.oupaasistente.pillbox.model;

public class OUPADateFormat {
    public OUPADateFormat() {
    }

    public String dateFormatForServer() {
        return "yyyy-MM-dd";
    }

    public String dateFormatPill() {
        return "dd/MM/yyyy";
    }

    public String timeFormatForServer() {
        return "HH:mm";
    }

    public String dateTimeFormatPill() {
        return "dd/MM/yyyy HH:mm";
    }
}
