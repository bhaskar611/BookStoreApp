package com.example.bridgelabz.bookstore.model;



import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;
    private String email;
    private String password;

    public List<Integer> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<Integer> cartItems) {
        this.cartItems = cartItems;
    }

    private List<Integer> favouriteItemsList;
    private int userID;
    private List<Integer> cartItems;
    private List<Address> userAddress;

    public List<Address> getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(List<Address> userAddress) {
        this.userAddress = userAddress;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public List<Integer> getFavouriteItemsList() {
        return favouriteItemsList;
    }

    public void setFavouriteItemsList(List<Integer> favouriteItemsList) {
        this.favouriteItemsList = favouriteItemsList;
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

    public User(int userId,String email,String password,String userName,List<Integer> favouriteItemsList,List<Integer> cartItems,List<Address> userAddress) {
        this.userID = userId;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.favouriteItemsList = favouriteItemsList;
        this.cartItems = cartItems;
        this.userAddress = userAddress;
    }
    public User(){

    }
}
