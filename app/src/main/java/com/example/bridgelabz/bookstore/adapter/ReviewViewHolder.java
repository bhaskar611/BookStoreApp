package com.example.bridgelabz.bookstore.adapter;

import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.model.Address;
import com.example.bridgelabz.bookstore.model.Review;

public class ReviewViewHolder extends RecyclerView.ViewHolder {
    TextView reviewuser,reviewdetails;
    RatingBar reviewrating;
    public ReviewViewHolder(@NonNull View itemView) {
        super(itemView);
        reviewuser = itemView.findViewById(R.id.review_user);
        reviewdetails = itemView.findViewById(R.id.review_detail);
        reviewrating = itemView.findViewById(R.id.review_user_rating);
    }
    public void bind(Review review){
        reviewuser.setText(review.getAuthor());
        reviewdetails.setText(review.getReview());
        reviewrating.setRating(review.getRating());
    }
}
