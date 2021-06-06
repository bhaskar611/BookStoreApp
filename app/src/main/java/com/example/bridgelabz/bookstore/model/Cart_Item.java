package com.example.bridgelabz.bookstore.model;

import android.widget.ImageView;
import android.widget.TextView;

public class Cart_Item {

    private String bookTitle;
    private String bookAuthor;
    private String bookImage;
    private float bookPrice;

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

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Cart_Item(String bookTitle,String bookAuthor,String bookImage,float bookPrice) {

        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookImage = bookImage;
        this.bookPrice = bookPrice;
    }

    public Cart_Item(){

    }
}
