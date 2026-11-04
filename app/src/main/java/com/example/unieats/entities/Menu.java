package com.example.unieats.entities;

public class Menu {
    private String menuId;
    private String restaurantId;
    private String name;
    private String description;
    private double price;
    private String imageUrl;
    private boolean isAvailable;
    private Integer categoryId;
    private String createdAt;

    public Menu() {}

    public Menu(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.isAvailable = true;
    }

    public Menu(String menuId, String restaurantId, String name, String description, double price, String imageUrl, boolean isAvailable, Integer categoryId, String createdAt) {
        this.menuId = menuId;
        this.restaurantId = restaurantId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.isAvailable = isAvailable;
        this.categoryId = categoryId;
        this.createdAt = createdAt;
    }

    public String getMenuId() { return menuId; }
    public void setMenuId(String menuId) { this.menuId = menuId; }

    public String getRestaurantId() { return restaurantId; }
    public void setRestaurantId(String restaurantId) { this.restaurantId = restaurantId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public Integer getCategoryId() { return categoryId; }
    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
