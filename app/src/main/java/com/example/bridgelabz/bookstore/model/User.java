package com.example.bridgelabz.bookstore.model;

import java.util.ArrayList;

public class User {
    private String userName;
    private String email;
    private String password;
    private ArrayList<Integer> bookID;

    public ArrayList<Integer> getBookID() {
        return bookID;
    }

    public void setBookID(ArrayList<Integer> bookID) {
        this.bookID = bookID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String email,String password,String userName) {
        this.email = email;
        this.password = password;
        this.userName = userName;
    }
    public User(){

    }
}
