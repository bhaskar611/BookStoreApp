package com.example.bridgelabz.bookstore.model;

public class CartModel {

    private int Quantites;
    private Book book;


    public CartModel(int Quantites, Book book) {
        this.Quantites = Quantites;
        this.book = book;
    }

    public CartModel(){

    }

    public int getQuantites() {
        return Quantites;
    }

    public void setQuantites(int quantites) {
        Quantites = quantites;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
