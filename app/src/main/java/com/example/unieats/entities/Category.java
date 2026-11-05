package com.example.unieats.entities;

public class Category {
    private int categoryId;
    private String name;
    private String slug;
    private String iconUrl;
    private int iconResId;
    private String description;

    public Category() {}

    public Category(int categoryId, String name, String slug, String iconUrl, String description) {
        this.categoryId = categoryId;
        this.name = name;
        this.slug = slug;
        this.iconUrl = iconUrl;
        this.description = description;
    }

    public Category(String name, String slug, String iconUrl) {
        this.name = name;
        this.slug = slug;
        this.iconUrl = iconUrl;
    }

    public Category(String name, String slug, int iconResId) {
        this.name = name;
        this.slug = slug;
        this.iconResId = iconResId;
    }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }

    public String getIconUrl() { return iconUrl; }
    public void setIconUrl(String iconUrl) { this.iconUrl = iconUrl; }

    public int getIconResId() { return iconResId; }
    public void setIconResId(int iconResId) { this.iconResId = iconResId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
