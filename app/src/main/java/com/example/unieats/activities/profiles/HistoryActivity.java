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
import com.example.unieats.adapters.ReviewShortAdapter;
import com.example.unieats.entities.Restaurant;
import com.example.unieats.entities.ReviewShort;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private ImageView tvBack;
    private TextView btnTabReviews, btnTabContributed;
    private RecyclerView rvHistoryList;

    private ReviewShortAdapter reviewShortAdapter;
    private RestaurantAdapter restaurantAdapter;
    private boolean isReviewTab = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupAdapters();
        setupListeners();
        switchTab(true);
    }

    private void initViews() {
        tvBack = findViewById(R.id.tvBack);
        btnTabReviews = findViewById(R.id.btnTabReviews);
        btnTabContributed = findViewById(R.id.btnTabContributed);
        rvHistoryList = findViewById(R.id.rvHistoryList);

        rvHistoryList.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupAdapters() {
        // Tab 1 Data: ReviewShort list
        List<ReviewShort> reviewShortList = new ArrayList<>();
        reviewShortList.add(new ReviewShort("Cơm nhà Góc Sân Trường", "Khu Phố 6, Linh Trung, Thủ Đức", 5, "Ngon và hợp túi tiền"));
        reviewShortList.add(new ReviewShort("Bún bò Cô Ba", "Làng Đại Học, Thủ Đức", 4, "Nước dùng đậm đà"));
        reviewShortList.add(new ReviewShort("Bún bò Huế An Nhiên", "Làng Đại học, Thủ Đức", 5, "Quán sạch sẽ"));

        reviewShortAdapter = new ReviewShortAdapter(reviewShortList);

        // Tab 2 Data: Restaurant list
        List<Restaurant> contributedList = new ArrayList<>();
        contributedList.add(new Restaurant("Cơm nhà Góc Sân Trường", "Khu Phố 6, Linh Trung, Thủ Đức", 4.8, 128, 25000, 45000, "500 m", "Đã duyệt"));
        contributedList.add(new Restaurant("Quán Lẩu Sinh Viên 99k", "Làng Đại Học, Thủ Đức", 4.6, 42, 99000, 150000, "1.0 km", "Chờ duyệt"));

        restaurantAdapter = new RestaurantAdapter(contributedList);
    }

    private void setupListeners() {
        tvBack.setOnClickListener(v -> finish());

        btnTabReviews.setOnClickListener(v -> switchTab(true));
        btnTabContributed.setOnClickListener(v -> switchTab(false));
    }

    private void switchTab(boolean reviews) {
        isReviewTab = reviews;
        int activeColor = getResources().getColor(R.color.orange_500, getTheme());
        int inactiveColor = getResources().getColor(R.color.neutral_700, getTheme());

        if (isReviewTab) {
            btnTabReviews.setBackgroundResource(R.drawable.bg_chip_selected);
            btnTabReviews.setTextColor(activeColor);

            btnTabContributed.setBackgroundResource(R.drawable.bg_chip_normal);
            btnTabContributed.setTextColor(inactiveColor);

            rvHistoryList.setAdapter(reviewShortAdapter);
        } else {
            btnTabContributed.setBackgroundResource(R.drawable.bg_chip_selected);
            btnTabContributed.setTextColor(activeColor);

            btnTabReviews.setBackgroundResource(R.drawable.bg_chip_normal);
            btnTabReviews.setTextColor(inactiveColor);

            rvHistoryList.setAdapter(restaurantAdapter);
        }
    }
}
