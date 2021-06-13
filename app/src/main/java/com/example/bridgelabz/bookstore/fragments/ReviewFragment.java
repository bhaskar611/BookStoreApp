package com.example.bridgelabz.bookstore.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.SharedPreference;
import com.example.bridgelabz.bookstore.model.Address;
import com.example.bridgelabz.bookstore.model.CartResponseModel;
import com.example.bridgelabz.bookstore.model.Order;
import com.example.bridgelabz.bookstore.model.ReviewModel;
import com.example.bridgelabz.bookstore.model.User;
import com.example.bridgelabz.bookstore.ui.Authentication.LoginActivity;
import com.example.bridgelabz.bookstore.ui.Authentication.RegisterActivity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class ReviewFragment extends Fragment {

    private static final String TAG = "ReviewFragment";
    EditText userReviewDetails;
    RatingBar ratingBar;
    Button submitReview;
    SharedPreference sharedPreference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        userReviewDetails = view.findViewById(R.id.userReview);
        ratingBar = view.findViewById(R.id.user_Review_ratingBar);
        submitReview = view.findViewById(R.id.user_Review_Submit);
        sharedPreference = new SharedPreference(this.getContext());
        submitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getArguments() != null;
                int BookId = getArguments().getInt("BookReviewID");

                String userName = null;
                String jsonStr = null;
                ObjectMapper mapper = new ObjectMapper();
                try {
                    List<User> userList1 = mapper.readValue(new File(getContext().getFilesDir(),
                            "Users.json"), new TypeReference<List<User>>() {
                    });
                    userName = userList1.get(sharedPreference.getPresentUserId()).getUserName();
                } catch (IOException jsonParseException) {
                    jsonParseException.printStackTrace();
                }

                try {
                    String userReview = userReviewDetails.getText().toString();
                    float userRating = ratingBar.getRating();
                    long reviewID = System.currentTimeMillis();
                    File file = new File(getContext().getFilesDir(), "reviews.json");
                    List<ReviewModel> reviewList = new ArrayList<ReviewModel>();
                    int userID = sharedPreference.getPresentUserId();
                    ReviewModel review = new ReviewModel(userName, userID, reviewID, BookId, userRating, userReview);
                    reviewList.add(review);
                    ArrayList<ReviewModel> userList1 = mapper.readValue(new File(getContext().getFilesDir(), "reviews.json"), new TypeReference<List<ReviewModel>>() {
                    });

                    List<ReviewModel> joined = new ArrayList<ReviewModel>();

                    joined.addAll(reviewList);
                    joined.addAll(userList1);

                    jsonStr = mapper.writeValueAsString(joined);

                    FileOutputStream fos = getContext().openFileOutput("reviews.json", Context.MODE_PRIVATE);
                    fos.write(jsonStr.getBytes());
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Fragment fragment = new bookListFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        return view;
    }

}