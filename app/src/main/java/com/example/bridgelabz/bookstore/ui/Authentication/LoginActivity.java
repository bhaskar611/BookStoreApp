package com.example.bridgelabz.bookstore.ui.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bridgelabz.bookstore.ui.dashBoard.DashBoardActivity;
import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.SharedPreference;
import com.example.bridgelabz.bookstore.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText emailId, passwordId;
    Button btnSignIn;
    TextView textViewSignUp;
    SharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupClickListeners();
    }



    private void findViews() {
        emailId = findViewById(R.id.et_email);
        passwordId = findViewById(R.id.et_password);
        btnSignIn = findViewById(R.id.btn_login);
        textViewSignUp = findViewById(R.id.signUp);
        sharedPreference = new SharedPreference(this);

    }
    private void setupClickListeners() {
        btnSignIn.setOnClickListener( v -> {
            try {
                signInValidation();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        textViewSignUp.setOnClickListener(v -> {
            Intent intSignUp = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intSignUp);
        });

    }

    private void signInValidation() throws IOException {
        String email = emailId.getText().toString();
        String password = passwordId.getText().toString();
        if(email.isEmpty()){
            emailId.setError("Please enter email id");
            emailId.requestFocus();
        } else if(!email.matches("^[a-zA-Z]+([._+-]{0,1}[a-zA-Z0-9]+)*@[a-zA-Z0-9]+.[a-zA-Z]{2,4}+(?:\\.[a-z]{2,}){0,1}$")) {
            emailId.setError("Please enter valid email id");
            emailId.requestFocus();
        }else  if(password.isEmpty()){
            passwordId.setError("Please enter your password");
            passwordId.requestFocus();
        } else  if(!password.matches("(^(?=.*[A-Z]))(?=.*[0-9])(?=.*[a-z])(?=.*[@*&^%#-*+!]{1}).{8,}$")) {
            passwordId.setError("Please enter Valid password");
            passwordId.requestFocus();
        }
        else  if(!(email.isEmpty() && password.isEmpty())){

            ObjectMapper mapper = new ObjectMapper();
            List<User> userList1 = mapper.readValue(new File(getFilesDir(), "Users.json"),new TypeReference<List<User>>(){} );
            int i;
            boolean isLoggedIN = false;
            for (i=0;i<userList1.size();i++) {
                if(userList1.get(i).getEmail().equals(email) && userList1.get(i).getPassword().equals(password)) {
                    isLoggedIN = true;

                    break;
                }
            }
            if (isLoggedIN){
                Toast.makeText(LoginActivity.this, "Sign In Successfull!", Toast.LENGTH_SHORT).show();
                sharedPreference.setLoggedIN(true);
                sharedPreference.setPresentUserId(i);
                startActivity(new Intent(LoginActivity.this, DashBoardActivity.class));

            }
            else {
                Toast.makeText(LoginActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();
            }

        }
    }
}