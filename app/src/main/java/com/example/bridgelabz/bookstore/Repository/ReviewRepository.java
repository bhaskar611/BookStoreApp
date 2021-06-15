package com.example.bridgelabz.bookstore.Repository;

import com.example.bridgelabz.bookstore.model.Review;
import com.example.bridgelabz.bookstore.model.ReviewModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {
    private File file;

    public ReviewRepository(File file) {
        this.file = file;
    }

    public float getAverageRating(int bookId) {
        float averageRating = 0.0f;
        int totalReviews = 0;
        float rating = 0.0f;
        ObjectMapper mapper = new ObjectMapper();
        List<ReviewModel> reviewsList = new ArrayList<>();
        List<ReviewModel> bookReviews = new ArrayList<>();
        try {
            reviewsList = mapper.readValue(file, new TypeReference<List<ReviewModel>>(){} );
            for (int i = 0; i < reviewsList.size(); i++) {
                if (reviewsList.get(i).getBookID() == bookId){
                     rating = rating + reviewsList.get(i).getRating();
                    totalReviews++;

                }
            }
            averageRating = rating /totalReviews;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return averageRating;
    }
}
