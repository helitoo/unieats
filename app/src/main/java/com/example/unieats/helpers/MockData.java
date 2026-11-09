package com.example.unieats.helpers;

import com.example.unieats.entities.Category;
import com.example.unieats.entities.Menu;
import com.example.unieats.entities.Restaurant;
import com.example.unieats.entities.Review;
import com.example.unieats.entities.ReviewPhoto;
import com.example.unieats.entities.ReviewShort;
import com.example.unieats.entities.User;

import java.util.ArrayList;
import java.util.List;

public class MockData {

    // Central Data Collections
    private static User currentUser;
    private static final List<User> usersList = new ArrayList<>();
    private static final List<Restaurant> allRestaurants = new ArrayList<>();
    private static final List<Restaurant> myRestaurantsList = new ArrayList<>();
    private static final List<Restaurant> savedRestaurantsList = new ArrayList<>();
    private static final List<Restaurant> contributedRestaurantsList = new ArrayList<>();
    private static final List<Menu> menuList = new ArrayList<>();
    private static final List<Review> reviewList = new ArrayList<>();
    private static final List<ReviewShort> reviewShortList = new ArrayList<>();
    private static final List<Category> categoryList = new ArrayList<>();

    static {
        initMockData();
    }

    private static synchronized void initMockData() {
        if (!usersList.isEmpty()) return; // Already initialized

        // Current User
        currentUser = new User("100", "Phương", "phuong@example.com", "0901234567", "ADMIN", "Đang hoạt động", null);

        // Users
        usersList.add(new User("1", "Nguyễn Minh Anh", "minhanh@gmail.com", "0901234567", "USER", "Đang hoạt động", null));
        usersList.add(new User("2", "Trần Thị Bình", "thibinh@gmail.com", "0912345678", "MODERATOR", "Đang hoạt động", null));
        usersList.add(new User("3", "Lê Hoài Cường", "hoaicuong@gmail.com", "0987654321", "USER", "Tạm dừng", null));
        usersList.add(new User("4", "Phạm Hoàng Dung", "hoangdung@gmail.com", "0971122334", "ADMIN", "Đang hoạt động", null));
        usersList.add(new User("5", "Đỗ Quốc Emp", "quocemp@gmail.com", "0934455667", "USER", "Đang hoạt động", null));

        // Main Restaurants
        Restaurant r1 = new Restaurant("Cơm nhà Góc Sân Trường", "Khu Phố 6, Linh Trung, Thủ Đức", 4.8, 128, 25000, 45000, "500 m", "Giá sinh viên");
        r1.setStatus("APPROVED");
        r1.setOpeningTime("06:30");
        r1.setClosingTime("21:30");

        Restaurant r2 = new Restaurant("Bún bò Cô Ba", "Làng Đại Học, Thủ Đức", 4.7, 96, 30000, 45000, "800 m", "Được yêu thích");
        r2.setStatus("APPROVED");
        r2.setOpeningTime("07:00");
        r2.setClosingTime("21:00");

        Restaurant r3 = new Restaurant("Trà sữa Gong Cha", "Đường Võ Văn Ngân, Thủ Đức", 4.5, 210, 35000, 60000, "1.2 km", "Giảm 20%");
        r3.setStatus("APPROVED");
        r3.setOpeningTime("08:00");
        r3.setClosingTime("22:30");

        Restaurant r4 = new Restaurant("Bún đậu Mắm Tôm Phố Cổ", "Đường Đặng Văn Bi, Thủ Đức", 4.6, 85, 35000, 70000, "1.5 km", "Món Bắc");
        r4.setStatus("PENDING");
        r4.setOpeningTime("10:00");
        r4.setClosingTime("22:00");

        Restaurant r5 = new Restaurant("Cơm Tấm Ba Ghiền", "Xa Lộ Hà Nội, Thủ Đức", 4.9, 340, 40000, 75000, "2.0 km", "Nổi tiếng");
        r5.setStatus("APPROVED");
        r5.setOpeningTime("06:00");
        r5.setClosingTime("22:00");

        Restaurant r6 = new Restaurant("Lẩu Thái Xóm Trọ", "45 Đường Số 8, Linh Trung, Thủ Đức", 4.2, 18, 70000, 150000, "2.3 km", "Lẩu nướng");
        r6.setStatus("REJECTED");
        r6.setOpeningTime("10:00");
        r6.setClosingTime("22:00");

        Restaurant r7 = new Restaurant("Gà Rán Chợ Đêm", "Cổng A Làng Đại Học, Thủ Đức", 4.3, 45, 30000, 60000, "600 m", "Ăn vặt");
        r7.setStatus("PENDING");
        r7.setOpeningTime("15:00");
        r7.setClosingTime("23:00");

        allRestaurants.add(r1);
        allRestaurants.add(r2);
        allRestaurants.add(r3);
        allRestaurants.add(r4);
        allRestaurants.add(r5);
        allRestaurants.add(r6);
        allRestaurants.add(r7);

        // My Restaurants (Owner's)
        myRestaurantsList.add(r1);
        myRestaurantsList.add(r2);
        myRestaurantsList.add(r3);

        // Saved Restaurants
        savedRestaurantsList.add(r1);
        savedRestaurantsList.add(r2);
        savedRestaurantsList.add(r3);

        // Contributed Restaurants
        contributedRestaurantsList.add(new Restaurant("Cơm nhà Góc Sân Trường", "Khu Phố 6, Linh Trung, Thủ Đức", 4.8, 128, 25000, 45000, "500 m", "Giá sinh viên"));
        contributedRestaurantsList.add(new Restaurant("Quán Lẩu Sinh Viên 99k", "Làng Đại Học, Thủ Đức", 4.6, 42, 99000, 199000, "1.1 km", "Món lẩu"));

        // Menus
        menuList.add(new Menu("Cơm Tấm Sườn", "Sườn nướng, mỡ hành thơm lừng", 35000));
        menuList.add(new Menu("Cơm Gà Xối Mỡ", "Da giòn, cơm vàng thơm ngon", 40000));
        menuList.add(new Menu("Cơm Sườn Bì Chả", "Sườn nướng, bì, chả trứng hấp", 45000));
        menuList.add(new Menu("Bún bò đặc biệt", "Nước dùng đậm đà, thịt bò tái nạm", 45000));

        // Reviews
        List<ReviewPhoto> photos1 = new ArrayList<>();
        photos1.add(new ReviewPhoto("photo_1"));
        photos1.add(new ReviewPhoto("photo_2"));

        Review rev1 = new Review("Phương (Tôi)", "Quán phục vụ rất nhanh, cơm tấm sườn nướng mỡ hành thơm phức, chuẩn vị Sài Gòn!", 5, true, photos1);
        Review rev2 = new Review("Lê Thu Hà", "Món ăn ngon hợp vị sinh viên, giá rẻ và chất lượng tuyệt vời.", 5, false, new ArrayList<>());
        Review rev3 = new Review("Người dùng · 1", "Không gian quán sạch sẽ, thái độ nhân viên nhiệt tình.", 4, false, new ArrayList<>());

        reviewList.add(rev1);
        reviewList.add(rev2);
        reviewList.add(rev3);

        // Review Shorts for History
        reviewShortList.add(new ReviewShort("Cơm nhà Góc Sân Trường", "Khu Phố 6, Linh Trung, Thủ Đức", 5, "Cơm tấm ngon tuyệt vời!"));
        reviewShortList.add(new ReviewShort("Bún bò Cô Ba", "Làng Đại Học, Thủ Đức", 4, "Nước dùng đậm đà hợp khẩu vị."));
        reviewShortList.add(new ReviewShort("Bún bò Huế An Nhiên", "Làng Đại học, Thủ Đức", 5, "Quán sạch sẽ, phục vụ chu đáo."));
    }

    // --- USER CRUD ---
    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static List<User> getUsers() {
        return usersList;
    }

    public static void addUser(User user) {
        if (user != null) {
            usersList.add(0, user);
        }
    }

    public static void updateUser(User user) {
        if (user == null || user.getUserId() == null) return;
        for (int i = 0; i < usersList.size(); i++) {
            if (user.getUserId().equals(usersList.get(i).getUserId())) {
                usersList.set(i, user);
                break;
            }
        }
    }

    public static void deleteUser(User user) {
        if (user != null) {
            usersList.remove(user);
        }
    }

    // --- RESTAURANT CRUD ---
    public static List<Restaurant> getRestaurants() {
        return allRestaurants;
    }

    public static List<Restaurant> getNearbyRestaurants() {
        List<Restaurant> list = new ArrayList<>();
        if (allRestaurants.size() >= 2) {
            list.add(allRestaurants.get(0));
            list.add(allRestaurants.get(1));
        } else {
            list.addAll(allRestaurants);
        }
        return list;
    }

    public static List<Restaurant> getHotRestaurants() {
        List<Restaurant> list = new ArrayList<>();
        if (allRestaurants.size() >= 3) {
            list.add(allRestaurants.get(1));
            list.add(allRestaurants.get(4));
            list.add(allRestaurants.get(2));
        } else {
            list.addAll(allRestaurants);
        }
        return list;
    }

    public static List<Restaurant> getMyRestaurants() {
        return myRestaurantsList;
    }

    public static void addMyRestaurant(Restaurant restaurant) {
        if (restaurant != null) {
            myRestaurantsList.add(0, restaurant);
            allRestaurants.add(0, restaurant);
        }
    }

    public static void deleteMyRestaurant(Restaurant restaurant) {
        if (restaurant != null) {
            myRestaurantsList.remove(restaurant);
            allRestaurants.remove(restaurant);
            savedRestaurantsList.remove(restaurant);
        }
    }

    public static List<Restaurant> getSavedRestaurants() {
        return savedRestaurantsList;
    }

    public static void toggleSaveRestaurant(Restaurant restaurant) {
        if (restaurant == null) return;
        if (savedRestaurantsList.contains(restaurant)) {
            savedRestaurantsList.remove(restaurant);
        } else {
            savedRestaurantsList.add(restaurant);
        }
    }

    public static boolean isRestaurantSaved(Restaurant restaurant) {
        return restaurant != null && savedRestaurantsList.contains(restaurant);
    }

    public static List<Restaurant> getContributedRestaurants() {
        return contributedRestaurantsList;
    }

    public static void addContributedRestaurant(Restaurant restaurant) {
        if (restaurant != null) {
            contributedRestaurantsList.add(0, restaurant);
            allRestaurants.add(0, restaurant);
        }
    }

    public static void addRestaurant(Restaurant restaurant) {
        if (restaurant != null) {
            allRestaurants.add(0, restaurant);
        }
    }

    public static void deleteRestaurant(Restaurant restaurant) {
        if (restaurant != null) {
            allRestaurants.remove(restaurant);
            myRestaurantsList.remove(restaurant);
            savedRestaurantsList.remove(restaurant);
        }
    }

    // --- MENU CRUD ---
    public static List<Menu> getMenus() {
        return menuList;
    }

    public static void addMenu(Menu menu) {
        if (menu != null) {
            menuList.add(0, menu);
        }
    }

    public static void deleteMenu(Menu menu) {
        if (menu != null) {
            menuList.remove(menu);
        }
    }

    // --- REVIEW CRUD ---
    public static List<Review> getReviews() {
        return reviewList;
    }

    public static List<ReviewShort> getReviewShorts() {
        return reviewShortList;
    }

    public static void addReview(Review review) {
        if (review != null) {
            reviewList.add(0, review);
            ReviewShort shortRev = new ReviewShort("Cơm nhà Góc Sân Trường", "Linh Trung, Thủ Đức", review.getRating(), review.getComment());
            reviewShortList.add(0, shortRev);
        }
    }

    public static void deleteReview(Review review) {
        if (review != null) {
            reviewList.remove(review);
        }
    }

    // --- CATEGORIES ---
    public static List<Category> getCategories() {
        return categoryList;
    }
}
