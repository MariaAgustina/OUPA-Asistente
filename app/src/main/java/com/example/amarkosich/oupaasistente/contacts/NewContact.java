package com.example.amarkosich.oupaasistente.contacts;

import android.content.Intent;
import android.os.Bundle;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


import com.example.amarkosich.oupaasistente.R;

import static android.content.Intent.FLAG_ACTIVITY_FORWARD_RESULT;

public class NewContact extends AppCompatActivity {

    public static final int STEP_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
    }

    public void confirmButtonPressed(View view) {
        TextView contactNameInput = (TextView) findViewById(R.id.newContactName);
        String contactName = contactNameInput.getText().toString();
        if (contactName.equals("")) {
            //TODO: ver si se puede hacer el snackbar custom y con letra grande
            Snackbar.make(view, "Ingrese el nombre del contacto", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return;
        }
        TextView contactPhoneInput = (TextView) findViewById(R.id.newContactPhone);
        String contactPhone = contactPhoneInput.getText().toString();
        if (contactPhone.equals("")) {
            //TODO: ver si se puede hacer el snackbar custom y con letra grande
            Snackbar.make(view, "Ingrese el telefono del contacto", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return;
        }
//        TextView contactPictureInput = (TextView) findViewById(R.id.new);
//        String contactName = contactNameInput.getText().toString();
//        if (contactName.equals("")) {
//            //TODO: ver si se puede hacer el snackbar custom y con letra grande
//            Snackbar.make(view, "Ingrese el nombre del contacto", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
//            return;
//        }

        Contact contact = new Contact();
        contact.name = contactName;
        contact.phoneNumber = contactPhone;

        Intent intent = new Intent(NewContact.this, NewContact2.class);
        intent.addFlags(FLAG_ACTIVITY_FORWARD_RESULT);
        intent.putExtra("contact", contact);
        startActivity(intent);
        finish();
    }


}
