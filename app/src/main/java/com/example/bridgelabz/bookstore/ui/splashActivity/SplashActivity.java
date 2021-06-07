package com.example.bridgelabz.bookstore.ui.splashActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bridgelabz.bookstore.ui.Authentication.LoginActivity;
import com.example.bridgelabz.bookstore.ui.dashBoard.DashBoardActivity;
import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.SharedPreference;

import java.util.Objects;

public class SplashActivity extends AppCompatActivity {

    private boolean isLoggedIn;
    SharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPreference = new SharedPreference(this);

        getSupportActionBar().hide();


        new Handler().postDelayed(() -> {

            isLoggedIn = sharedPreference.getLoggedIN();
            Intent intent;
            if (isLoggedIn){
                intent = new Intent(SplashActivity.this, DashBoardActivity.class);
            }else{
                intent = new Intent(SplashActivity.this, LoginActivity.class);
            }
            startActivity(intent);
            finish();


        },4000);

    }
}