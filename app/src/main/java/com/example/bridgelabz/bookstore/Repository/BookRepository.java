package com.example.bridgelabz.bookstore.Repository;

import android.content.Context;
import android.util.Log;


import com.example.bridgelabz.bookstore.SharedPreference;
import com.example.bridgelabz.bookstore.model.Book;
import com.example.bridgelabz.bookstore.model.BookResponseModel;
import com.example.bridgelabz.bookstore.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class BookRepository {

    private Context context;
    private static final String TAG = "BookRepository";
    private SharedPreference sharedPreference;

    public BookRepository(Context context) {
        this.context = context;
        sharedPreference = new SharedPreference(context);
    }

    public String loadBookJSON() {
        String json = null;
        try {
            InputStream is = context.getAssets().open("Books.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        Log.e(TAG, "loadJSONFromAsset: " + json);
        return json;
    }

    public ArrayList<Book> getBookList() {
        ArrayList<Book> bookList = new ArrayList<>();
        String data = loadBookJSON();
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<BookResponseModel> bookResponseModels = null;
        try {
            bookResponseModels = mapper.readValue(data, new TypeReference<List<BookResponseModel>>() {
            });
            User user = getLoggedInUser();
            List<Integer> favoriteBookIds = user.getFavouriteItemsList();
            for (BookResponseModel bookResponseModel : bookResponseModels) {
                Book favouriteBook = new Book(bookResponseModel);
                favouriteBook.setFavourite(favoriteBookIds.contains(bookResponseModel.getBookID()));
                bookList.add(favouriteBook);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public ArrayList<Book> getCartItemList() {
        ArrayList<Book> bookList = new ArrayList<>();
        String data = loadBookJSON();
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<BookResponseModel> bookResponseModels = null;
        try {
            bookResponseModels = mapper.readValue(data, new TypeReference<List<BookResponseModel>>() {
            });
            User user = getLoggedInUser();
            List<Integer> cartBookIds = user.getCartItems();
            for (BookResponseModel bookResponseModel : bookResponseModels) {
                Book cartBook = new Book(bookResponseModel);
                cartBook.setCarted(cartBookIds.contains(bookResponseModel.getBookID()));
                bookList.add(cartBook);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public User getLoggedInUser() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<User> userList = mapper.readValue(new File(context.getFilesDir(),
                "Users.json"), new TypeReference<List<User>>() {
        });
        for (User user : userList) {
            if (user.getUserID() == sharedPreference.getPresentUserId()) {
                return user;
            }
        }
        return null;
    }
    public ArrayList<Book> getFavoriteBooks() {
        ArrayList<Book> favoriteBooks = new ArrayList<>();
        for(Book book : getBookList()){
            if(book.isFavourite()){
                favoriteBooks.add(book);
            }
        }
        return favoriteBooks;
    }
    public ArrayList<Book> getCartItemBooks() {
        ArrayList<Book> cartItemBooks = new ArrayList<>();
        for(Book book : getCartItemList()){
            if(book.isCarted()){
                cartItemBooks.add(book);
            }
        }
        return cartItemBooks;
    }

}
