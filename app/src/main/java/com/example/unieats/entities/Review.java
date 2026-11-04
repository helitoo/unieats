package com.example.unieats.entities;

import java.util.ArrayList;
import java.util.List;

public class Review {
    private String reviewId;
    private String userId;
    private String restaurantId;
    private int rating;
    private String comment;
    private int likeCount;
    private String createdAt;
    private String updatedAt;

    // Photos list
    private List<ReviewPhoto> photos = new ArrayList<>();

    // UI Display attributes
    private String userName;
    private String reason;
    private boolean isAuthor;

    public Review() {}

    public Review(String userName, String comment, String reason) {
        this.userName = userName;
        this.comment = comment;
        this.reason = reason;
    }

    public Review(String userName, String comment, int rating, List<ReviewPhoto> photos) {
        this.userName = userName;
        this.comment = comment;
        this.rating = rating;
        this.photos = photos;
    }

    public Review(String userName, String comment, int rating, boolean isAuthor, List<ReviewPhoto> photos) {
        this.userName = userName;
        this.comment = comment;
        this.rating = rating;
        this.isAuthor = isAuthor;
        this.photos = photos;
    }

    public Review(String reviewId, String userId, String restaurantId, int rating, String comment, int likeCount, String createdAt, String updatedAt, String userName, String reason, List<ReviewPhoto> photos) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.rating = rating;
        this.comment = comment;
        this.likeCount = likeCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userName = userName;
        this.reason = reason;
        this.photos = photos != null ? photos : new ArrayList<>();
    }

    public String getReviewId() { return reviewId; }
    public void setReviewId(String reviewId) { this.reviewId = reviewId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getRestaurantId() { return restaurantId; }
    public void setRestaurantId(String restaurantId) { this.restaurantId = restaurantId; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public int getLikeCount() { return likeCount; }
    public void setLikeCount(int likeCount) { this.likeCount = likeCount; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }

    public List<ReviewPhoto> getPhotos() { return photos; }
    public void setPhotos(List<ReviewPhoto> photos) { this.photos = photos; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public boolean isAuthor() { return isAuthor; }
    public void setAuthor(boolean author) { isAuthor = author; }
}
