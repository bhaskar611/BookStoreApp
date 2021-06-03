package com.example.bridgelabz.bookstore.util;

public interface CallBack<T> {
    void onSuccess(T data);
    void onFailure(Exception exception);
    // void onCompletion(T data);

}

