package com.example.amarkosich.oupaasistente.contacts.services;

import android.util.Log;


import com.example.amarkosich.oupaasistente.UserSessionManager;
import com.example.amarkosich.oupaasistente.contacts.Contact;
import com.example.amarkosich.oupaasistente.contacts.NewContact4;
import com.example.amarkosich.oupaasistente.networking.ApiClient;
import com.example.amarkosich.oupaasistente.networking.OupaApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactService {
    private OupaApi oupaApi;

    public ContactService() {
        oupaApi = ApiClient.getInstance().getOupaClient();
    }

    public Call<ContactResponse> createContact(String accessToken, String s, ContactSerialized contactSerialized) {

        return oupaApi.createContact(accessToken, s, contactSerialized);
    }

    public void createNewContact(final Contact contact, final NewContact4 delegate) {

        ContactSerialized contactSerialized = new ContactSerialized();
        contactSerialized.contact = new ContactSerialized.ContactS();
        contactSerialized.contact.name = contact.name;
        contactSerialized.contact.phoneNumber = contact.phoneNumber;
        contactSerialized.contact.picture = contact.picture;

        UserSessionManager userSessionManager = new UserSessionManager(delegate.getApplicationContext());

        String accessToken = userSessionManager.getAuthorizationToken();
//        String oupaUserId = userSessionManager.getOupaAssisted().id;

        createContact(accessToken,"application/json",contactSerialized).enqueue(new Callback<ContactResponse>() {

            @Override
            public void onResponse(Call<ContactResponse> call, Response<ContactResponse> response) {
                if (response.code() > 199 && response.code() < 300) {
                    Log.i("CONTACTSERVICE", "NEW CONTACT CREATED!!!");
                    delegate.onResponseSuccess(response.body());
                } else {
                    Log.e("CONTACTSERVICE", response.body().toString());
                    delegate.onResponseError();
                }
            }

            @Override
            public void onFailure(Call<ContactResponse> call, Throwable t) {
                Log.e("CONTACTSERVICE", t.getMessage());
                delegate.onResponseError();
            }
        });
    }

    public void getContacts(final ContactClient delegate) {

        UserSessionManager userSessionManager = new UserSessionManager(delegate.getApplicationContext());

        String accessToken = userSessionManager.getAuthorizationToken();
        String oupaUserId = userSessionManager.getOupaAssisted().id;

        oupaApi.getContacts(accessToken, oupaUserId).enqueue(new Callback<ArrayList<ContactResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<ContactResponse>> call, Response<ArrayList<ContactResponse>> response) {
                if (response.code() > 199 && response.code() < 300) {
                    if(response.body() != null) {
                        Log.i("CONTACTSERVICE", response.body().toString());
                        delegate.onResponseSuccess(response.body());
                    }else {
                        Log.i("CONTACTSERVICE", "NO RESPONSE");
                        delegate.onResponseError();
                    }
                } else {
                    if(response.body() != null) {
                        Log.e("CONTACTSERVICE", response.body().toString());
                    }else {
                        Log.e("CONTACTSERVICE", "NO RESPONSE");
                    }
                    delegate.onResponseError();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ContactResponse>> call, Throwable t) {
                delegate.onResponseError();
                Log.e("CONTACTSERVICE", t.getMessage());
            }
        });
    }
}
