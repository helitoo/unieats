package com.example.unieats.activities.restaurants;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
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
import com.example.unieats.adapters.ReviewAdapter;
import com.example.unieats.entities.Review;
import com.example.unieats.helpers.MockData;

import java.util.List;

public class ReviewActivity extends AppCompatActivity {

    private ImageView tvBack;
    private TextView tvRestaurantName;
    private Button btnWriteReview;
    private RecyclerView rvReviews;
    private String restaurantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_review);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        readIntentData();
        setupListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupReviews();
    }

    private void initViews() {
        tvBack = findViewById(R.id.tvBack);
        tvRestaurantName = findViewById(R.id.tvRestaurantName);
        btnWriteReview = findViewById(R.id.btnWriteReview);
        rvReviews = findViewById(R.id.rvReviews);

        rvReviews.setLayoutManager(new LinearLayoutManager(this));
    }

    private void readIntentData() {
        restaurantName = getIntent().getStringExtra("EXTRA_RESTAURANT_NAME");
        if (restaurantName == null || restaurantName.isEmpty()) {
            restaurantName = "Cơm Tấm Nhà Mình";
        }
        tvRestaurantName.setText(restaurantName);
    }

    private void setupReviews() {
        List<Review> reviewList = MockData.getReviews();

        ReviewAdapter adapter = new ReviewAdapter(reviewList);
        rvReviews.setAdapter(adapter);
    }

    private void setupListeners() {
        tvBack.setOnClickListener(v -> finish());

        btnWriteReview.setOnClickListener(v -> {
            Intent intent = new Intent(ReviewActivity.this, WriteReviewActivity.class);
            intent.putExtra("EXTRA_RESTAURANT_NAME", restaurantName);
            startActivity(intent);
        });
    }
}
