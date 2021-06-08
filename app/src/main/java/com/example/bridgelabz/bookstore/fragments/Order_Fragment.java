package com.example.bridgelabz.bookstore.fragments;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.Repository.CartRepository;
import com.example.bridgelabz.bookstore.adapter.OrderAdapter;
import com.example.bridgelabz.bookstore.model.CartModel;
import com.example.bridgelabz.bookstore.model.Order;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Order_Fragment extends Fragment {

    Order order;
    long orderID;
    CartRepository cartRepository;
    RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    int spanCount;
    Date date;
    Calendar calendar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_, container, false);
        orderID = Order_Placed_Fragment.orderNo;
        calendar.getTime();
        cartRepository = new CartRepository(this.getContext());
        List<CartModel> cartList = cartRepository.getCartList();
        date =calendar.getTime();
        order = new Order(orderID,cartList,date);
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            spanCount = 2;
        } else {
            // In portrait
            spanCount = 1;
        }
        final RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
        recyclerView = view.findViewById(R.id.order_RecyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        orderAdapter = new OrderAdapter(orderList);
        recyclerView.setAdapter(orderAdapter);
        orderAdapter.notifyDataSetChanged();
        onBackPressed(view);
        return view;

    }
    private void onBackPressed(View view) {

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.order_toolbar);
        toolbar.setTitle("Order Fragment");
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //handle any click event
                getParentFragmentManager().popBackStack();

            }
        });


    }

    public void onResume() {
        super.onResume();
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).hide();
    }
    @Override
    public void onStop() {
        super.onStop();
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).show();
    }
}