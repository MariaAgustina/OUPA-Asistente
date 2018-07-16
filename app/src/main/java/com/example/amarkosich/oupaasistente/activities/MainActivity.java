package com.example.amarkosich.oupaasistente.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amarkosich.oupaasistente.R;
import com.example.amarkosich.oupaasistente.UserSessionManager;
import com.example.amarkosich.oupaasistente.model.UserLogged;
import com.example.amarkosich.oupaasistente.services.UserService;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private UserService userService;
    private UserSessionManager userSessionManager;
    private UserLogged oupaAssisted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userService = new UserService();
        userService.updateDeviceToken(FirebaseInstanceId.getInstance().getToken());

        userSessionManager = new UserSessionManager(this);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Log.i(getClass().getCanonicalName(), "Firebase token: " + FirebaseInstanceId.getInstance().getToken());

        setupUI();

        setupActions();
    }

    private void setupUI() {
        oupaAssisted = userSessionManager.getOupaAssisted();

        if (oupaAssisted == null) {
            startActivity(new Intent(this, OupaSelectorActivity.class));
            finish();

        } else {
            TextView textView = findViewById(R.id.oupa_assisted);
            textView.setText(oupaAssisted.toString());

            TextView email = findViewById(R.id.oupa_assisted_email);
            email.setText(oupaAssisted.email);

            TextView switchOupa = findViewById(R.id.switch_oupa);
            switchOupa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), OupaSelectorActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    private void setupActions() {
        LinearLayout contacts = findViewById(R.id.contacts_action);
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "aun no hago nada :(", Toast.LENGTH_SHORT).show();
            }
        });

        LinearLayout medicine = findViewById(R.id.medicine_action);
        medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "aun no hago nada :(", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        UserLogged user = userSessionManager.getUserLogged();

        menu.getItem(0).setTitle(user.toString());
        menu.getItem(1).setIcon(ContextCompat.getDrawable(this, R.drawable.close));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout) {
            userSessionManager.logout();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
