package com.example.bridgelabz.bookstore.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bridgelabz.bookstore.R;

public class WishList_ViewHolder extends RecyclerView.ViewHolder {

    TextView bookWishTitle,bookWishAuthor;
    ImageView bookWishImage;


    public WishList_ViewHolder(@NonNull View itemView) {
        super(itemView);
        bookWishTitle = itemView.findViewById(R.id.wishList_bookTitle);
        bookWishAuthor = itemView.findViewById(R.id.wishList_bookAuthor);
        bookWishImage = itemView.findViewById(R.id.wishList_bookImage);

    }

}
