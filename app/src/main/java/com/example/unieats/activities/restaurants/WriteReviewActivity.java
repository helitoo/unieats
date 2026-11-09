package com.example.unieats.activities.restaurants;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.unieats.R;
import com.example.unieats.entities.Review;
import com.example.unieats.entities.User;
import com.example.unieats.helpers.MockData;

import java.util.ArrayList;

public class WriteReviewActivity extends AppCompatActivity {

    private ImageView tvBack, tvStar1, tvStar2, tvStar3, tvStar4, tvStar5;
    private TextView tvRestaurantName;
    private TextView tvAddPhoto1, tvAddPhoto2, tvAddPhoto3;
    private EditText etReviewComment;
    private Button btnPostReview;
    private int selectedRating = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_write_review);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        readIntentData();
        setupListeners();
        updateStarRating(selectedRating);
    }

    private void initViews() {
        tvBack = findViewById(R.id.tvBack);
        tvRestaurantName = findViewById(R.id.tvRestaurantName);
        tvStar1 = findViewById(R.id.tvStar1);
        tvStar2 = findViewById(R.id.tvStar2);
        tvStar3 = findViewById(R.id.tvStar3);
        tvStar4 = findViewById(R.id.tvStar4);
        tvStar5 = findViewById(R.id.tvStar5);
        tvAddPhoto1 = findViewById(R.id.tvAddPhoto1);
        tvAddPhoto2 = findViewById(R.id.tvAddPhoto2);
        tvAddPhoto3 = findViewById(R.id.tvAddPhoto3);
        etReviewComment = findViewById(R.id.etReviewComment);
        btnPostReview = findViewById(R.id.btnPostReview);
    }

    private void readIntentData() {
        String name = getIntent().getStringExtra("EXTRA_RESTAURANT_NAME");
        if (name != null && !name.isEmpty()) {
            tvRestaurantName.setText(name);
        }
    }

    private void setupListeners() {
        tvBack.setOnClickListener(v -> finish());

        ImageView[] stars = new ImageView[]{tvStar1, tvStar2, tvStar3, tvStar4, tvStar5};
        for (int i = 0; i < stars.length; i++) {
            final int rating = i + 1;
            stars[i].setOnClickListener(v -> {
                selectedRating = rating;
                updateStarRating(selectedRating);
            });
        }

        TextView[] photoBoxes = new TextView[]{tvAddPhoto1, tvAddPhoto2, tvAddPhoto3};
        for (TextView box : photoBoxes) {
            box.setOnClickListener(v -> 
                Toast.makeText(WriteReviewActivity.this, "Chọn ảnh từ thư viện", Toast.LENGTH_SHORT).show()
            );
        }

        btnPostReview.setOnClickListener(v -> {
            String comment = etReviewComment.getText().toString().trim();
            if (comment.isEmpty()) {
                comment = "Chất lượng quán tốt, phục vụ chu đáo!";
            }

            User currentUser = MockData.getCurrentUser();
            String authorName = (currentUser != null && currentUser.getName() != null) ? currentUser.getName() + " (Tôi)" : "Phương (Tôi)";

            Review newReview = new Review(authorName, comment, selectedRating, true, new ArrayList<>());
            MockData.addReview(newReview);

            Toast.makeText(WriteReviewActivity.this, "Đăng đánh giá thành công!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void updateStarRating(int rating) {
        ImageView[] stars = new ImageView[]{tvStar1, tvStar2, tvStar3, tvStar4, tvStar5};
        int orangeColor = getResources().getColor(R.color.orange_500, getTheme());
        int grayColor = getResources().getColor(R.color.neutral_300, getTheme());

        for (int i = 0; i < stars.length; i++) {
            if (i < rating) {
                stars[i].setImageResource(R.drawable.ic_star_filled);
                stars[i].setColorFilter(orangeColor);
            } else {
                stars[i].setImageResource(R.drawable.ic_star_outline);
                stars[i].setColorFilter(grayColor);
            }
        }
    }
}
