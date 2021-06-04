package com.example.bridgelabz.bookstore.model;

public class Book {
    private String bookTitle;
    private String bookAuthor;
    private String bookImage;
    private int bookID;


    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Book(String bookTitle,String bookAuthor,String bookImage) {

        this.bookTitle =bookTitle;
        this.bookAuthor=bookAuthor;
        this.bookImage=bookImage;
    }

    public Book(){

    }
}
