package com.example.bridgelabz.bookstore.model;



import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;
    private String email;
    private String password;
    private String userImage;

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    private List<Order> orderList;


    public List<CartResponseModel> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartResponseModel> cartItemList) {
        this.cartItemList = cartItemList;
    }

    private List<Integer> favouriteItemsList;
    private int userID;
    private List<CartResponseModel> cartItemList;
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

    public User(int userId,String email,String password,String userName,List<Integer> favouriteItemsList,List<CartResponseModel> cartItemList,List<Address> userAddress,List<Order> orderList,String userImage) {
        this.userID = userId;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.favouriteItemsList = favouriteItemsList;
        this.cartItemList = cartItemList;
        this.userAddress = userAddress;
        this.orderList = orderList;
        this.userImage = userImage;
    }
    public User(){

    }
}
