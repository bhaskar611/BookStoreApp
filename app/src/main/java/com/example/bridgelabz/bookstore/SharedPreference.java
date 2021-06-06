package com.example.bridgelabz.bookstore;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.bridgelabz.bookstore.model.Book;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class SharedPreference  {

    private Context context;
    private static int presentUserId;
    private static int registeredUsersCount;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String IS_LOGGED_IN = "Logged_In";
    public static final String LOGGED_IN_USER_ID_KEY = "User_ID";
    public static final String REGISTERED_USERS = "User_Register"  ;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static final String FAVORITES = "Product_Favorite";


    public SharedPreference(Context context) {
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

    public int getPresentUserId() {
        return sharedPreferences.getInt(LOGGED_IN_USER_ID_KEY,-1);
    }

    public void setPresentUserId(int presentUserId) {
        editor  = sharedPreferences.edit();
        editor.putInt(LOGGED_IN_USER_ID_KEY, presentUserId);
        editor.apply();
    }

    public int getRegisteredUsersCount() {
        return sharedPreferences.getInt(REGISTERED_USERS,-1);
    }

    public void setRegisteredUsersCount(int registeredUsersCount) {
        editor  = sharedPreferences.edit();
        editor.putInt(REGISTERED_USERS, registeredUsersCount);
        editor.apply();    }

//    public void setBookTitle(String value){
//        editor = sharedPreferences.edit();
//        editor.putString("bookTitle",value);
//        editor.apply();
//    }
//    public void setBookAuthor(String value){
//        editor = sharedPreferences.edit();
//        editor.putString("bookAuthor",value);
//        editor.apply();
//    }
//    public void setBookImage(String value){
//        editor = sharedPreferences.edit();
//        editor.putString("bookImage",value);
//        editor.apply();
//    }
//
//    public String getBookTitle(){
//        return sharedPreferences.getString("bookTitle",null);
//    }
//    public String getBookAuthor(){
//        return sharedPreferences.getString("bookAuthor",null);
//    }
//    public String getBookImage(){
//        return sharedPreferences.getString("bookImage",null);
//    }
}
