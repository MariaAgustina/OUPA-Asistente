package com.example.amarkosich.oupaasistente.activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.amarkosich.oupaasistente.recyclerview.OupaAdapter;
import com.example.amarkosich.oupaasistente.R;
import com.example.amarkosich.oupaasistente.UserSessionManager;
import com.example.amarkosich.oupaasistente.model.UserLogged;
import com.example.amarkosich.oupaasistente.services.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.amarkosich.oupaasistente.App.getContext;

public class OupaSelectorActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OupaAdapter oupaAdapter;
    private List<UserLogged> oupas = new ArrayList<>();
    private UserService userService;
    private UserSessionManager userSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oupa_selector);

        getSupportActionBar().hide();

        userSessionManager = new UserSessionManager(getContext());
        userService = new UserService();

        setupUI();

        initRecycler();
    }

    private void setupUI() {
        //if the assistant is a doctor, will set other styles.
        if (userSessionManager.isDoctor()) {
            final int sdk = android.os.Build.VERSION.SDK_INT;
            if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                findViewById(R.id.oupa_selector).setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.estetocopio));
            } else {
                findViewById(R.id.oupa_selector).setBackground(ContextCompat.getDrawable(getContext(), R.drawable.estetocopio));
                findViewById(R.id.oupa_selector).setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.blue)));
            }

            findViewById(R.id.title_assisted).setBackgroundColor(getResources().getColor(R.color.blue));
        }

        findViewById(R.id.oupa_selector).getBackground().setAlpha(80);
    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.my_recycler_view);
        oupaAdapter = new OupaAdapter(oupas, new OupaAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(UserLogged oupaAssited) {
                userSessionManager.saveOupaAssisted(oupaAssited);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }

        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.HORIZONTAL));

        recyclerView.setAdapter(oupaAdapter);

        populateOupaSelector();
    }

    private void populateOupaSelector() {

        userService.getOupas(userSessionManager.getAuthorizationToken()).enqueue(new Callback<List<UserLogged>>() {
            @Override
            public void onResponse(Call<List<UserLogged>> oupaList, Response<List<UserLogged>> response) {
                oupas.addAll(response.body());
                oupaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<UserLogged>> call, Throwable t) {

            }
        });

        oupaAdapter.notifyDataSetChanged();
    }


}
