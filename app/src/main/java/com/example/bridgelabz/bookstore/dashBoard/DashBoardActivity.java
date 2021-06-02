package com.example.bridgelabz.bookstore.dashBoard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.SharedPreference;

public class DashBoardActivity extends AppCompatActivity {
    SharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        sharedPreference = new SharedPreference(this);
        sharedPreference.setLoggedIN(false);
    }
}