package com.example.bridgelabz.bookstore;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.bridgelabz.bookstore.model.Book;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class SharedPreference  {

    private Context context;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String IS_LOGGED_IN = "Logged_In";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static final String FAVORITES = "Product_Favorite";


    public SharedPreference(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);

    }
    public void setLoggedIN(boolean value){
        editor  = sharedPreferences.edit();
        editor.putBoolean(IS_LOGGED_IN,value);
        editor.apply();
    }
    public boolean getLoggedIN(){
        return sharedPreferences.getBoolean(IS_LOGGED_IN,false);

    }

    public void setBookTitle(String value){
        editor = sharedPreferences.edit();
        editor.putString("bookTitle",value);
        editor.apply();
    }
    public void setBookAuthor(String value){
        editor = sharedPreferences.edit();
        editor.putString("bookAuthor",value);
        editor.apply();
    }
    public void setBookImage(String value){
        editor = sharedPreferences.edit();
        editor.putString("bookImage",value);
        editor.apply();
    }

    public String getBookTitle(){
        return sharedPreferences.getString("bookTitle",null);
    }
    public String getBookAuthor(){
        return sharedPreferences.getString("bookAuthor",null);
    }
    public String getBookImage(){
        return sharedPreferences.getString("bookImage",null);
    }
}
