package com.example.amarkosich.oupaasistente.pillbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;

import android.widget.TextView;

import com.example.amarkosich.oupaasistente.R;
import com.example.amarkosich.oupaasistente.pillbox.model.Pill;


public class NewPillStep4 extends AppCompatActivity {

    private Pill pill;
    public static final int STEP_CODE = 400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pill_step4);

        pill = (Pill) getIntent().getSerializableExtra("pill");

        setupPillDescription();
    }

    private void setupPillDescription(){

        TextView pillName = findViewById(R.id.pill_name);
        pillName.setText(pill.name);

    }


    public void backButtonPressed(View view) {
        Intent intent = new Intent(NewPillStep4.this, PillboxActivity.class);
        setResult(STEP_CODE, intent);
        finish();
    }



}
