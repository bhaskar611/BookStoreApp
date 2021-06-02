package com.example.bridgelabz.bookstore.model;

public class UserDetails {
    private String userName;
    private String email;
    private String password;

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

    public UserDetails(String email,String password,String userName) {
        this.email = email;
        this.password = password;
        this.userName = userName;
    }
    public UserDetails(){

    }
}
