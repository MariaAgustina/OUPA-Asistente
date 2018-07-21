package com.example.amarkosich.oupaasistente.measurements;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import com.example.amarkosich.oupaasistente.R;


import com.example.amarkosich.oupaasistente.measurements.model.Measurement;

import static android.content.Intent.FLAG_ACTIVITY_FORWARD_RESULT;

public class NewMeasurementStep1 extends AppCompatActivity {

    private Measurement measurement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_measurement_step1);
        measurement = new Measurement();
        attachEvents();
    }

    private void attachEvents() {

        LinearLayout temperatureLayout = findViewById(R.id.temperature_layout);
        temperatureLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                measurement.measurement_type = "temperature";
                startStep2();
            }
        });

        LinearLayout glucoseLayout = findViewById(R.id.glucose_layout);
        glucoseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                measurement.measurement_type = "glucose";
                startStep2();
            }
        });

        LinearLayout cameraLayout = findViewById(R.id.preasure_layout);
        cameraLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                measurement.measurement_type = "preasure";
                startStep2();
            }
        });

    }

    private void startStep2() {

        Intent intent = new Intent(NewMeasurementStep1.this, NewMeasurementStep2.class);
        intent.addFlags(FLAG_ACTIVITY_FORWARD_RESULT);
        intent.putExtra("measurement", measurement);
        startActivity(intent);
        finish();

    }

}


