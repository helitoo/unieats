package com.example.unieats.entities;

public class RestaurantShort {
    private String restaurantId;
    private String name;
    private String address;

    public RestaurantShort() {}

    public RestaurantShort(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public RestaurantShort(String restaurantId, String name, String address) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.address = address;
    }

    public String getRestaurantId() { return restaurantId; }
    public void setRestaurantId(String restaurantId) { this.restaurantId = restaurantId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
