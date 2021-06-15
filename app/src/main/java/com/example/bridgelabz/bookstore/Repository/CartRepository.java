package com.example.bridgelabz.bookstore.Repository;

import android.content.Context;
import android.util.Log;

import com.example.bridgelabz.bookstore.SharedPreference;
import com.example.bridgelabz.bookstore.model.Book;
import com.example.bridgelabz.bookstore.model.BookResponseModel;
import com.example.bridgelabz.bookstore.model.CartModel;
import com.example.bridgelabz.bookstore.model.CartResponseModel;
import com.example.bridgelabz.bookstore.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CartRepository {
    private static final String TAG = "CartRepository";
    private Context context;
    private SharedPreference sharedPreference;
    private BookRepository bookRepository;
    private ReviewRepository reviewRepository;


    public CartRepository(Context context,ReviewRepository reviewRepository) {
        this.context = context;
        sharedPreference = new SharedPreference(context);
        bookRepository = new BookRepository(context, reviewRepository);
    }

    public List<CartModel> getCartList()  {
        List<CartModel> cartList = new ArrayList<>();
        User user = bookRepository.getLoggedInUser();
        List<CartResponseModel> userCartItemList = user.getCartItemList();
        Log.e(TAG, "cartBookIds: " + userCartItemList);

        ArrayList<Book> bookList = bookRepository.getBookList();
        ArrayList<Integer> bookIds = new ArrayList<>();
        for (Book bookModel : bookList) {
            bookIds.add(bookModel.getBookID());
        }

        for (CartResponseModel cartResponseModel : userCartItemList) {
            int bookIndex = bookIds.indexOf(cartResponseModel.getBookID());
            CartModel cartModel = new CartModel(cartResponseModel.getQuantites(), bookList.get(bookIndex));
            cartList.add(cartModel);
        }

        return cartList;
    }

    public ArrayList<Book> getUserCartItemList() {
        ArrayList<Book> bookList = new ArrayList<>();
        String data = bookRepository.loadBookJSON();
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<BookResponseModel> bookResponseModels = null;
        try {
            bookResponseModels = mapper.readValue(data, new TypeReference<List<BookResponseModel>>() {
            });
            User user = bookRepository.getLoggedInUser();
            List<CartResponseModel> cartBookIds = user.getCartItemList();
            Log.e(TAG, "cartBookIds: " + cartBookIds);
            for (BookResponseModel bookResponseModel : bookResponseModels) {
                Book cartBook = new Book(bookResponseModel);
                cartBook.setCarted(cartBookIds.contains(bookResponseModel.getBookID()));
                Log.e(TAG, "cartBook: " + cartBook);
                bookList.add(cartBook);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public ArrayList<Book> getCartItemBooks() {
        ArrayList<Book> cartItemBooks = new ArrayList<>();
        for (Book book : getUserCartItemList()) {
            if (book.isCarted()) {
                cartItemBooks.add(book);
            }
        }
        return cartItemBooks;
    }

    public float calculateTotalPrice(List<CartModel> cartList) {
        float totalPrice = 0.0f;
        for (CartModel cart : cartList) {
            totalPrice += cart.getBook().getBookPrice() * cart.getQuantites();
        }
        return totalPrice;
    }
}
