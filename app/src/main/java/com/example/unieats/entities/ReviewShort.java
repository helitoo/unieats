package com.example.unieats.entities;

public class ReviewShort {
    private String reviewId;
    private String restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    private int rating;
    private String comment;
    private String createdAt;

    public ReviewShort() {}

    public ReviewShort(String restaurantName, String restaurantAddress) {
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
    }

    public ReviewShort(String restaurantName, String restaurantAddress, int rating, String comment) {
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.rating = rating;
        this.comment = comment;
    }

    public String getReviewId() { return reviewId; }
    public void setReviewId(String reviewId) { this.reviewId = reviewId; }

    public String getRestaurantId() { return restaurantId; }
    public void setRestaurantId(String restaurantId) { this.restaurantId = restaurantId; }

    public String getRestaurantName() { return restaurantName; }
    public void setRestaurantName(String restaurantName) { this.restaurantName = restaurantName; }

    public String getRestaurantAddress() { return restaurantAddress; }
    public void setRestaurantAddress(String restaurantAddress) { this.restaurantAddress = restaurantAddress; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
