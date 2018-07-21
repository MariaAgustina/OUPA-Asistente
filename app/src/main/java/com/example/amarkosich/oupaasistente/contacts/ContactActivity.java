package com.example.amarkosich.oupaasistente.contacts;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.amarkosich.oupaasistente.R;
import com.example.amarkosich.oupaasistente.contacts.services.ContactClient;
import com.example.amarkosich.oupaasistente.contacts.services.ContactResponse;
import com.example.amarkosich.oupaasistente.contacts.services.ContactService;
import com.example.amarkosich.oupaasistente.contacts.views.ContactAdapter;

import java.util.ArrayList;


public class ContactActivity extends AppCompatActivity implements ContactClient {

    private ArrayList<Contact> contactArray;
    private ContactService contactService;

    public static final int REQUEST_CODE = 1;
    public static final int RESULT_CODE_ADDED_CONTACT = 400;

    public ContactActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        contactService = new ContactService();


        ImageButton newContactButton = (ImageButton) findViewById(R.id.btn_contact);
        newContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactActivity.this, NewContact.class);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });

        setupInitials();
    }

    private void setupInitials() {
        contactArray = new ArrayList<Contact>();
        contactService.getContacts(this);

    }


    private void displayContacts() {
        final ListView contactList = (ListView) findViewById(R.id.list_of_contacts);
        ContactAdapter contactAdapter = new ContactAdapter(this, contactArray);
        contactList.setAdapter(contactAdapter);
        contactList.setSelection(this.contactArray.size());

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == REQUEST_CODE && resultCode == RESULT_CODE_ADDED_CONTACT) {
                ProgressBar loadingView = (ProgressBar) findViewById(R.id.loading);
                loadingView.setVisibility(View.VISIBLE);
                contactService.getContacts(this);
            }
        } catch (Exception ex) {
            Toast.makeText(this, ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }

    }

    public ContactActivity(ArrayList<Contact> contactArray) {
        this.contactArray = contactArray;
    }

    @Override
    public void onResponseSuccess(Object responseBody) {
        ArrayList<ContactResponse> contactResponseArrayList = (ArrayList<ContactResponse>) responseBody;

        for (ContactResponse contactResponse : contactResponseArrayList) {

            Contact contact = new Contact();
            contact.name = contactResponse.name;
            contact.phoneNumber = contactResponse.phoneNumber;
            contact.picture = contactResponse.picture;


            contactArray.add(contact);
        }

        ProgressBar loadingView = (ProgressBar) findViewById(R.id.loading);
        loadingView.setVisibility(View.INVISIBLE);
        displayContacts();
    }

    public void onResponseError() {
        Toast.makeText(this, "Se produjo un error de conexión en la agenda, intente luego",
                Toast.LENGTH_LONG).show();
        ProgressBar loadingView = (ProgressBar) findViewById(R.id.loading);
        loadingView.setVisibility(View.INVISIBLE);
        finish();

    }

}