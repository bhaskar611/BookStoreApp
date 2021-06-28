package com.example.bridgelabz.bookstore.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.SharedPreference;
import com.example.bridgelabz.bookstore.model.ReviewModel;
import com.example.bridgelabz.bookstore.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ReviewFragment extends Fragment {

    private static final String TAG = "ReviewFragment";
    EditText userReviewDetails;
    RatingBar ratingBar;
    Button submitReview;
    SharedPreference sharedPreference;
    int BookId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
             BookId = getArguments().getInt("BookReviewID");
        }
//        assert getArguments() != null;

        userReviewDetails = view.findViewById(R.id.userReview);
        ratingBar = view.findViewById(R.id.user_Review_ratingBar);
        submitReview = view.findViewById(R.id.user_Review_Submit);
        sharedPreference = new SharedPreference(this.getContext());
        submitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

                getParentFragmentManager().popBackStack();

//                Fragment fragment = new BookListFragment();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_container, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
            }
        });

        onBackPressed(view);
        return view;
    }
    private void onBackPressed(View view) {

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar3);
        toolbar.setTitle("Review");
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //handle any click event
                getParentFragmentManager().popBackStack();

            }
        });


    }

    public void onResume() {
        super.onResume();
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).hide();
    }
    @Override
    public void onStop() {
        super.onStop();
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).show();
    }

}