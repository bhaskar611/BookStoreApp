package com.example.bridgelabz.bookstore.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bridgelabz.bookstore.Authentication.LoginActivity;
import com.example.bridgelabz.bookstore.dashBoard.DashBoardActivity;
import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.SharedPreference;

public class SplashActivity extends AppCompatActivity {

    private boolean isLoggedIn;
    SharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPreference = new SharedPreference(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams
                .FLAG_FULLSCREEN);

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