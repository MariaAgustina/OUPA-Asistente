package com.example.amarkosich.oupaasistente.pillbox.services;

import android.content.Context;
import android.util.Log;

import com.example.amarkosich.oupaasistente.UserSessionManager;
import com.example.amarkosich.oupaasistente.networking.ApiClient;
import com.example.amarkosich.oupaasistente.networking.OupaApi;
import com.example.amarkosich.oupaasistente.pillbox.model.OUPADateFormat;
import com.example.amarkosich.oupaasistente.pillbox.model.Pill;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PillService {
    private OupaApi oupaApi;

    public PillService() {
        oupaApi = ApiClient.getInstance().getOupaClient();
    }

    public void createNewPill(final Pill pill, final PillClient client) {

        PillSerialized pillSerialized = new PillSerialized();
        pillSerialized.personal_medicine_reminder = new PillSerialized.Personal_medicine_reminder();
        pillSerialized.personal_medicine_reminder.name = pill.name;
        pillSerialized.personal_medicine_reminder.notes = "";//por ahora no se usa

        OUPADateFormat customDateFormat = new OUPADateFormat();
        String myFormat = customDateFormat.dateFormatForServer();
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

        String hourFormat = customDateFormat.timeFormatForServer();
        SimpleDateFormat hourdf = new SimpleDateFormat(hourFormat);

        pillSerialized.personal_medicine_reminder.date = sdf.format(pill.date);
        pillSerialized.personal_medicine_reminder.time = hourdf.format(pill.date);

        UserSessionManager userSessionManager = new UserSessionManager(client.getApplicationContext());

        String accessToken=userSessionManager.getAuthorizationToken();
        String oupaUserId=userSessionManager.getOupaAssisted().id;

        oupaApi.createPill(accessToken,"application/json",pillSerialized).enqueue(new Callback<PillResponse>() {

            @Override
            public void onResponse(Call<PillResponse> call, Response<PillResponse> response) {
                if (response.code() > 199 && response.code() < 300) {
                    Log.i("PILLSERVICE", "NEW PILL CREATED!!!");
                    client.onResponseSuccess(response.body());
                } else {
                    Log.e("PILLSERVICE", "ERROR CREATING PILL, CODE:"+response.code());
                    client.onResponseError();
                }
            }

            @Override
            public void onFailure(Call<PillResponse> call, Throwable t) {
                Log.e("PILLSERVICE", t.getMessage());
                client.onResponseError();
            }
        });
    }

    public void getPillsForToday(final PillClient delegate) {

        UserSessionManager userSessionManager = new UserSessionManager(delegate.getApplicationContext());

        String accessToken=userSessionManager.getAuthorizationToken();
        String oupaUserId=userSessionManager.getOupaAssisted().id;

        oupaApi.getPillsForToday(accessToken,oupaUserId).enqueue(new Callback<ArrayList<PillResponse>>() {

            @Override
            public void onResponse(Call<ArrayList<PillResponse>> call, Response<ArrayList<PillResponse>> response) {
                if (response.code() > 199 && response.code() < 300) {
                    if(response.body() != null) {
                        Log.i("PILLSERVICE", response.body().toString());
                        delegate.onResponseSuccess(response.body());
                    }else {
                        Log.i("PILLSERVICE", "NO RESPONSE");
                        delegate.onResponseError();
                    }
                } else {
                    if(response.body() != null) {
                        Log.e("PILLSERVICE", response.body().toString());
                    }else {
                        Log.e("PILLSERVICE", "NO RESPONSE");
                    }
                    delegate.onResponseError();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PillResponse>> call, Throwable t) {
                delegate.onResponseError();
                Log.e("PILLSERVICE", t.getMessage());
            }
        });
    }


}