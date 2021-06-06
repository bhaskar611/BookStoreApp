package com.example.bridgelabz.bookstore.model;

public class BookResponseModel {

    private String bookTitle;
    private String bookAuthor;
    private String bookImage;
    private int bookID;
    private float bookPrice;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //    private boolean isCarted;
    private boolean isFavourite;

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public float getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(float bookPrice) {
        this.bookPrice = bookPrice;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public BookResponseModel() {

    }


    public BookResponseModel(int bookID, String bookTitle, String bookAuthor, String description,
                             String bookImage, float bookPrice) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.description = description;
        this.bookPrice = bookPrice;
        this.bookImage = bookImage;
    }



    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }



}
