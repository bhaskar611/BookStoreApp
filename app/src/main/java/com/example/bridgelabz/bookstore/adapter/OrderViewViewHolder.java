package com.example.bridgelabz.bookstore.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.model.CartModel;
import com.example.bridgelabz.bookstore.model.Order;

public class OrderViewViewHolder extends RecyclerView.ViewHolder {
    TextView bookTitle1,bookAuthor1,bookPrice1,bookrating1,bookmrp1,bookdiscount1;
    ImageView bookImage1;
    CheckBox isFavourite1;
    public OrderViewViewHolder(@NonNull View itemView) {
        super(itemView);
        bookTitle1 = itemView.findViewById(R.id.bookTitle1);
        bookAuthor1 = itemView.findViewById(R.id.bookAuthor1);
        bookImage1 = itemView.findViewById(R.id.bookImage1);
        isFavourite1 = itemView.findViewById(R.id.cartcheckbox1);
        bookPrice1 = itemView.findViewById(R.id.bookPrice1);
        bookrating1 = itemView.findViewById(R.id.textView21);
        bookmrp1 = itemView.findViewById(R.id.textView181);
        bookdiscount1 = itemView.findViewById(R.id.textView191);
    }

    public void bind(CartModel order) {
        bookTitle1.setText(order.getBook().getBookTitle());
        bookAuthor1.setText(order.getBook().getBookAuthor());
        String url = order.getBook().getBookImage();
        Glide.with(itemView.getContext()).load(url).into(bookImage1);
        bookPrice1.setText(String.valueOf(order.getBook().getBookPrice()));
    }
}
