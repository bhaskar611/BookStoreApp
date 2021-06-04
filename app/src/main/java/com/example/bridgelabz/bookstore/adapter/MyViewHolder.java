package com.example.bridgelabz.bookstore.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.model.Book;

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView bookTitle,bookAuthor;
    ImageView bookImage;
    CheckBox bookWishItem;
    OnBookListener onBookListener;

    public MyViewHolder(@NonNull View itemView,OnBookListener onBookListener) {
        super(itemView);
        bookTitle = itemView.findViewById(R.id.bookTitle);
        bookAuthor = itemView.findViewById(R.id.bookAuthor);
        bookImage = itemView.findViewById(R.id.bookImage);
        bookWishItem = itemView.findViewById(R.id.checkbox);
        this.onBookListener = onBookListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onBookListener.onBookClick(getBindingAdapterPosition(),v);
    }
}
