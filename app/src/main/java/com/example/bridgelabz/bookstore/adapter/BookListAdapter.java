package com.example.bridgelabz.bookstore.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.SharedPreference;
import com.example.bridgelabz.bookstore.fragments.WishListFragment;
import com.example.bridgelabz.bookstore.model.Book;
import com.example.bridgelabz.bookstore.model.Book_Wish_Item;
import com.example.bridgelabz.bookstore.model.Book_Wish_List;
import com.example.bridgelabz.bookstore.model.User;
import com.example.bridgelabz.bookstore.util.CallBack;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<MyViewHolder> {

    SharedPreference sharedPreference;
    private static final String TAG = "BookListAdapter";
    private ArrayList<Book> booksList = new ArrayList<Book>();
    private Context context;
    Book_Wish_Item book_wish_item = new Book_Wish_Item();
    private ArrayList<Book_Wish_Item> book_wish_items = new ArrayList<Book_Wish_Item>();
    private OnBookListener onBookListener;
    private  OnFavoriteChangeListener onFavoriteChangeListener;


    WishListFragment wishListFragment = new WishListFragment();

    public BookListAdapter(ArrayList<Book> booksList,OnBookListener onBookListener,OnFavoriteChangeListener onFavoriteChangeListener) {

        this.booksList = booksList;
//        this.context = context;
        this.onBookListener = onBookListener;
        this.onFavoriteChangeListener = onFavoriteChangeListener;
//        ,Context context,OnBookListener onBookListener

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.booklist,parent,false);
        return new MyViewHolder(view,onBookListener,onFavoriteChangeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Book book = booksList.get(position);
        holder.bind(book);
        holder.isFavourite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                holder.favouriteChanged(book,isChecked,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }
    public Book getItem(int position) {
        try{
            return booksList.get(position);
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return null;
    }

    public void removeAt(Book book, int position) {
        booksList.remove(book);
        notifyItemRemoved(position);
    }

}