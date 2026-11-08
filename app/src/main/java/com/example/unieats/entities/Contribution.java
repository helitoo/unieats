package com.example.unieats.entities;

import java.util.List;

public class Contribution {
    private String contributionId;
    private String userId;
    private String restaurantName;
    private String address;
    private Double latitude;
    private Double longitude;
    private List<String> menuImages;
    private String note;
    private String status; // PENDING, APPROVED, REJECTED
    private String createdAt;

    public Contribution() {}

    public Contribution(String contributionId, String userId, String restaurantName, String address, Double latitude, Double longitude, List<String> menuImages, String note, String status, String createdAt) {
        this.contributionId = contributionId;
        this.userId = userId;
        this.restaurantName = restaurantName;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.menuImages = menuImages;
        this.note = note;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getContributionId() { return contributionId; }
    public void setContributionId(String contributionId) { this.contributionId = contributionId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getRestaurantName() { return restaurantName; }
    public void setRestaurantName(String restaurantName) { this.restaurantName = restaurantName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public List<String> getMenuImages() { return menuImages; }
    public void setMenuImages(List<String> menuImages) { this.menuImages = menuImages; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
