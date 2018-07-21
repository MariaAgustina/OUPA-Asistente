package com.example.amarkosich.oupaasistente.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amarkosich.oupaasistente.R;
import com.example.amarkosich.oupaasistente.contacts.services.ContactClient;
import com.example.amarkosich.oupaasistente.contacts.services.ContactResponse;
import com.example.amarkosich.oupaasistente.contacts.services.ContactService;

import java.util.HashMap;
import java.util.Map;

public class NewContact4 extends AppCompatActivity implements ContactClient {

    private  Contact contact;
    public static final int STEP_CODE = 400;
    private ContactService contactService;

    private Map<String, Integer> mapContactToImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact4);

        contactService = new ContactService();


        mapContactToImg = new HashMap<>();
        mapContactToImg.put("contacto1", R.drawable.contact1);
        mapContactToImg.put("contacto2", R.drawable.contact2);
        mapContactToImg.put("contacto3", R.drawable.contact3);
        mapContactToImg.put("contacto4", R.drawable.contact4);
        mapContactToImg.put("contacto5", R.drawable.contact5);
        mapContactToImg.put("contacto6", R.drawable.contact6);
        mapContactToImg.put("picture1", R.drawable.contact1);
        mapContactToImg.put("picture2", R.drawable.contact2);
        mapContactToImg.put("picture3", R.drawable.contact3);
        mapContactToImg.put("picture4", R.drawable.contact4);
        mapContactToImg.put("picture5", R.drawable.contact5);
        mapContactToImg.put("picture6", R.drawable.contact6);
        mapContactToImg.put("contact1.png", R.drawable.contact1);
        mapContactToImg.put("contact2.png", R.drawable.contact2);
        mapContactToImg.put("contact3.png", R.drawable.contact3);
        mapContactToImg.put("contact4.png", R.drawable.contact4);
        mapContactToImg.put("contact5.png", R.drawable.contact5);
        mapContactToImg.put("contact6.png", R.drawable.contact6);
        mapContactToImg.put("contacto1.png", R.drawable.contact1);
        mapContactToImg.put("contacto2.png", R.drawable.contact2);
        mapContactToImg.put("contacto3.png", R.drawable.contact3);
        mapContactToImg.put("contacto4.png", R.drawable.contact4);
        mapContactToImg.put("contacto5.png", R.drawable.contact5);
        mapContactToImg.put("contacto6.png", R.drawable.contact6);
        mapContactToImg.put("picture1.png", R.drawable.contact1);
        mapContactToImg.put("picture2.png", R.drawable.contact2);
        mapContactToImg.put("picture3.png", R.drawable.contact3);
        mapContactToImg.put("picture4.png", R.drawable.contact4);
        mapContactToImg.put("picture5.png", R.drawable.contact5);
        mapContactToImg.put("picture6.png", R.drawable.contact6);


        contact = (Contact) getIntent().getSerializableExtra("contact");
        setUpContact();
    }

    private void setUpContact(){

        TextView contactName = findViewById(R.id.contact_name);
        contactName.setText(contact.name);

        TextView contactPhoneNumber = findViewById(R.id.contact_phone);
        contactPhoneNumber.setText(contact.phoneNumber);


        ImageView contactPicture = findViewById(R.id.contactImage);
        Integer value = mapContactToImg.get(contact.picture);

        if (value == null){
            value = R.drawable.contact6;
        }
        contactPicture.setImageResource(value);

    }

    public void confirmButtonPressed(View view) {
        ProgressBar loadingView = findViewById(R.id.loading);
        loadingView.setVisibility(View.VISIBLE);
        contactService.createNewContact(contact,this);
    }

    public void onResponseSuccess(Object responseBody) {

        ContactResponse contactResponse = (ContactResponse) responseBody;

        ProgressBar loadingView = (ProgressBar) findViewById(R.id.loading);
        loadingView.setVisibility(View.INVISIBLE);

        Intent intent = new Intent(NewContact4.this, ContactActivity.class);
        setResult(STEP_CODE, intent);
        finish();
    }


    public void onResponseError() {
        Toast.makeText(this, "Se produjo un error de conexión en la agenda, intente luego",
                Toast.LENGTH_LONG).show();
        ProgressBar loadingView = (ProgressBar) findViewById(R.id.loading);
        loadingView.setVisibility(View.INVISIBLE);
        finish();

    }
}
