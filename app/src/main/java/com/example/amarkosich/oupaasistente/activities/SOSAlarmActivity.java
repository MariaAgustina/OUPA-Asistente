package com.example.amarkosich.oupaasistente.activities;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.amarkosich.oupaasistente.R;

public class SOSAlarmActivity extends AppCompatActivity {

    private Button acceptButton;
    private TextView alarmText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sosalarm);

        String alarmMessage = getIntent().getStringExtra("SOS_MESSAGE");
        alarmText = findViewById(R.id.alarmMessageText);
        alarmText.setText(alarmMessage);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.alarm);
        mp.start();

        acceptButton = findViewById(R.id.aceptButton);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                finish();
            }
        });


    }
}
