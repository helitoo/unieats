package com.example.unieats.entities;

public class Promotion {
    private String promotionId;
    private String restaurantId;
    private String title;
    private String description;
    private Integer discountPercent;
    private double discountAmount;
    private String startDate;
    private String endDate;
    private boolean isActive;
    private String createdAt;

    public Promotion() {}

    public Promotion(String title, String description, Integer discountPercent, String startDate, String endDate) {
        this.title = title;
        this.description = description;
        this.discountPercent = discountPercent;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = true;
    }

    public Promotion(String promotionId, String restaurantId, String title, String description, Integer discountPercent, double discountAmount, String startDate, String endDate, boolean isActive, String createdAt) {
        this.promotionId = promotionId;
        this.restaurantId = restaurantId;
        this.title = title;
        this.description = description;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }

    public String getPromotionId() { return promotionId; }
    public void setPromotionId(String promotionId) { this.promotionId = promotionId; }

    public String getRestaurantId() { return restaurantId; }
    public void setRestaurantId(String restaurantId) { this.restaurantId = restaurantId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getDiscountPercent() { return discountPercent; }
    public void setDiscountPercent(Integer discountPercent) { this.discountPercent = discountPercent; }

    public double getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(double discountAmount) { this.discountAmount = discountAmount; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
