package com.example.unieats.entities;

public class User {
    private String userId;
    private String name;
    private String email;
    private String phone;
    private String role;
    private String status;
    private String avatarUrl;
    private boolean active = true;
    private boolean expanded = false;

    public User() {}

    public User(String name, String role, String status) {
        this.name = name;
        this.role = role;
        this.status = status;
        this.active = !"Tạm dừng".equals(status);
    }

    public User(String name, String email, String phone, String role) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.status = "Đang hoạt động";
        this.active = true;
    }

    public User(String userId, String name, String email, String phone, String role, String status, String avatarUrl) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.status = status;
        this.avatarUrl = avatarUrl;
        this.active = !"Tạm dừng".equals(status);
    }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAvatarUrl() { return avatarUrl; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) {
        this.active = active;
        this.status = active ? "Đang hoạt động" : "Tạm dừng";
    }

    public boolean isExpanded() { return expanded; }
    public void setExpanded(boolean expanded) { this.expanded = expanded; }
}
