package com.example.bridgelabz.bookstore.adapter;

import com.example.bridgelabz.bookstore.model.CartModel;

public interface CartBookClickListener {
    void onAddItemQuantity(CartModel cart);
    void onMinusItemQuantity(CartModel cart, int position);
}
