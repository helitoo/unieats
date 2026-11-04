package com.example.unieats.entities;

public class ReviewPhoto {
    private String photoId;
    private String reviewId;
    private String photoUrl;
    private String createdAt;

    public ReviewPhoto() {}

    public ReviewPhoto(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public ReviewPhoto(String photoId, String reviewId, String photoUrl, String createdAt) {
        this.photoId = photoId;
        this.reviewId = reviewId;
        this.photoUrl = photoUrl;
        this.createdAt = createdAt;
    }

    public String getPhotoId() { return photoId; }
    public void setPhotoId(String photoId) { this.photoId = photoId; }

    public String getReviewId() { return reviewId; }
    public void setReviewId(String reviewId) { this.reviewId = reviewId; }

    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
