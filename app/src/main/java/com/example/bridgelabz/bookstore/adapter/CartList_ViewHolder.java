package com.example.bridgelabz.bookstore.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.model.Book;

public class CartList_ViewHolder extends RecyclerView.ViewHolder {
    TextView bookCartTitle, bookCartAuthor, bookCartPrice;
    ImageView bookCartImage;


    public CartList_ViewHolder(@NonNull View itemView) {
        super(itemView);
        bookCartTitle = itemView.findViewById(R.id.CartList_bookTitle);
        bookCartAuthor = itemView.findViewById(R.id.CartList_bookAuthor);
        bookCartImage = itemView.findViewById(R.id.CartList_bookImage);
        bookCartPrice =itemView.findViewById(R.id.CartList_bookPrice);

    }
    public void bind(Book book) {
        bookCartTitle.setText(book.getBookTitle());
        bookCartAuthor.setText(book.getBookAuthor());
        bookCartPrice.setText(String.valueOf(book.getBookPrice()));
        String imageUri = book.getBookImage();
        Glide.with(itemView.getContext())
                .load(imageUri)
                .into(bookCartImage);
    }
}
