package com.example.bridgelabz.bookstore.adapter;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bridgelabz.bookstore.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView bookTitle,bookAuthor;
    ImageView bookImage;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        bookTitle = itemView.findViewById(R.id.bookTitle);
        bookAuthor = itemView.findViewById(R.id.bookAuthor);
        bookImage = itemView.findViewById(R.id.bookImage);

    }
}
