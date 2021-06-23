package com.example.bridgelabz.bookstore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.model.Book;
import com.example.bridgelabz.bookstore.model.CartModel;
import com.example.bridgelabz.bookstore.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderViewAdapter extends RecyclerView.Adapter<OrderViewViewHolder> {

    private ArrayList<CartModel> orderList = new ArrayList<>();

    public OrderViewAdapter(ArrayList<CartModel> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.order_view_item,parent,false);
        return new OrderViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewViewHolder holder, int position) {

        CartModel order = orderList.get(position);
        holder.bind(order);

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
