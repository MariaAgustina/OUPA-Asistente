package com.example.amarkosich.oupaasistente.activities;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
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
        if (alarmMessage != null) {
            alarmText = findViewById(R.id.alarmMessageText);
            alarmText.setText(alarmMessage);
        }

        getSupportActionBar().hide();

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.alarm);
        mp.start();

        final ScaleAnimation growAnim = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
        final ScaleAnimation shrinkAnim = new ScaleAnimation(1.5f, 1.0f, 1.5f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);

        growAnim.setDuration(500);
        shrinkAnim.setDuration(500);

        final ImageView heartView = findViewById(R.id.heart_pulse_icon);

        heartView.setAnimation(growAnim);
        growAnim.start();

        growAnim.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation){}

            @Override
            public void onAnimationRepeat(Animation animation){}

            @Override
            public void onAnimationEnd(Animation animation)
            {
                heartView.setAnimation(shrinkAnim);
                shrinkAnim.start();
            }
        });
        shrinkAnim.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation){}

            @Override
            public void onAnimationRepeat(Animation animation){}

            @Override
            public void onAnimationEnd(Animation animation)
            {
                heartView.setAnimation(growAnim);
                growAnim.start();
            }
        });


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
