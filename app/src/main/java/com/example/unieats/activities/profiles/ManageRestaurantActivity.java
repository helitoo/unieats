package com.example.unieats.activities.profiles;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unieats.R;
import com.example.unieats.adapters.ManageRestaurantAdapter;
import com.example.unieats.entities.Restaurant;
import com.example.unieats.helpers.MockData;

import java.util.ArrayList;
import java.util.List;

public class ManageRestaurantActivity extends AppCompatActivity {

    private ImageView tvBack;
    private RecyclerView rvManageRestaurants;
    private TextView chipAll, chipPending, chipApproved, chipRejected, chipClosed;
    private TextView tvTotalRestaurantsCount, tvPendingCount, tvApprovedCount, tvRejectedCount;

    private final List<Restaurant> fullList = new ArrayList<>();
    private final List<Restaurant> filteredList = new ArrayList<>();
    private ManageRestaurantAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manage_restaurant);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupRestaurantList();
        setupListeners();
    }

    private void initViews() {
        tvBack = findViewById(R.id.tvBack);
        rvManageRestaurants = findViewById(R.id.rvManageRestaurants);
        rvManageRestaurants.setLayoutManager(new LinearLayoutManager(this));

        chipAll = findViewById(R.id.chipAll);
        chipPending = findViewById(R.id.chipPending);
        chipApproved = findViewById(R.id.chipApproved);
        chipRejected = findViewById(R.id.chipRejected);
        chipClosed = findViewById(R.id.chipClosed);

        tvTotalRestaurantsCount = findViewById(R.id.tvTotalRestaurantsCount);
        tvPendingCount = findViewById(R.id.tvPendingCount);
        tvApprovedCount = findViewById(R.id.tvApprovedCount);
        tvRejectedCount = findViewById(R.id.tvRejectedCount);
    }

    private void setupRestaurantList() {
        fullList.clear();
        fullList.addAll(MockData.getRestaurants());

        filteredList.clear();
        filteredList.addAll(fullList);
        adapter = new ManageRestaurantAdapter(filteredList, this::updateStatistics);
        rvManageRestaurants.setAdapter(adapter);

        updateStatistics();
    }

    private void updateStatistics() {
        int total = fullList.size();
        int pending = 0;
        int approved = 0;
        int rejected = 0;

        for (Restaurant r : fullList) {
            String status = r.getStatus() != null ? r.getStatus().toUpperCase() : "";
            switch (status) {
                case "PENDING":
                    pending++;
                    break;
                case "APPROVED":
                    approved++;
                    break;
                case "REJECTED":
                    rejected++;
                    break;
            }
        }

        if (tvTotalRestaurantsCount != null) tvTotalRestaurantsCount.setText(String.valueOf(total));
        if (tvPendingCount != null) tvPendingCount.setText(String.valueOf(pending));
        if (tvApprovedCount != null) tvApprovedCount.setText(String.valueOf(approved));
        if (tvRejectedCount != null) tvRejectedCount.setText(String.valueOf(rejected));
    }

    private void setupListeners() {
        tvBack.setOnClickListener(v -> finish());

        chipAll.setOnClickListener(v -> filterByStatus(null, chipAll));
        chipPending.setOnClickListener(v -> filterByStatus("PENDING", chipPending));
        chipApproved.setOnClickListener(v -> filterByStatus("APPROVED", chipApproved));
        chipRejected.setOnClickListener(v -> filterByStatus("REJECTED", chipRejected));
        chipClosed.setOnClickListener(v -> filterByStatus("CLOSED", chipClosed));
    }

    private void filterByStatus(String status, TextView selectedChip) {
        resetChipStyles();
        selectedChip.setBackgroundResource(R.drawable.bg_chip_selected);
        selectedChip.setTextColor(ContextCompat.getColor(this, R.color.neutral_0));

        filteredList.clear();
        if (status == null) {
            filteredList.addAll(fullList);
        } else {
            for (Restaurant r : fullList) {
                if (status.equalsIgnoreCase(r.getStatus())) {
                    filteredList.add(r);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void resetChipStyles() {
        TextView[] chips = new TextView[]{chipAll, chipPending, chipApproved, chipRejected, chipClosed};
        for (TextView chip : chips) {
            chip.setBackgroundResource(R.drawable.bg_chip_normal);
            chip.setTextColor(ContextCompat.getColor(this, R.color.neutral_700));
        }
    }
}
