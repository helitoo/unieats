package com.example.unieats.activities.profiles;

import android.content.Intent;
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
import com.example.unieats.activities.auth.PasswordRestoreActivity;
import com.example.unieats.entities.User;
import com.example.unieats.helpers.MockData;

public class EditProfileActivity extends AppCompatActivity {

    private ImageView tvBack;
    private TextView tvChangePassword;
    private EditText etName, etEmail, etPhone;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        loadProfileData();
        setupListeners();
    }

    private void initViews() {
        tvBack = findViewById(R.id.tvBack);
        tvChangePassword = findViewById(R.id.tvChangePassword);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        btnSave = findViewById(R.id.btnSave);
    }

    private void loadProfileData() {
        User u = MockData.getCurrentUser();
        if (u != null) {
            etName.setText(u.getName());
            etEmail.setText(u.getEmail());
            etPhone.setText(u.getPhone());
        }
    }

    private void setupListeners() {
        tvBack.setOnClickListener(v -> finish());

        tvChangePassword.setOnClickListener(v -> {
            Intent intent = new Intent(EditProfileActivity.this, PasswordRestoreActivity.class);
            startActivity(intent);
        });

        btnSave.setOnClickListener(v -> {
            String newName = etName.getText().toString().trim();
            String newEmail = etEmail.getText().toString().trim();
            String newPhone = etPhone.getText().toString().trim();

            if (newName.isEmpty()) {
                etName.setError("Vui lòng nhập họ tên!");
                return;
            }

            User u = MockData.getCurrentUser();
            if (u != null) {
                u.setName(newName);
                u.setEmail(newEmail);
                u.setPhone(newPhone);
            }

            Toast.makeText(EditProfileActivity.this, "Cập nhật hồ sơ thành công!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
