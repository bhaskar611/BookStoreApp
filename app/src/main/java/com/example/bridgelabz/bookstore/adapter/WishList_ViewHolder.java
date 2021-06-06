package com.example.bridgelabz.bookstore.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.model.Book;

public class WishList_ViewHolder extends RecyclerView.ViewHolder {

    TextView bookWishTitle,bookWishAuthor,bookWishPrice;
    ImageView bookWishImage;


    public WishList_ViewHolder(@NonNull View itemView) {
        super(itemView);
        bookWishTitle = itemView.findViewById(R.id.WishList_bookTitle);
        bookWishAuthor = itemView.findViewById(R.id.WishList_bookAuthor);
        bookWishImage = itemView.findViewById(R.id.WishList_bookImage);
        bookWishPrice =itemView.findViewById(R.id.WishList_bookPrice);

    }
    public void bind(Book book) {
        bookWishTitle.setText(book.getBookTitle());
        bookWishAuthor.setText(book.getBookAuthor());
        bookWishPrice.setText(String.valueOf(book.getBookPrice()));
        String imageUri = book.getBookImage();
        Glide.with(itemView.getContext())
                .load(imageUri)
                .into(bookWishImage);
    }

}
