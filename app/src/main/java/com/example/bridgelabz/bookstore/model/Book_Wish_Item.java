package com.example.bridgelabz.bookstore.model;

import java.util.ArrayList;

public class Book_Wish_Item {
    private String bookWishAuthor;
    private String bookWishTitle;
    private String bookWishImage;



    public String getBookWishAuthor() {
        return bookWishAuthor;
    }



    public void setBookWishAuthor(String bookWishAuthor) {
        this.bookWishAuthor = bookWishAuthor;
    }

    public String getBookWishTitle() {
        return bookWishTitle;
    }

    public void setBookWishTitle(String bookWishTitle) {
        this.bookWishTitle = bookWishTitle;
    }

    public String getBookWishImage() {
        return bookWishImage;
    }

    public void setBookWishImage(String bookWishImage) {
        this.bookWishImage = bookWishImage;
    }

    public Book_Wish_Item(String bookWishTitle,String bookWishAuthor,String bookWishImage) {
        this.bookWishTitle = bookWishTitle;
        this.bookWishAuthor = bookWishAuthor;
        this.bookWishImage = bookWishImage;
    }


    public Book_Wish_Item(){

    }
}
