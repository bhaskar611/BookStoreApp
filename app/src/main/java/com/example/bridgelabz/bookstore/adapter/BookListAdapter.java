package com.example.bridgelabz.bookstore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.model.Book;

import java.util.ArrayList;

public class BookListAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private ArrayList<Book> booksList = new ArrayList<Book>();

    public BookListAdapter(ArrayList<Book> booksList) {

        this.booksList = booksList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.booklist,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Book book = booksList.get(position);
        holder.bookTitle.setText(book.getBookTitle());
        holder.bookAuthor.setText(book.getBookAuthor());
//        holder.bookImage.setText(book.getBookImage());
        String imageUri =book.getBookImage();
        Glide.with(holder.itemView.getContext())
                .load(imageUri)
                .into(holder.bookImage);

    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }
}
