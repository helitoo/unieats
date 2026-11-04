package com.example.unieats.activities.profiles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.unieats.R;
import com.example.unieats.entities.User;
import com.example.unieats.helpers.AuthManager;
import com.example.unieats.helpers.FooterHelper;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvUserName, tvUserRole;
    private View cvEditProfile, cvHistory, cvSavedRestaurants;
    private Button btnLogout;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        loadUserData();
        setupFooter();
        setupListeners();
    }

    private void initViews() {
        tvUserName = findViewById(R.id.tvUserName);
        tvUserRole = findViewById(R.id.tvUserRole);
        cvEditProfile = findViewById(R.id.cvEditProfile);
        cvHistory = findViewById(R.id.cvHistory);
        cvSavedRestaurants = findViewById(R.id.cvSavedRestaurants);
        btnLogout = findViewById(R.id.btnLogout);
    }

    private void loadUserData() {
        currentUser = new User("Phương", "phuong@example.com", "0901234567", "Thành viên Foodie");
        tvUserName.setText(String.format("Xin chào, %s!", currentUser.getName()));
        tvUserRole.setText(currentUser.getRole());
    }

    private void setupFooter() {
        FooterHelper.setupFooter(this, FooterHelper.Tab.PROFILE);
    }

    private void setupListeners() {
        cvEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
            startActivity(intent);
        });

        cvHistory.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, HistoryActivity.class);
            startActivity(intent);
        });

        cvSavedRestaurants.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, SavedRestaurantsActivity.class);
            startActivity(intent);
        });

        btnLogout.setOnClickListener(v -> 
            AuthManager.logout(ProfileActivity.this)
        );
    }
}
