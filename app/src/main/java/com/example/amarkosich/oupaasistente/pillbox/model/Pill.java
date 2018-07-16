package com.example.amarkosich.oupaasistente.pillbox.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pill implements Serializable{

    public String name;
    public String notes;
    public Date date;
    public boolean repeat;
    public boolean drinked;
    public String id;

    public String hourString() {
        DateFormat df = new SimpleDateFormat(new OUPADateFormat().timeFormatForServer());
        return df.format(this.date);
    }

    public boolean shouldBeDrinked(){
        Date now = new Date();
        return now.after(this.date);
    }

}
