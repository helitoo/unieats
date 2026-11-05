package com.example.unieats.activities.restaurants;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {

    private ImageView tvBack, tvClearQuery, tvHeaderSearchIcon;
    private TextView tvResultCount, tvSortOrder;
    private EditText etSearchQuery;
    private RecyclerView rvSearchResults;
    
    private RestaurantAdapter adapter;
    private List<Restaurant> fullList;
    private List<Restaurant> filteredList;
    private boolean isAscending = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupData();
        setupListeners();
        
        // Handle incoming category intent if any
        String categorySlug = getIntent().getStringExtra("EXTRA_CATEGORY_SLUG");
        String categoryName = getIntent().getStringExtra("EXTRA_CATEGORY_NAME");
        if (categoryName != null && !categoryName.isEmpty()) {
            etSearchQuery.setText(categoryName);
        } else if (categorySlug != null && !categorySlug.isEmpty()) {
            etSearchQuery.setText(categorySlug);
        }
    }

    private void initViews() {
        tvBack = findViewById(R.id.tvBack);
        tvClearQuery = findViewById(R.id.tvClearQuery);
        tvResultCount = findViewById(R.id.tvResultCount);
        tvSortOrder = findViewById(R.id.tvSortOrder);
        etSearchQuery = findViewById(R.id.etSearchQuery);
        rvSearchResults = findViewById(R.id.rvSearchResults);

        rvSearchResults.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupData() {
        fullList = new ArrayList<>();
        fullList.add(new Restaurant("Cơm nhà Góc Sân Trường", "Khu Phố 6, Linh Trung, Thủ Đức", 4.8, 128, 25000, 45000, "500 m", "Giá sinh viên"));
        fullList.add(new Restaurant("Bún bò Cô Ba", "Làng Đại Học, Thủ Đức", 4.7, 96, 30000, 45000, "800 m", "Được yêu thích"));
        fullList.add(new Restaurant("Trà sữa Gong Cha", "Đường Võ Văn Ngân, Thủ Đức", 4.5, 210, 35000, 60000, "1.2 km", "Giảm 20%"));
        fullList.add(new Restaurant("Bún đậu Mắm Tôm Phố Cổ", "Đường Đặng Văn Bi, Thủ Đức", 4.6, 85, 35000, 55000, "1.5 km", "Gần tôi"));
        fullList.add(new Restaurant("Cơm Tấm Ba Ghiền", "Xa Lộ Hà Nội, Thủ Đức", 4.9, 340, 40000, 75000, "2.0 km", "Quán HOT"));

        filteredList = new ArrayList<>(fullList);
        adapter = new RestaurantAdapter(filteredList);
        rvSearchResults.setAdapter(adapter);

        updateResultCount();
    }

    private void setupListeners() {
        tvBack.setOnClickListener(v -> finish());

        tvClearQuery.setOnClickListener(v -> etSearchQuery.setText(""));

        etSearchQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        tvSortOrder.setOnClickListener(v -> {
            isAscending = !isAscending;
            sortList();
            if (isAscending) {
                tvSortOrder.setText("Tăng dần (giá)");
                Toast.makeText(SearchActivity.this, "Sắp xếp giá tăng dần", Toast.LENGTH_SHORT).show();
            } else {
                tvSortOrder.setText("Giảm dần (giá)");
                Toast.makeText(SearchActivity.this, "Sắp xếp giá giảm dần", Toast.LENGTH_SHORT).show();
            }
        });
        
        // Chip click listeners
        setupChipListeners();
    }

    private void setupChipListeners() {
        TextView chip1 = findViewById(R.id.chip1);
        TextView chip2 = findViewById(R.id.chip2);
        TextView chip3 = findViewById(R.id.chip3);
        TextView chip4 = findViewById(R.id.chip4);
        TextView chip5 = findViewById(R.id.chip5);

        TextView[] chips = new TextView[]{chip1, chip2, chip3, chip4, chip5};
        for (TextView chip : chips) {
            if (chip != null) {
                chip.setOnClickListener(v -> {
                    for (TextView c : chips) {
                        if (c != null) {
                            c.setBackgroundResource(R.drawable.bg_chip_normal);
                            c.setTextColor(getResources().getColor(R.color.neutral_700, getTheme()));
                        }
                    }
                    chip.setBackgroundResource(R.drawable.bg_chip_selected);
                    chip.setTextColor(getResources().getColor(R.color.orange_500, getTheme()));

                    filter(chip.getText().toString());
                });
            }
        }
    }

    private void filter(String query) {
        filteredList.clear();
        if (query == null || query.trim().isEmpty()) {
            filteredList.addAll(fullList);
        } else {
            String lowerQuery = query.toLowerCase(Locale.getDefault());
            for (Restaurant item : fullList) {
                if (item.getName().toLowerCase(Locale.getDefault()).contains(lowerQuery)
                        || item.getAddress().toLowerCase(Locale.getDefault()).contains(lowerQuery)
                        || (item.getTag() != null && item.getTag().toLowerCase(Locale.getDefault()).contains(lowerQuery))) {
                    filteredList.add(item);
                }
            }
        }
        sortList();
        updateResultCount();
    }

    private void sortList() {
        Collections.sort(filteredList, new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant o1, Restaurant o2) {
                if (isAscending) {
                    return Double.compare(o1.getPriceRangeMin(), o2.getPriceRangeMin());
                } else {
                    return Double.compare(o2.getPriceRangeMin(), o1.getPriceRangeMin());
                }
            }
        });
        adapter.notifyDataSetChanged();
    }

    private void updateResultCount() {
        tvResultCount.setText(String.format(Locale.getDefault(), "%d quán phù hợp với bạn", filteredList.size()));
    }
}
