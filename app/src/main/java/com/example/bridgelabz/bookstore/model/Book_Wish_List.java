package com.example.bridgelabz.bookstore.model;

import java.util.ArrayList;

public class Book_Wish_List {

    private ArrayList<Book_Wish_List> book_wish_lists;

    public ArrayList<Book_Wish_List> getBook_wish_lists() {
        return book_wish_lists;
    }

    public void setBook_wish_lists(ArrayList<Book_Wish_List> book_wish_lists) {
        this.book_wish_lists = book_wish_lists;
    }

    public Book_Wish_List(ArrayList<Book_Wish_List> book_wish_lists){
        this.book_wish_lists= book_wish_lists;
    }
}
