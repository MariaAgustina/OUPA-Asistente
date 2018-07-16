package com.example.amarkosich.oupaasistente.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.amarkosich.oupaasistente.R;

import static android.content.Intent.FLAG_ACTIVITY_FORWARD_RESULT;


public class NewContact2 extends AppCompatActivity {

//    Calendar myCalendar = Calendar.getInstance();
    public static final int STEP_CODE = 200;

    private LinearLayout contacto1;
    private LinearLayout contacto2;
    private LinearLayout contacto3;
    private LinearLayout contacto4;
    private LinearLayout contacto5;
    private LinearLayout contacto6;
    private TextView momentaneo;
    private String picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact2);
        contacto1 = findViewById(R.id.contact1Layout);
        contacto2 = findViewById(R.id.contact2Layout);
        contacto3 = findViewById(R.id.contact3Layout);
        contacto4 = findViewById(R.id.contact4Layout);
        contacto5 = findViewById(R.id.contact5Layout);
        contacto6 = findViewById(R.id.contact6Layout);
        attachEvents();

    }

    private void attachEvents() {
        contacto1.setOnClickListener(callListenerCon1());
        contacto2.setOnClickListener(callListenerCon2());
        contacto3.setOnClickListener(callListenerCon3());
        contacto4.setOnClickListener(callListenerCon4());
        contacto5.setOnClickListener(callListenerCon5());
        contacto6.setOnClickListener(callListenerCon6());
    }

    private View.OnClickListener callListenerCon1() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Contact contact = (Contact) getIntent().getSerializableExtra("contact");
                contact.picture = "contacto1";

                Intent intent = new Intent(NewContact2.this, NewContact4.class);
                intent.putExtra("contact", contact);
                intent.addFlags(FLAG_ACTIVITY_FORWARD_RESULT);
                startActivity(intent);
                finish();
            }
        };
    }
    private View.OnClickListener callListenerCon2() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Contact contact = (Contact) getIntent().getSerializableExtra("contact");
                contact.picture = "contacto2";

                Intent intent = new Intent(NewContact2.this, NewContact4.class);
                intent.putExtra("contact", contact);
                intent.addFlags(FLAG_ACTIVITY_FORWARD_RESULT);
                startActivity(intent);
                finish();
            }
        };
    }
    private View.OnClickListener callListenerCon3() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Contact contact = (Contact) getIntent().getSerializableExtra("contact");
                contact.picture = "contacto3";

                Intent intent = new Intent(NewContact2.this, NewContact4.class);
                intent.putExtra("contact", contact);
                intent.addFlags(FLAG_ACTIVITY_FORWARD_RESULT);
                startActivity(intent);
                finish();
            }
        };
    }
    private View.OnClickListener callListenerCon4() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Contact contact = (Contact) getIntent().getSerializableExtra("contact");
                contact.picture = "contacto4";

                Intent intent = new Intent(NewContact2.this, NewContact4.class);
                intent.putExtra("contact", contact);
                intent.addFlags(FLAG_ACTIVITY_FORWARD_RESULT);
                startActivity(intent);
                finish();
            }
        };
    }
    private View.OnClickListener callListenerCon5() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Contact contact = (Contact) getIntent().getSerializableExtra("contact");
                contact.picture = "contacto5";

                Intent intent = new Intent(NewContact2.this, NewContact4.class);
                intent.putExtra("contact", contact);
                intent.addFlags(FLAG_ACTIVITY_FORWARD_RESULT);
                startActivity(intent);
                finish();
            }
        };
    }

    private View.OnClickListener callListenerCon6() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Contact contact = (Contact) getIntent().getSerializableExtra("contact");
                contact.picture = "contacto6";

                Intent intent = new Intent(NewContact2.this, NewContact4.class);
                intent.putExtra("contact", contact);
                intent.addFlags(FLAG_ACTIVITY_FORWARD_RESULT);
                startActivity(intent);
                finish();
            }
        };
    }



}
