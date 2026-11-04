package com.example.unieats.activities.restaurants;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.unieats.R;

public class ContributeRestaurantActivity extends AppCompatActivity {

    private TextView tvBack, tvUploadCover, tvUploadMenu;
    private EditText etName, etAddress, etCategory, etPriceMin, etPriceMax;
    private Button btnSelectCoordinates, btnSubmit;

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
        btnSelectCoordinates = findViewById(R.id.btnSelectCoordinates);
        btnSubmit = findViewById(R.id.btnSubmit);
    }

    private void setupListeners() {
        tvBack.setOnClickListener(v -> finish());

        btnSelectCoordinates.setOnClickListener(v -> 
            Toast.makeText(ContributeRestaurantActivity.this, "Đã chọn tọa độ trên bản đồ (10.87, 106.80)", Toast.LENGTH_SHORT).show()
        );

        tvUploadCover.setOnClickListener(v -> 
            Toast.makeText(ContributeRestaurantActivity.this, "Chọn ảnh không gian quán", Toast.LENGTH_SHORT).show()
        );

        tvUploadMenu.setOnClickListener(v -> 
            Toast.makeText(ContributeRestaurantActivity.this, "Chọn ảnh menu / bảng giá", Toast.LENGTH_SHORT).show()
        );

        btnSubmit.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            if (name.isEmpty()) {
                Toast.makeText(ContributeRestaurantActivity.this, "Vui lòng nhập tên quán!", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(ContributeRestaurantActivity.this, "Đã gửi thông tin quán chờ duyệt!", Toast.LENGTH_LONG).show();
            finish();
        });
    }
}
