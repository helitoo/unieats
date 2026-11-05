package com.example.unieats.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unieats.R;
import com.example.unieats.activities.auth.SigninActivity;
import com.example.unieats.activities.restaurants.ContributeRestaurantActivity;
import com.example.unieats.activities.restaurants.HotRestaurantsActivity;
import com.example.unieats.activities.restaurants.SearchActivity;
import com.example.unieats.adapters.CategoryAdapter;
import com.example.unieats.adapters.PromotionAdapter;
import com.example.unieats.adapters.RestaurantAdapter;
import com.example.unieats.entities.Category;
import com.example.unieats.entities.Promotion;
import com.example.unieats.entities.Restaurant;
import com.example.unieats.helpers.AuthManager;
import com.example.unieats.helpers.FooterHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private View llSearchBar, llHotSectionHeader;
    private TextView tvAddRestaurant;
    private RecyclerView rvCategories;
    private RecyclerView rvNearbyRestaurants;
    private RecyclerView rvHotRestaurants;
    private RecyclerView rvPromotions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check authentication state on launch
        if (!AuthManager.isLoggedIn(this)) {
            Intent intent = new Intent(this, SigninActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupFooter();
        setupCategories();
        setupNearbyRestaurants();
        setupHotRestaurants();
        setupPromotions();
        setupListeners();
    }

    private void initViews() {
        tvAddRestaurant = findViewById(R.id.tvAddRestaurant);
        llSearchBar = findViewById(R.id.llSearchBar);
        llHotSectionHeader = findViewById(R.id.llHotSectionHeader);
        rvCategories = findViewById(R.id.rvCategories);
        rvNearbyRestaurants = findViewById(R.id.rvNearbyRestaurants);
        rvHotRestaurants = findViewById(R.id.rvHotRestaurants);
        rvPromotions = findViewById(R.id.rvPromotions);
    }

    private void setupFooter() {
        FooterHelper.setupFooter(this, FooterHelper.Tab.HOME);
    }

    private void setupCategories() {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("Cơm", "com", R.drawable.ic_category_rice));
        categoryList.add(new Category("Bún/Phở/Mì", "bun-pho-mi", R.drawable.ic_category_noodle));
        categoryList.add(new Category("Ăn vặt", "an-vat", R.drawable.ic_category_snack));
        categoryList.add(new Category("Trà sữa/Cà phê", "tra-sua-ca-phe", R.drawable.ic_category_coffee));
        categoryList.add(new Category("Lẩu & Nướng", "lau-nuong", R.drawable.ic_category_bbq));

        rvCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList);
        rvCategories.setAdapter(categoryAdapter);
    }

    private void setupNearbyRestaurants() {
        List<Restaurant> nearbyList = new ArrayList<>();
        nearbyList.add(new Restaurant("Cơm nhà Góc Sân Trường", "Khu Phố 6, Linh Trung, Thủ Đức", 4.8, 128, 25000, 45000, "500 m", "Giá sinh viên"));
        nearbyList.add(new Restaurant("Bún bò Cô Ba", "Làng Đại Học, Thủ Đức", 4.7, 96, 30000, 45000, "800 m", "Được yêu thích"));

        rvNearbyRestaurants.setLayoutManager(new LinearLayoutManager(this));
        RestaurantAdapter adapter = new RestaurantAdapter(nearbyList);
        rvNearbyRestaurants.setAdapter(adapter);
    }

    private void setupHotRestaurants() {
        List<Restaurant> hotList = new ArrayList<>();
        hotList.add(new Restaurant("Bún bò Cô Ba", "Làng Đại Học, Thủ Đức", 4.7, 96, 30000, 45000, "800 m", "Được yêu thích"));
        hotList.add(new Restaurant("Cơm Tấm Ba Ghiền", "Xa Lộ Hà Nội, Thủ Đức", 4.9, 340, 40000, 75000, "2.0 km", "Quán HOT"));

        rvHotRestaurants.setLayoutManager(new LinearLayoutManager(this));
        RestaurantAdapter adapter = new RestaurantAdapter(hotList);
        rvHotRestaurants.setAdapter(adapter);
    }

    private void setupPromotions() {
        List<Promotion> promoList = new ArrayList<>();
        promoList.add(new Promotion("Giảm 20% Combo Sinh Viên", "Áp dụng cho tất cả quán cơm tại Làng Đại học", 20, "01/10/2023", "31/10/2023"));
        promoList.add(new Promotion("Freeship Đơn Từ 50k", "Mã giảm tối đa 15.000đ phí vận chuyển", 0, "05/10/2023", "20/10/2023"));

        rvPromotions.setLayoutManager(new LinearLayoutManager(this));
        PromotionAdapter adapter = new PromotionAdapter(promoList);
        rvPromotions.setAdapter(adapter);
    }

    private void setupListeners() {
        if (tvAddRestaurant != null) {
            tvAddRestaurant.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, ContributeRestaurantActivity.class);
                startActivity(intent);
            });
        }

        llSearchBar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
        });

        View.OnClickListener openHotRestaurants = v -> {
            Intent intent = new Intent(MainActivity.this, HotRestaurantsActivity.class);
            startActivity(intent);
        };

        if (llHotSectionHeader != null) {
            llHotSectionHeader.setOnClickListener(openHotRestaurants);
        }
    }
}
