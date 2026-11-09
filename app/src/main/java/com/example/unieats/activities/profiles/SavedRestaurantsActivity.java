package com.example.unieats.activities.profiles;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import android.widget.Toast;

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
import com.example.unieats.helpers.MockData;

import java.util.List;

public class SavedRestaurantsActivity extends AppCompatActivity {

    private ImageView tvBack;
    private RecyclerView rvSavedRestaurants;
    private RestaurantAdapter adapter;
    private List<Restaurant> savedList;

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

    @Override
    protected void onResume() {
        super.onResume();
        setupSavedList();
    }

    private void initViews() {
        tvBack = findViewById(R.id.tvBack);
        rvSavedRestaurants = findViewById(R.id.rvSavedRestaurants);

        rvSavedRestaurants.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupSavedList() {
        savedList = MockData.getSavedRestaurants();

        adapter = new RestaurantAdapter(savedList, (restaurant, position) -> {
            MockData.toggleSaveRestaurant(restaurant);
            if (position >= 0 && position < savedList.size()) {
                adapter.notifyItemRemoved(position);
                adapter.notifyItemRangeChanged(position, savedList.size());
            } else {
                adapter.notifyDataSetChanged();
            }
            Toast.makeText(SavedRestaurantsActivity.this, String.format("Đã hủy lưu \"%s\"", restaurant.getName()), Toast.LENGTH_SHORT).show();
        });
        rvSavedRestaurants.setAdapter(adapter);
    }

    private void setupListeners() {
        tvBack.setOnClickListener(v -> finish());
    }
}
