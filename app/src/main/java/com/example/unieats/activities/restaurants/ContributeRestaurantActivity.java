package com.example.unieats.activities.restaurants;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.unieats.R;
import com.example.unieats.entities.Restaurant;
import com.example.unieats.helpers.MockData;

public class ContributeRestaurantActivity extends AppCompatActivity {

    private ImageView tvBack;
    private TextView tvUploadCover, tvUploadMenu;
    private EditText etName, etAddress, etCategory, etPriceMin, etPriceMax;
    private Button btnSubmit;
    private Uri coverUri = null;
    private Uri menuUri = null;

    private final ActivityResultLauncher<String> pickCoverLauncher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    coverUri = uri;
                    tvUploadCover.setText("✓ Đã chọn ảnh bìa quán");
                    tvUploadCover.setTextColor(getResources().getColor(R.color.orange_500, getTheme()));
                    Toast.makeText(this, "Đã chọn ảnh bìa từ thư viện thiết bị!", Toast.LENGTH_SHORT).show();
                }
            });

    private final ActivityResultLauncher<String> pickMenuLauncher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    menuUri = uri;
                    tvUploadMenu.setText("✓ Đã chọn ảnh thực đơn");
                    tvUploadMenu.setTextColor(getResources().getColor(R.color.orange_500, getTheme()));
                    Toast.makeText(this, "Đã chọn ảnh menu/bảng giá từ thư viện thiết bị!", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contribute_restaurant);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupListeners();
    }

    private void initViews() {
        tvBack = findViewById(R.id.tvBack);
        tvUploadCover = findViewById(R.id.tvUploadCover);
        tvUploadMenu = findViewById(R.id.tvUploadMenu);
        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);
        etCategory = findViewById(R.id.etCategory);
        etPriceMin = findViewById(R.id.etPriceMin);
        etPriceMax = findViewById(R.id.etPriceMax);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Make category click-only dropdown
        etCategory.setFocusable(false);
        etCategory.setClickable(true);
    }

    private void setupListeners() {
        tvBack.setOnClickListener(v -> finish());

        etCategory.setOnClickListener(v -> showCategorySelectionDialog());

        tvUploadCover.setOnClickListener(v -> pickCoverLauncher.launch("image/*"));

        tvUploadMenu.setOnClickListener(v -> pickMenuLauncher.launch("image/*"));

        btnSubmit.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String address = etAddress.getText().toString().trim();
            String minStr = etPriceMin.getText().toString().trim();
            String maxStr = etPriceMax.getText().toString().trim();
            String category = etCategory.getText().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(ContributeRestaurantActivity.this, "Vui lòng nhập tên quán!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (address.isEmpty()) {
                address = "Làng Đại Học, Thủ Đức";
            }

            double minPrice = minStr.isEmpty() ? 25000 : Double.parseDouble(minStr);
            double maxPrice = maxStr.isEmpty() ? 50000 : Double.parseDouble(maxStr);

            Restaurant newRest = new Restaurant(name, address, 5.0, 1, minPrice, maxPrice, "500 m", category.isEmpty() ? "Món Việt" : category);
            newRest.setStatus("PENDING");
            if (coverUri != null) {
                newRest.setCoverImageUrl(coverUri.toString());
            } else {
                newRest.setCoverImageUrl("cover_photo.jpg");
            }

            if (menuUri != null) {
                newRest.getAttachedPhotos().add(menuUri.toString());
            } else {
                newRest.getAttachedPhotos().add("photo_menu_1");
            }
            newRest.getAttachedPhotos().add("photo_menu_2");

            MockData.addContributedRestaurant(newRest);

            Toast.makeText(ContributeRestaurantActivity.this, "Đã gửi đóng góp quán & ảnh menu thành công! Quán đang chờ Admin kiểm duyệt.", Toast.LENGTH_LONG).show();
            finish();
        });
    }

    private void showCategorySelectionDialog() {
        String[] categories = new String[]{"Cơm", "Bún / Phở / Mì", "Ăn vặt", "Trà sữa / Cà phê", "Lẩu & Nướng", "Đồ ăn nhanh"};
        new AlertDialog.Builder(this)
                .setTitle("Chọn danh mục món ăn")
                .setItems(categories, (dialog, which) -> etCategory.setText(categories[which]))
                .show();
    }
}
