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
    private List<Review> reviewList;
    private float bookMRP;
    private float discount;
    private float rating;

    public Book(int bookID,String bookTitle,String bookAuthor,String description,String bookImage,float bookPrice,boolean isCarted, boolean isFavourite,List<Review> reviewList,float bookMRP,float discount,float rating) {

        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.description =description;
        this.bookImage = bookImage;
        this.bookPrice = bookPrice;
        this.isCarted = isCarted;
        this.isFavourite = isFavourite;
        this.reviewList = reviewList;
        this.bookMRP = bookMRP;
        this.discount = discount;
        this.rating = rating;

    }
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
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
        this.reviewList = bookResponseModel.getReviewList();
        this.bookMRP = bookResponseModel.getBookMRP();
        this.discount = bookResponseModel.getDiscount();
        this.rating = bookResponseModel.getRating();
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



    public Book(){

    }
}
