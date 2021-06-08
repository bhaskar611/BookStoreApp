package com.example.bridgelabz.bookstore.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.Repository.CartRepository;
import com.example.bridgelabz.bookstore.model.CartModel;
import com.example.bridgelabz.bookstore.model.Order;

import java.util.List;

public class Order_Fragment extends Fragment {

    Order order;
    long orderID;
    CartRepository cartRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_, container, false);
        orderID = Order_Placed_Fragment.orderNo;
        cartRepository = new CartRepository(this.getContext());
        List<CartModel> cartList = cartRepository.getCartList();
        order = new Order(orderID,cartList);

        return view;

    }

}