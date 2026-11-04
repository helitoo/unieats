package com.example.unieats.activities.restaurants;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
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
import com.example.unieats.entities.ReviewPhoto;

import java.util.ArrayList;
import java.util.List;

public class ReviewActivity extends AppCompatActivity {

    private TextView tvBack, tvRestaurantName;
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
        setupReviews();
        setupListeners();
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
        List<Review> reviewList = new ArrayList<>();

        List<ReviewPhoto> photos1 = new ArrayList<>();
        photos1.add(new ReviewPhoto("photo_1"));
        photos1.add(new ReviewPhoto("photo_2"));

        Review review1 = new Review("Phương (Tôi)", "Quán phục vụ rất nhanh, cơm tấm sườn nướng mỡ hành thơm nức mũi!", 5, photos1);
        review1.setLikeCount(24);
        review1.setAuthor(true); // User is the author of this review

        Review review2 = new Review("Lê Thu Hà", "Món ăn ngon hợp vị sinh viên, giá rẻ và chất lượng tuyệt vời.", 5, new ArrayList<>());
        review2.setLikeCount(12);

        Review review3 = new Review("Người dùng · 1", "Quán này tệ, phục vụ rất kém...", "Ngôn từ xúc phạm");
        review3.setLikeCount(2);

        reviewList.add(review1);
        reviewList.add(review2);
        reviewList.add(review3);

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
