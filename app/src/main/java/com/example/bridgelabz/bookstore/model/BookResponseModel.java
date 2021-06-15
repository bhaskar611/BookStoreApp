package com.example.bridgelabz.bookstore.model;

import java.util.List;

public class BookResponseModel {

    private String bookTitle;
    private String bookAuthor;
    private String bookImage;
    private int bookID;
    private float bookPrice;
    private String description;
    private List<Review> reviewList;
    private float bookMRP;
    private float discount;
//    private float rating;

    public float getBookMRP() {
        return bookMRP;
    }

    public void setBookMRP(float bookMRP) {
        this.bookMRP = bookMRP;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

//    public float getRating() {
//        return rating;
//    }
//
//    public void setRating(float rating) {
//        this.rating = rating;
//    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

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
                             String bookImage, float bookPrice,List<Review> reviewList,float bookMRP,float discount/*,float rating*/) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.description = description;
        this.bookPrice = bookPrice;
        this.bookImage = bookImage;
        this.reviewList = reviewList;
        this.bookMRP = bookMRP;
        this.discount = discount;
        //this.rating = rating;
    }



    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }



}
