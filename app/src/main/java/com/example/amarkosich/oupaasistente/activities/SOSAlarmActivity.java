package com.example.amarkosich.oupaasistente.activities;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.amarkosich.oupaasistente.R;

public class SOSAlarmActivity extends AppCompatActivity {

    private Button acceptButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sosalarm);

        acceptButton = findViewById(R.id.aceptButton);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.alarm);
        mp.start();

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                finish();
            }
        });


    }
}
