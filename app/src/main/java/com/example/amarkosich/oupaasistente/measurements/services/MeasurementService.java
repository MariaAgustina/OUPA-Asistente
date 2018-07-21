package com.example.amarkosich.oupaasistente.measurements.services;

import android.util.Log;


import com.example.amarkosich.oupaasistente.UserSessionManager;
import com.example.amarkosich.oupaasistente.measurements.MeasurementListActivity;
import com.example.amarkosich.oupaasistente.measurements.NewMeasurementStep2;
import com.example.amarkosich.oupaasistente.measurements.model.Measurement;
import com.example.amarkosich.oupaasistente.networking.ApiClient;
import com.example.amarkosich.oupaasistente.networking.OupaApi;
import com.example.amarkosich.oupaasistente.pillbox.model.OUPADateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeasurementService {
    private OupaApi oupaApi;

    public MeasurementService() {
        oupaApi = ApiClient.getInstance().getOupaClient();
    }

    public void createNewMeasurement(final Measurement measurement, final NewMeasurementStep2 delegate) {

        MeasurementSerialized measurementSerialized = new MeasurementSerialized();
        measurementSerialized.measurement = new MeasurementSerialized.Measurement();
        measurementSerialized.measurement.measurement_type = measurement.measurement_type;
        measurementSerialized.measurement.notes = "";//por ahora no se usa
        measurementSerialized.measurement.value = measurement.value;

        OUPADateFormat customDateFormat = new OUPADateFormat();
        String myFormat = customDateFormat.dateFormatForServer();
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

        String hourFormat = customDateFormat.timeFormatForServer();
        SimpleDateFormat hourdf = new SimpleDateFormat(hourFormat);

        measurementSerialized.measurement.date = sdf.format(measurement.date);

        String accessToken = new UserSessionManager(delegate.getApplicationContext()).getAuthorizationToken();

        oupaApi.createMeasurement(accessToken,"application/json",measurementSerialized).enqueue(new Callback<MeasurementSerialized>() {

            @Override
            public void onResponse(Call<MeasurementSerialized> call, Response<MeasurementSerialized> response) {
                if (response.code() > 199 && response.code() < 300) {
                    Log.i("MEASUREMENTESERVICE", "NEW MEASUREMENTE CREATED!!!");
                    delegate.onResponseSuccess(response.body());
                } else {
                    Log.e("MEASUREMENTESERVICE", response.body().toString());
                    delegate.onResponseError();
                }
            }

            @Override
            public void onFailure(Call<MeasurementSerialized> call, Throwable t) {
                Log.e("MEASUREMENTESERVICE", t.getMessage());
                delegate.onResponseError();
            }
        });
    }

    public void getMeasurements(final MeasurementListActivity delegate) {
        UserSessionManager userSessionManager = new UserSessionManager(delegate.getApplicationContext());

        String accessToken=userSessionManager.getAuthorizationToken();
        String oupaUserId=userSessionManager.getOupaAssisted().id;

        oupaApi.getMeasurements(accessToken,oupaUserId).enqueue(new Callback<ArrayList<MeasurementSerialized.Measurement>>() {
            @Override
            public void onResponse(Call<ArrayList<MeasurementSerialized.Measurement>> call, Response<ArrayList<MeasurementSerialized.Measurement>> response) {
                if (response.code() > 199 && response.code() < 300) {
                    if(response.body() != null) {
                        Log.i("MEASUREMENTESERVICE", response.body().toString());
                        delegate.onResponseSuccess(response.body());
                    }else {
                        Log.i("MEASUREMENTESERVICE", "NO RESPONSE");
                        delegate.onResponseError();
                    }
                } else {
                    if(response.body() != null) {
                        Log.e("MEASUREMENTESERVICE", response.body().toString());
                    }else {
                        Log.e("MEASUREMENTESERVICE", "NO RESPONSE");
                    }
                    delegate.onResponseError();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<MeasurementSerialized.Measurement>> call, Throwable t) {
                delegate.onResponseError();
                Log.e("MEASUREMENTESERVICE", t.getMessage());
            }
        });
    }
}
