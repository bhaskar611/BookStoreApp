package com.example.bridgelabz.bookstore.adapter;

import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.model.Address;
import com.example.bridgelabz.bookstore.model.Review;
import com.example.bridgelabz.bookstore.model.ReviewModel;

import java.util.Locale;

public class ReviewViewHolder extends RecyclerView.ViewHolder {
    TextView reviewuser,reviewdetails;
    RatingBar reviewrating;
    public ReviewViewHolder(@NonNull View itemView) {
        super(itemView);
        reviewuser = itemView.findViewById(R.id.review_user);
        reviewdetails = itemView.findViewById(R.id.review_detail);
        reviewrating = itemView.findViewById(R.id.review_user_rating);
    }
    public void bind(ReviewModel review){
        reviewuser.setText(review.getUserName());
        reviewdetails.setText(review.getReview());
        reviewrating.setRating(review.getRating());
    }
}
