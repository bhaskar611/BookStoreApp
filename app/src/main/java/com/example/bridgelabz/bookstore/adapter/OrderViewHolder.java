package com.example.bridgelabz.bookstore.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.model.Order;

public class OrderViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView orderID,bookTitle;
    ImageView bookPic;
    TextView date;
    OnOrderListner onOrderListner;
    public OrderViewHolder(@NonNull View itemView,OnOrderListner onOrderListner) {
        super(itemView);
        orderID = itemView.findViewById(R.id.textView21);
        bookPic = itemView.findViewById(R.id.imageView4);
        date = itemView.findViewById(R.id.textView30);
       bookTitle = itemView.findViewById(R.id.textView24);
       this.onOrderListner = onOrderListner;
        itemView.setOnClickListener(this);
    }

    public void bind(Order order) {
            orderID.setText(String.valueOf(order.getOrderID()));
             String imageUri = "";
            for(int i=0; i<order.getCart_items().size(); i++){
                 imageUri =  order.getCart_items().get(i).getBook().getBookImage();
                break;
            }
            date.setText(order.getOrderDate());
//            bookTitle.setText(order.getCart_items().get());
//        for(int i=0; i<order.getCart_items().size(); i++){
//            if (order.getOrderID())
//        }
       bookTitle.setText(String.valueOf(order.getOrderTotal()));
//
        Glide.with(itemView.getContext())
                .load(imageUri)
                .into(bookPic);
    }

    @Override
    public void onClick(View v) {
        onOrderListner.onOrderClick(getBindingAdapterPosition(),v);
    }
}
