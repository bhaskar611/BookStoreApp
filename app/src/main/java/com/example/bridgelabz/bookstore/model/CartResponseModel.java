package com.example.bridgelabz.bookstore.model;

public class CartResponseModel {

    private int bookID;
    private int Quantites;

    public CartResponseModel() {

    }

    public CartResponseModel(int bookID,int Quantites) {
        this.bookID = bookID;
        this.Quantites = Quantites;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getQuantites() {
        return Quantites;
    }

    public void setQuantites(int quantites) {
        Quantites = quantites;
    }
}
