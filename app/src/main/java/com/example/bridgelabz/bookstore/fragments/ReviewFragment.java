package com.example.bridgelabz.bookstore.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.bridgelabz.bookstore.R;


public class ReviewFragment extends Fragment {

    EditText userReviewDetails;
    RatingBar ratingBar;
    Button submitReview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        userReviewDetails = view.findViewById(R.id.userReview);
        ratingBar = view.findViewById(R.id.user_Review_ratingBar);
        submitReview = view.findViewById(R.id.user_Review_Submit);

        return view;
    }
}