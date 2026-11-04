package com.example.unieats.activities.auth;

import android.content.Intent;
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

public class PasswordRestoreActivity extends AppCompatActivity {

    private EditText etEmailPhone;
    private Button btnSendOtp;
    private TextView tvBackToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_password_restore);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupListeners();
    }

    private void initViews() {
        etEmailPhone = findViewById(R.id.etEmailPhone);
        btnSendOtp = findViewById(R.id.btnSendOtp);
        tvBackToLogin = findViewById(R.id.tvBackToLogin);
    }

    private void setupListeners() {
        btnSendOtp.setOnClickListener(v -> 
            Toast.makeText(PasswordRestoreActivity.this, "Mã OTP đã được gửi!", Toast.LENGTH_SHORT).show()
        );

        tvBackToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(PasswordRestoreActivity.this, SigninActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
