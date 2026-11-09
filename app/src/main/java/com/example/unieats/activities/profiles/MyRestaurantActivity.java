package com.example.unieats.activities.profiles;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unieats.R;
import com.example.unieats.adapters.MyRestaurantAdapter;
import com.example.unieats.entities.Restaurant;
import com.example.unieats.helpers.MockData;

import java.util.List;

public class MyRestaurantActivity extends AppCompatActivity implements MyRestaurantAdapter.OnRestaurantActionListener {

    private ImageView tvBack;
    private RecyclerView rvMyRestaurants;
    private List<Restaurant> myRestaurantList;
    private MyRestaurantAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_restaurants);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupList();
        setupListeners();
    }

    private void initViews() {
        tvBack = findViewById(R.id.tvBack);
        rvMyRestaurants = findViewById(R.id.rvMyRestaurants);
        rvMyRestaurants.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupList() {
        myRestaurantList = MockData.getMyRestaurants();

        adapter = new MyRestaurantAdapter(myRestaurantList, this);
        rvMyRestaurants.setAdapter(adapter);
    }

    private void setupListeners() {
        tvBack.setOnClickListener(v -> finish());
    }

    @Override
    public void onDelete(Restaurant restaurant, int position) {
        showDeleteConfirmationDialog(restaurant, position);
    }

    private void showDeleteConfirmationDialog(Restaurant restaurant, int position) {
        new AlertDialog.Builder(this)
                .setTitle("Xác nhận xóa")
                .setMessage(String.format("Bạn có chắc chắn muốn xóa \"%s\" không?", restaurant.getName()))
                .setPositiveButton("Xóa", (dialog, which) -> {
                    MockData.deleteMyRestaurant(restaurant);
                    adapter.notifyItemRemoved(position);
                    adapter.notifyItemRangeChanged(position, myRestaurantList.size());
                    Toast.makeText(this, "Đã xóa quán ăn", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
