package com.example.amarkosich.oupaasistente.pillbox;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;

import android.widget.TextView;

import com.example.amarkosich.oupaasistente.R;
import com.example.amarkosich.oupaasistente.UserSessionManager;
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

        UserSessionManager userSessionManager = new UserSessionManager(this.getApplicationContext());

        String assistedName=userSessionManager.getOupaAssisted().firstName+" "+userSessionManager.getOupaAssisted().lastName;
        TextView pillText = findViewById(R.id.pill_notification);


        String normalText = "a la lista de medicinas a tomar de ";

        SpannableString str = new SpannableString(normalText+assistedName);
        str.setSpan(new StyleSpan(Typeface.BOLD), normalText.length(), normalText.length()+assistedName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        pillText.setText(str);

    }


    public void backButtonPressed(View view) {
        Intent intent = new Intent(NewPillStep4.this, PillboxActivity.class);
        setResult(STEP_CODE, intent);
        finish();
    }



}
