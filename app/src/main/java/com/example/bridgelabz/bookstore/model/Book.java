package com.example.bridgelabz.bookstore.model;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private String bookTitle;
    private String bookAuthor;
    private String bookImage;
    private int bookID;
    private float bookPrice;
    private String description;
    private boolean isCarted;
    private boolean isFavourite;
    private List<Review> ReviewList;

    public List<Review> getReviewList() {
        return ReviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        ReviewList = reviewList;
    }

    public boolean isCarted() {
        return isCarted;
    }

    public void setCarted(boolean carted) {
        isCarted = carted;
    }

    public Book(BookResponseModel bookResponseModel) {
        this.bookID = bookResponseModel.getBookID();
        this.bookTitle = bookResponseModel.getBookTitle();
        this.bookAuthor = bookResponseModel.getBookAuthor();
        this.description = bookResponseModel.getDescription();
        this.bookPrice = bookResponseModel.getBookPrice();
        this.bookImage = bookResponseModel.getBookImage();
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }





    public float getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(float bookPrice) {
        this.bookPrice = bookPrice;
    }



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

    public Book(int bookID,String bookTitle,String bookAuthor,String description,String bookImage,float bookPrice,boolean isCarted, boolean isFavourite,List<Review> ReviewList) {

        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.description =description;
        this.bookImage = bookImage;
       this.bookPrice = bookPrice;
       this.isCarted = isCarted;
        this.isFavourite = isFavourite;
        this.ReviewList = ReviewList;

    }

    public Book(){

    }
}
