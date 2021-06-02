package com.example.bridgelabz.bookstore.model;

import java.util.List;

public class GetEntries{
    private List<User> entries;

    public List<User> getEntries(){
        return this.entries;
    }

    public void setEntries(List<User> entries){
        this.entries = entries;
    }
}