package com.example.unieats.activities.profiles;

import android.os.Bundle;
import android.widget.ImageView;
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

import java.util.ArrayList;
import java.util.List;

public class SavedRestaurantsActivity extends AppCompatActivity {

    private ImageView tvBack;
    private RecyclerView rvSavedRestaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_saved_restaurants);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupSavedList();
        setupListeners();
    }

    private void initViews() {
        tvBack = findViewById(R.id.tvBack);
        rvSavedRestaurants = findViewById(R.id.rvSavedRestaurants);

        rvSavedRestaurants.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupSavedList() {
        List<Restaurant> savedList = new ArrayList<>();
        savedList.add(new Restaurant("Cơm nhà Góc Sân Trường", "Khu Phố 6, Linh Trung, Thủ Đức", 4.8, 128, 25000, 45000, "500 m", "Giá sinh viên"));
        savedList.add(new Restaurant("Bún bò Cô Ba", "Làng Đại Học, Thủ Đức", 4.7, 96, 30000, 45000, "800 m", "Được yêu thích"));
        savedList.add(new Restaurant("Trà sữa Gong Cha", "Đường Võ Văn Ngân, Thủ Đức", 4.5, 210, 35000, 60000, "1.2 km", "Giảm 20%"));

        RestaurantAdapter adapter = new RestaurantAdapter(savedList);
        rvSavedRestaurants.setAdapter(adapter);
    }

    private void setupListeners() {
        tvBack.setOnClickListener(v -> finish());
    }
}
