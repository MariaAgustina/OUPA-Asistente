package com.example.amarkosich.oupaasistente;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.amarkosich.oupaasistente.activities.LoginActivity;
import com.example.amarkosich.oupaasistente.model.UserLogged;
import com.google.gson.Gson;

public class UserSessionManager {

    // Shared Preferences reference
    SharedPreferences pref;

    // Editor reference for Shared preferences
    SharedPreferences.Editor editor;

    private Context context;

    // Shared preferences mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    public static final String PREFER_NAME = "Reg";

    // All Shared Preferences Keys
    public static final String IS_USER_LOGIN = "isUserLoggedIn";

    // All Shared Preferences Keys
    public static final String OUPA_ASSISTED = "oupsAssisted";
    public static final String OUPA_LOGGED = "oupaLogged";

    public static final String KEY_FIRST_NAME = "firstName";
    public static final String KEY_LAST_NAME = "lastName";
    public static final String KEY_TOKEN = "accessToken";


    public UserSessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void saveUserLogged(UserLogged userLogged, String accessToken){
        editor.putBoolean(IS_USER_LOGIN, true);
        // Storing name in preferences
        Gson gson = new Gson();
        String userAsJson = gson.toJson(userLogged);
        editor.putString(OUPA_LOGGED, userAsJson);
        editor.putString(KEY_TOKEN, accessToken);

        // commit changes
        editor.commit();
    }

    public void saveOupaAssisted(UserLogged userLogged) {
        Gson gson = new Gson();
        String userAsJson = gson.toJson(userLogged);
        editor.putString(OUPA_ASSISTED, userAsJson);

        // commit changes
        editor.commit();
    }

    public String getAuthorizationToken() {
        String accessToken = pref.getString(KEY_TOKEN, null);
        return accessToken;
    }

    public UserLogged getOupaAssisted() {
        Gson gson = new Gson();
        String userAsJson = pref.getString(OUPA_ASSISTED, null);
        return gson.fromJson(userAsJson, UserLogged.class);

    }

    public boolean isDoctor() {
        return "DoctorUser".equals(getUserLogged().type);
    }

    public UserLogged getUserLogged() {
        Gson gson = new Gson();
        String userAsJson = pref.getString(OUPA_LOGGED, null);
        return gson.fromJson(userAsJson, UserLogged.class);
    }

    public boolean isUserLoggedIn(){
        return pref.getBoolean(IS_USER_LOGIN, false);
    }

    /**
     * Clear session details
     * */
    public void logout() {

        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to MainActivity
        Intent i = new Intent(context, LoginActivity.class);

        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        context.startActivity(i);
    }

}
