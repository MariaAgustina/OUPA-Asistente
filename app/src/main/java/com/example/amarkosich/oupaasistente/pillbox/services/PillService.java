package com.example.amarkosich.oupaasistente.pillbox.services;

import android.content.Context;
import android.util.Log;

import com.example.amarkosich.oupaasistente.UserManager;
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

        oupaApi.createPill("eyJhbGciOiJIUzI1NiJ9.eyJlbGRlcmx5X3VzZXJfaWQiOjIsInZlcmlmaWNhdGlvbl9jb2RlIjoiVF8xX01hQjdndU5Cb2lTLXN3TndnX3l4Qm5SOWVDenN4amZna0NnLTdKekJ6X3hZZHluVlgta0d5UjZqSGtLeiIsInJlbmV3X2lkIjoicjlOdFRpRTRvNk5NdmtFTmhMeVRzRUNoYmNwMVpCMkEiLCJtYXhpbXVtX3VzZWZ1bF9kYXRlIjoxNTMyMDEwNjYzLCJleHBpcmF0aW9uX2RhdGUiOjE1MzIwMTA2NjMsIndhcm5pbmdfZXhwaXJhdGlvbl9kYXRlIjoxNTI5NDM2NjYzfQ.Un5EecwJ1i-bgaZf6j1TEYHinX9ni0-jb3h6m_EdgOU","application/json",pillSerialized).enqueue(new Callback<PillResponse>() {

            @Override
            public void onResponse(Call<PillResponse> call, Response<PillResponse> response) {
                if (response.code() > 199 && response.code() < 300) {
                    Log.i("PILLSERVICE", "NEW PILL CREATED!!!");
                    client.onResponseSuccess(response.body());
                } else {
                    Log.e("PILLSERVICE", response.body().toString());
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
        oupaApi.getPillsForToday("eyJhbGciOiJIUzI1NiJ9.eyJlbGRlcmx5X3VzZXJfaWQiOjIsInZlcmlmaWNhdGlvbl9jb2RlIjoiVF8xX01hQjdndU5Cb2lTLXN3TndnX3l4Qm5SOWVDenN4amZna0NnLTdKekJ6X3hZZHluVlgta0d5UjZqSGtLeiIsInJlbmV3X2lkIjoicjlOdFRpRTRvNk5NdmtFTmhMeVRzRUNoYmNwMVpCMkEiLCJtYXhpbXVtX3VzZWZ1bF9kYXRlIjoxNTMyMDEwNjYzLCJleHBpcmF0aW9uX2RhdGUiOjE1MzIwMTA2NjMsIndhcm5pbmdfZXhwaXJhdGlvbl9kYXRlIjoxNTI5NDM2NjYzfQ.Un5EecwJ1i-bgaZf6j1TEYHinX9ni0-jb3h6m_EdgOU").enqueue(new Callback<ArrayList<PillResponse>>() {

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

    public void updatePillDrinked(Context applicationContext, final Pill pill){
        PillTakenSerialized pillTakenSerialized = new PillTakenSerialized();
        pillTakenSerialized.personal_medicine_reminder = new PillTakenSerialized.Personal_medicine_reminder();
        pillTakenSerialized.personal_medicine_reminder.taken = pill.drinked;

        oupaApi.drinkedPill(UserManager.getInstance().getAuthorizationToken(),pill.id,pillTakenSerialized).enqueue(new Callback<PillResponse>() {

            @Override
            public void onResponse(Call<PillResponse> call, Response<PillResponse> response) {
                if (response.code() > 199 && response.code() < 300) {
                    Log.i("PILLSERVICE", "PILL " +pill.id+" UPDATED!!!");
                } else {
                    Log.e("PILLSERVICE", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<PillResponse> call, Throwable t) {
                Log.e("PILLSERVICE", t.getMessage());
            }
        });
    }
}