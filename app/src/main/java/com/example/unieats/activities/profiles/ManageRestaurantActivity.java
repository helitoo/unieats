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

import java.util.ArrayList;
import java.util.List;

public class ManageRestaurantActivity extends AppCompatActivity {

    private ImageView tvBack;
    private RecyclerView rvManageRestaurants;
    private TextView chipAll, chipPending, chipApproved, chipRejected, chipClosed;

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
    }

    private void setupRestaurantList() {
        Restaurant r1 = new Restaurant("Bún bò Huế An Nhiên", "123 Đường Xô Viết Nghệ Tĩnh, Bình Thạnh", 4.5, 32, 35000, 55000);
        r1.setStatus("PENDING");
        r1.setOpeningTime("06:30");
        r1.setClosingTime("21:30");

        Restaurant r2 = new Restaurant("Cơm tấm Đêm Làng Đại Học", "Khu Phố 6, Linh Trung, Thủ Đức", 4.8, 120, 25000, 45000);
        r2.setStatus("APPROVED");
        r2.setOpeningTime("17:00");
        r2.setClosingTime("02:00");

        Restaurant r3 = new Restaurant("Lẩu Thái Xóm Trọ", "45 Đường Số 8, Linh Trung, Thủ Đức", 4.2, 18, 70000, 150000);
        r3.setStatus("REJECTED");
        r3.setOpeningTime("10:00");
        r3.setClosingTime("22:00");

        Restaurant r4 = new Restaurant("Quán Trà Sữa Học Bài", "88 Võ Văn Ngân, Thủ Đức", 4.6, 95, 25000, 50000);
        r4.setStatus("CLOSED");
        r4.setOpeningTime("08:00");
        r4.setClosingTime("22:30");

        Restaurant r5 = new Restaurant("Gà Rán Chợ Đêm", "Cổng A Làng Đại Học, Thủ Đức", 4.3, 45, 30000, 60000);
        r5.setStatus("PENDING");
        r5.setOpeningTime("15:00");
        r5.setClosingTime("23:00");

        fullList.add(r1);
        fullList.add(r2);
        fullList.add(r3);
        fullList.add(r4);
        fullList.add(r5);

        filteredList.addAll(fullList);
        adapter = new ManageRestaurantAdapter(filteredList);
        rvManageRestaurants.setAdapter(adapter);
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
