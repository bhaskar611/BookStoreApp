package com.example.bridgelabz.bookstore.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailId, passwordId;
    private Button btnSignUp;
    private TextView nameId,textViewSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViews();
        setupClickListeners();
    }

    private void findViews() {
        emailId = findViewById(R.id.et_email);
        passwordId = findViewById(R.id.et_password);
        btnSignUp = findViewById(R.id.btn_register);
        textViewSignIn = findViewById(R.id.sigin);
        nameId = findViewById(R.id.et_name);
    }

    private void setupClickListeners() {

        btnSignUp.setOnClickListener( v -> registerUser());
        textViewSignIn.setOnClickListener(v -> {
            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(i);
        });


    }

    private boolean isValidName(String name){

        if(name.isEmpty()) {
            nameId.setError("Please enter name id");
            nameId.requestFocus();
            return false;
        } else if(name.matches("[0-9*$%#&^()@!_+{}';]*")) {
            nameId.setError("Please enter proper name id");
            nameId.requestFocus();
            return false;

        }else{
            return true;
        }
    }

    private boolean isValidEmail(String email){

        if(email.isEmpty()) {
            emailId.setError("Please enter email id");
            emailId.requestFocus();
            return false;
        } else if(!email.matches("^[a-zA-Z]+([._+-]{0,1}[a-zA-Z0-9]+)*@[a-zA-Z0-9]+.[a-zA-Z]{2,4}+(?:\\.[a-z]{2,}){0,1}$")) {
            emailId.setError("Please enter valid email id");
            emailId.requestFocus();
            return false;
        }else{
            return true;
        }
    }

    private boolean isValidPassword(String password){

        if(password.isEmpty()) {
            passwordId.setError("Please enter your password");
            passwordId.requestFocus();
            return false;
        } else  if(!password.matches("(^(?=.*[A-Z]))(?=.*[0-9])(?=.*[a-z])(?=.*[@*&^%#-*+!]{1}).{8,}$")) {
            passwordId.setError("Please enter Valid password");
            passwordId.requestFocus();
            return false;
        }else{
            return true;
        }
    }



    private void registerUser() {
        String email = emailId.getText().toString();
        String password = passwordId.getText().toString();
        String name = nameId.getText().toString();
        String jsonStr;

        if (!isValidName(name)) {
            return;
        }
        else if (!isValidEmail(email)) {
            return;
        }else if (!isValidPassword(password)) {
            return;
        }  else  if(email.isEmpty() && password.isEmpty()) {
            Toast.makeText(RegisterActivity.this,"Fields Are Empty!",
                    Toast.LENGTH_SHORT).show();
        } else  if(!(email.isEmpty() && password.isEmpty())){
            try{

//                Map<> hashMap=new HashMap<>();
//
//                hashMap.put("User_Name",name);
//                hashMap.put("Email",email);
//                hashMap.put("Password",password);
//                String jsonString = hashMap.toString();
//                jsonStr = mapper.writeValueAsString(userList);
                File file = new File(getFilesDir(), "Users.json");
                if (file.exists()){
                    ObjectMapper mapper = new ObjectMapper();
                    List<User> userList = new ArrayList<User>();
                    User user = new User(email,password,name);
                    userList.add(user);
               List<User>  userList1 = mapper.readValue(new File(getFilesDir(), "Users.json"),new TypeReference<List<User>>(){} );

                    List<User> joined = new ArrayList<User>();

                    joined.addAll(userList);
                    joined.addAll(userList1);

               jsonStr = mapper.writeValueAsString(joined);

                    FileOutputStream fos = this.openFileOutput("Users.json", Context.MODE_PRIVATE);
                    fos.write(jsonStr.getBytes());
                    fos.close();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }
                else {
                    ObjectMapper mapper = new ObjectMapper();
                    List<User> userList = new ArrayList<User>();
                    User user = new User(email,password,name);
                    userList.add(user);
                    jsonStr = mapper.writeValueAsString(userList);
                    FileOutputStream fos = this.openFileOutput("Users.json", Context.MODE_PRIVATE);
                    fos.write(jsonStr.getBytes());
                    fos.close();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }
//                mapper.writeValue(new File(RegisterActivity.this.getFilesDir().getAbsolutePath()+"/User.json"), hashMap);  // write to file



            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}