package com.example.unieats.activities.restaurants;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unieats.R;
import com.example.unieats.adapters.RestaurantAdapter;
import com.example.unieats.entities.Restaurant;
import com.example.unieats.helpers.FooterHelper;

import java.util.ArrayList;
import java.util.List;

public class HotRestaurantsActivity extends AppCompatActivity {

    private TextView tvBack, tvHeaderSearchIcon;
    private RecyclerView rvHotRestaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hot_restaurants);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupFooter();
        setupHotRestaurants();
        setupListeners();
    }

    private void initViews() {
        tvBack = findViewById(R.id.tvBack);
        tvHeaderSearchIcon = findViewById(R.id.tvHeaderSearchIcon);
        rvHotRestaurants = findViewById(R.id.rvHotRestaurants);

        rvHotRestaurants.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupFooter() {
        FooterHelper.setupFooter(this, FooterHelper.Tab.TRENDING);
    }

    private void setupHotRestaurants() {
        List<Restaurant> hotList = new ArrayList<>();
        hotList.add(new Restaurant("Cơm nhà Góc Sân Trường", "Khu Phố 6, Linh Trung, Thủ Đức", 4.8, 128, 25000, 45000, "500 m", "Giá sinh viên"));
        hotList.add(new Restaurant("Bún bò Cô Ba", "Làng Đại Học, Thủ Đức", 4.7, 96, 30000, 45000, "800 m", "Được yêu thích"));
        hotList.add(new Restaurant("Cơm nhà Góc Sân Trường", "Khu Phố 6, Linh Trung, Thủ Đức", 4.8, 128, 25000, 45000, "500 m", "Giá sinh viên"));
        hotList.add(new Restaurant("Trà sữa Gong Cha", "Đường Võ Văn Ngân, Thủ Đức", 4.5, 210, 35000, 60000, "1.2 km", "Giảm 20%"));

        RestaurantAdapter adapter = new RestaurantAdapter(hotList);
        rvHotRestaurants.setAdapter(adapter);
    }

    private void setupListeners() {
        tvBack.setOnClickListener(v -> finish());

        tvHeaderSearchIcon.setOnClickListener(v -> {
            Intent intent = new Intent(HotRestaurantsActivity.this, SearchActivity.class);
            startActivity(intent);
        });
    }
}
