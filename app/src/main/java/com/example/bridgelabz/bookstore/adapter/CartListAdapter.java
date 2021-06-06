package com.example.bridgelabz.bookstore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.model.Book;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartList_ViewHolder> {

    private ArrayList<Book> book_cart_items = new ArrayList<>();

    public CartListAdapter(ArrayList<Book> book_cart_items) {
        this.book_cart_items = book_cart_items;
    }

    @NonNull
    @Override
    public CartList_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.cart_list_item, parent, false);
        return new CartList_ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CartList_ViewHolder holder, int position) {
        Book book = book_cart_items.get(position);
        holder.bind(book);
    }




    @Override
    public int getItemCount() {
        return book_cart_items.size();
    }

}