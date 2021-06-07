package com.example.bridgelabz.bookstore.model;

public class Cart_Model {

    private int Quantites;
    private Book book;


    public Cart_Model(int Quantites, Book book) {
        this.Quantites = Quantites;
        this.book = book;
    }

    public Cart_Model(){

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
