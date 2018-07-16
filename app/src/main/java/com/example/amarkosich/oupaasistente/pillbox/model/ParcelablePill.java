package com.example.amarkosich.oupaasistente.pillbox.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParcelablePill implements Parcelable {

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

    public boolean shouldBeDrinked() {
        Date now = new Date();
        return now.after(this.date);
    }

    public ParcelablePill(){

    }


    protected ParcelablePill(Parcel in) {
        name = in.readString();
        notes = in.readString();
        long tmpDate = in.readLong();
        date = tmpDate != -1 ? new Date(tmpDate) : null;
        repeat = in.readByte() != 0x00;
        drinked = in.readByte() != 0x00;
        id = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(notes);
        dest.writeLong(date != null ? date.getTime() : -1L);
        dest.writeByte((byte) (repeat ? 0x01 : 0x00));
        dest.writeByte((byte) (drinked ? 0x01 : 0x00));
        dest.writeString(id);
    }

    @SuppressWarnings("unused")
    public static final Creator<ParcelablePill> CREATOR = new Creator<ParcelablePill>() {
        @Override
        public ParcelablePill createFromParcel(Parcel in) {
            return new ParcelablePill(in);
        }

        @Override
        public ParcelablePill[] newArray(int size) {
            return new ParcelablePill[size];
        }
    };

}