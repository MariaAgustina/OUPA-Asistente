package com.example.amarkosich.oupaasistente.pillbox.services;

import android.content.Context;

public interface PillClient<T> {

    public abstract void onResponseSuccess(T responseBody);

    public abstract void onResponseError();

    public abstract Context getApplicationContext();
}
