package com.example.bridgelabz.bookstore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.model.Book;
import com.example.bridgelabz.bookstore.model.Book_Wish_Item;

import java.util.ArrayList;

public class WishListAdapter extends RecyclerView.Adapter<WishList_ViewHolder> {

    private ArrayList<Book> book_wish_items = new ArrayList<>();

    public WishListAdapter(ArrayList<Book> book_wish_items){
        this.book_wish_items = book_wish_items;
    }
    @NonNull
    @Override
    public WishList_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.wishlist_item,parent,false);
        return new WishList_ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull WishList_ViewHolder holder, int position) {
        Book book = book_wish_items.get(position);
        holder.bind(book);
    }


    @Override
    public int getItemCount() {
        return book_wish_items.size();
    }
}
