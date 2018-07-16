package com.example.amarkosich.oupaasistente.contacts.services;

import android.content.Context;

public interface ContactClient<T> {

    public abstract void onResponseSuccess(T responseBody);

    public abstract void onResponseError();

    public abstract Context getApplicationContext();
}
