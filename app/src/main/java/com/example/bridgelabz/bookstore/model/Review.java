package com.example.bridgelabz.bookstore.model;

public class Review {
    private String userName;
    private String userReview;
    private float userRating;

    public Review(String userName, String userReview, float userRating) {
        this.userName = userName;
        this.userReview = userReview;
        this.userRating = userRating;
    }

    public Review() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserReview() {
        return userReview;
    }

    public void setUserReview(String userReview) {
        this.userReview = userReview;
    }

    public float getUserRating() {
        return userRating;
    }

    public void setUserRating(float userRating) {
        this.userRating = userRating;
    }
}
