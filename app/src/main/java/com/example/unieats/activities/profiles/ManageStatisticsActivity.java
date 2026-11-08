package com.example.unieats.activities.profiles;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.unieats.R;

public class ManageStatisticsActivity extends AppCompatActivity {

    private ImageView tvBack, ivSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manage_statistics);
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
        ivSearch = findViewById(R.id.ivSearch);
    }

    private void setupListeners() {
        tvBack.setOnClickListener(v -> finish());
        ivSearch.setOnClickListener(v -> 
            Toast.makeText(this, "Tìm kiếm thống kê", Toast.LENGTH_SHORT).show()
        );
    }
}
