package com.example.unieats.entities;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String restaurantId;
    private String name;
    private String slug;
    private String address;
    private double priceRangeMin;
    private double priceRangeMax;
    private String openingTime;
    private String closingTime;
    private double ratingAvg;
    private int reviewCount;
    private String coverImageUrl;
    private String status;
    private String createdBy;
    private String createdAt;
    private String updatedAt;

    // List of attached photos (menu photos, space photos)
    private List<String> attachedPhotos = new ArrayList<>();

    // UI Helpers
    private String distance;
    private String tag;

    public Restaurant() {}

    public Restaurant(String name, String address, double ratingAvg, int reviewCount, double priceRangeMin, double priceRangeMax) {
        this.name = name;
        this.address = address;
        this.ratingAvg = ratingAvg;
        this.reviewCount = reviewCount;
        this.priceRangeMin = priceRangeMin;
        this.priceRangeMax = priceRangeMax;
    }

    public Restaurant(String name, String address, double ratingAvg, int reviewCount, double priceRangeMin, double priceRangeMax, String distance, String tag) {
        this.name = name;
        this.address = address;
        this.ratingAvg = ratingAvg;
        this.reviewCount = reviewCount;
        this.priceRangeMin = priceRangeMin;
        this.priceRangeMax = priceRangeMax;
        this.distance = distance;
        this.tag = tag;
    }

    public String getRestaurantId() { return restaurantId; }
    public void setRestaurantId(String restaurantId) { this.restaurantId = restaurantId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public double getPriceRangeMin() { return priceRangeMin; }
    public void setPriceRangeMin(double priceRangeMin) { this.priceRangeMin = priceRangeMin; }

    public double getPriceRangeMax() { return priceRangeMax; }
    public void setPriceRangeMax(double priceRangeMax) { this.priceRangeMax = priceRangeMax; }

    public String getOpeningTime() { return openingTime; }
    public void setOpeningTime(String openingTime) { this.openingTime = openingTime; }

    public String getClosingTime() { return closingTime; }
    public void setClosingTime(String closingTime) { this.closingTime = closingTime; }

    public double getRatingAvg() { return ratingAvg; }
    public void setRatingAvg(double ratingAvg) { this.ratingAvg = ratingAvg; }

    public int getReviewCount() { return reviewCount; }
    public void setReviewCount(int reviewCount) { this.reviewCount = reviewCount; }

    public String getCoverImageUrl() { return coverImageUrl; }
    public void setCoverImageUrl(String coverImageUrl) { this.coverImageUrl = coverImageUrl; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }

    public String getDistance() { return distance; }
    public void setDistance(String distance) { this.distance = distance; }

    public String getTag() { return tag; }
    public void setTag(String tag) { this.tag = tag; }

    public List<String> getAttachedPhotos() {
        if (attachedPhotos == null) {
            attachedPhotos = new ArrayList<>();
        }
        return attachedPhotos;
    }

    public void setAttachedPhotos(List<String> attachedPhotos) {
        this.attachedPhotos = attachedPhotos;
    }

    private boolean expanded = false;
    public boolean isExpanded() { return expanded; }
    public void setExpanded(boolean expanded) { this.expanded = expanded; }
}
