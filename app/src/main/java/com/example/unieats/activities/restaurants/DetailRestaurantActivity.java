package com.example.unieats.activities.restaurants;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.example.unieats.adapters.MenuAdapter;
import com.example.unieats.entities.Menu;
import com.example.unieats.entities.Restaurant;
import com.example.unieats.helpers.FooterHelper;
import com.example.unieats.helpers.MockData;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DetailRestaurantActivity extends AppCompatActivity {

    private ImageView tvBack, tvBookmark;
    private TextView tvDetailName, tvDetailAddress, tvRatingScore, tvReviewCountText;
    private View cvRatingCard, llWriteReviewPrompt;
    private Button btnSubmitReview, btnDirections;
    private RecyclerView rvMenu;
    private String restaurantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_restaurant);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        readIntentData();
        setupFooter();
        setupMenu();
        setupListeners();
    }

    private void initViews() {
        tvBack = findViewById(R.id.tvBack);
        tvBookmark = findViewById(R.id.tvBookmark);
        tvDetailName = findViewById(R.id.tvDetailName);
        tvDetailAddress = findViewById(R.id.tvDetailAddress);
        tvRatingScore = findViewById(R.id.tvRatingScore);
        tvReviewCountText = findViewById(R.id.tvReviewCountText);
        cvRatingCard = findViewById(R.id.cvRatingCard);
        llWriteReviewPrompt = findViewById(R.id.llWriteReviewPrompt);
        btnSubmitReview = findViewById(R.id.btnSubmitReview);
        btnDirections = findViewById(R.id.btnDirections);
        rvMenu = findViewById(R.id.rvMenu);

        rvMenu.setLayoutManager(new LinearLayoutManager(this));
    }

    private void readIntentData() {
        restaurantName = getIntent().getStringExtra("EXTRA_RESTAURANT_NAME");
        if (restaurantName == null || restaurantName.isEmpty()) {
            restaurantName = "Cơm Tấm Nhà Mình";
        }
        tvDetailName.setText(restaurantName);

        String address = getIntent().getStringExtra("EXTRA_RESTAURANT_ADDRESS");
        if (address != null && !address.isEmpty()) {
            tvDetailAddress.setText(String.format("Địa chỉ: %s", address));
        }

        double rating = getIntent().getDoubleExtra("EXTRA_RESTAURANT_RATING", 4.8);
        tvRatingScore.setText(String.format(Locale.getDefault(), "%.1f", rating));

        int reviewCount = getIntent().getIntExtra("EXTRA_RESTAURANT_REVIEW_COUNT", 120);
        tvReviewCountText.setText(String.format(Locale.getDefault(), "%d+ Đánh giá", reviewCount));
    }

    private void setupFooter() {
        FooterHelper.setupFooter(this, FooterHelper.Tab.HOME);
    }

    private void setupMenu() {
        List<Menu> menuList = MockData.getMenus();

        MenuAdapter adapter = new MenuAdapter(menuList);
        rvMenu.setAdapter(adapter);
    }

    private void setupListeners() {
        tvBack.setOnClickListener(v -> finish());

        tvBookmark.setOnClickListener(v -> {
            String address = tvDetailAddress.getText().toString().replace("Địa chỉ: ", "").trim();
            Restaurant currentRest = null;
            for (Restaurant r : MockData.getRestaurants()) {
                if (r.getName().equalsIgnoreCase(restaurantName)) {
                    currentRest = r;
                    break;
                }
            }

            if (currentRest == null) {
                currentRest = new Restaurant(restaurantName, address, 4.8, 120, 25000, 50000, "500 m", "Đã lưu");
                MockData.addRestaurant(currentRest);
            }

            boolean wasSaved = MockData.isRestaurantSaved(currentRest);
            MockData.toggleSaveRestaurant(currentRest);

            if (wasSaved) {
                Toast.makeText(DetailRestaurantActivity.this, "Đã hủy lưu quán ăn", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DetailRestaurantActivity.this, "Đã lưu quán ăn thành công", Toast.LENGTH_SHORT).show();
            }
        });

        if (btnDirections != null) {
            btnDirections.setOnClickListener(v -> {
                String fullAddressText = tvDetailAddress.getText().toString();
                String address = fullAddressText.replace("Địa chỉ: ", "").trim();
                String query = address.isEmpty() ? restaurantName : address;

                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(query));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                try {
                    startActivity(mapIntent);
                } catch (Exception e) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/?api=1&query=" + Uri.encode(query)));
                    startActivity(browserIntent);
                }
            });
        }

        // Click on rating card -> ReviewActivity
        cvRatingCard.setOnClickListener(v -> {
            Intent intent = new Intent(DetailRestaurantActivity.this, ReviewActivity.class);
            intent.putExtra("EXTRA_RESTAURANT_NAME", restaurantName);
            startActivity(intent);
        });

        // Click on write review -> WriteReviewActivity
        View.OnClickListener goToWriteReview = v -> {
            Intent intent = new Intent(DetailRestaurantActivity.this, WriteReviewActivity.class);
            intent.putExtra("EXTRA_RESTAURANT_NAME", restaurantName);
            startActivity(intent);
        };

        llWriteReviewPrompt.setOnClickListener(goToWriteReview);
        btnSubmitReview.setOnClickListener(goToWriteReview);
    }
}
