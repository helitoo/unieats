package com.example.unieats.activities.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.unieats.R;
import com.example.unieats.activities.MainActivity;
import com.example.unieats.entities.User;
import com.example.unieats.helpers.AuthManager;
import com.example.unieats.helpers.MockData;

public class SignupActivity extends AppCompatActivity {

    private EditText etFullName, etEmailPhone, etPassword, etConfirmPassword;
    private CheckBox cbTerms;
    private Button btnSignup;
    private TextView tvSignInPrompt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupListeners();
    }

    private void initViews() {
        etFullName = findViewById(R.id.etFullName);
        etEmailPhone = findViewById(R.id.etEmailPhone);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        cbTerms = findViewById(R.id.cbTerms);
        btnSignup = findViewById(R.id.btnSignup);
        tvSignInPrompt = findViewById(R.id.tvSignInPrompt);
    }

    private void setupListeners() {
        btnSignup.setOnClickListener(v -> {
            String name = etFullName.getText().toString().trim();
            String emailPhone = etEmailPhone.getText().toString().trim();

            if (name.isEmpty()) {
                name = "Người dùng mới";
            }

            User newUser = new User(String.valueOf(System.currentTimeMillis()), name, emailPhone.contains("@") ? emailPhone : "user@example.com", emailPhone, "USER", "Đang hoạt động", null);
            MockData.addUser(newUser);
            MockData.setCurrentUser(newUser);

            AuthManager.setLoggedIn(SignupActivity.this, true);
            Toast.makeText(SignupActivity.this, "Tạo tài khoản thành công!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        tvSignInPrompt.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
