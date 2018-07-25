package com.example.amarkosich.oupaasistente.pillbox;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.example.amarkosich.oupaasistente.R;
import com.example.amarkosich.oupaasistente.pillbox.model.OUPADateFormat;
import com.example.amarkosich.oupaasistente.pillbox.model.Pill;
import com.example.amarkosich.oupaasistente.pillbox.services.PillClient;
import com.example.amarkosich.oupaasistente.pillbox.services.PillResponse;
import com.example.amarkosich.oupaasistente.pillbox.services.PillService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.content.Intent.FLAG_ACTIVITY_FORWARD_RESULT;


public class NewPillStep1 extends AppCompatActivity implements PillClient{

    Calendar date;
    //Context context = this;
    private PillService pillService;
    Pill pill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pill_step1);

        date = Calendar.getInstance();
        pillService = new PillService();
    }

    public void confirmButtonPressed(View view) {
        TextView pillNameInput = (TextView) findViewById(R.id.newPillInput);
        String pillName = pillNameInput.getText().toString();
        if (pillName.isEmpty()) {
            //TODO: ver si se puede hacer el snackbar custom y con letra grande
            Snackbar.make(view, "Ingrese el nombre de la medicina", Snackbar.LENGTH_LONG)
                      .setAction("Action", null).show();
            return;
        } else {
            TextView pillDateText = findViewById(R.id.selectedDate);
            String pillDate = pillDateText.getText().toString();
            if(pillDate.isEmpty()){
                Snackbar.make(view, "Ingrese la fecha para tomar la medicina", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }
        pill= new Pill();
        pill.name = pillName;
        pill.date = date.getTime();

        ProgressBar loadingView = findViewById(R.id.loading);
        loadingView.setVisibility(View.VISIBLE);
        pillService.createNewPill(pill,this);
    }

    private void updateDate() {
        OUPADateFormat customDateFormat = new OUPADateFormat();
        /*DateFormat hourFormat = new SimpleDateFormat(customDateFormat.timeFormatForServer());
        TextView pillHourText = findViewById(R.id.pill_hour_info);
        String hours = hourFormat.format(date.getTime());
        pillHourText.setText("Hora: " + hours);*/

        DateFormat dateFormat = new SimpleDateFormat(customDateFormat.dateTimeFormatPill());
        TextView pillDateText = findViewById(R.id.selectedDate);
        String fecha = dateFormat.format(date.getTime());
        pillDateText.setText("Fecha: " + fecha);
    }


    public void showDateTimePicker(View view) {
        final Calendar currentDate = Calendar.getInstance();
;

        new DatePickerDialog(NewPillStep1.this , new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(NewPillStep1.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        date.set(Calendar.MINUTE, minute);
                        Log.v("NEWPILLSTEP1", "The choosen one " + date.getTime());
                        updateDate();
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), true).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();

    }

    public void onResponseSuccess(Object responseBody) {

        PillResponse pillResponse = (PillResponse) responseBody;

        ProgressBar loadingView = (ProgressBar) findViewById(R.id.loading);
        loadingView.setVisibility(View.INVISIBLE);

        Intent intent = new Intent(NewPillStep1.this, NewPillStep4.class);
        intent.putExtra("pill", pill );
        intent.addFlags(FLAG_ACTIVITY_FORWARD_RESULT);
        startActivity(intent);
        finish();
    }

    public void onResponseError() {
        Toast.makeText(this, "Se produjo un error de conexión en el pastillero, intente luego",
                Toast.LENGTH_LONG).show();
        ProgressBar loadingView = (ProgressBar) findViewById(R.id.loading);
        loadingView.setVisibility(View.INVISIBLE);
        finish();

    }

}
