package com.example.unieats.activities.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
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
import com.example.unieats.activities.MainActivity;
import com.example.unieats.entities.User;
import com.example.unieats.helpers.AuthManager;
import com.example.unieats.helpers.MockData;

public class SigninActivity extends AppCompatActivity {

    private EditText etEmailPhone, etPassword;
    private ImageView tvTogglePassword;
    private TextView tvForgotPassword, tvSignUpPrompt;
    private Button btnSignin, btnGoogle, btnFacebook;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signin);
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
        etPassword = findViewById(R.id.etPassword);
        tvTogglePassword = findViewById(R.id.tvTogglePassword);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        tvSignUpPrompt = findViewById(R.id.tvSignUpPrompt);
        btnSignin = findViewById(R.id.btnSignin);
        btnGoogle = findViewById(R.id.btnGoogle);
        btnFacebook = findViewById(R.id.btnFacebook);
    }

    private void setupListeners() {
        tvTogglePassword.setOnClickListener(v -> {
            if (isPasswordVisible) {
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                isPasswordVisible = false;
                tvTogglePassword.setImageResource(R.drawable.ic_eye);
            } else {
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                isPasswordVisible = true;
                tvTogglePassword.setImageResource(R.drawable.ic_eye_slash);
            }
            etPassword.setSelection(etPassword.getText().length());
        });

        btnSignin.setOnClickListener(v -> {
            String input = etEmailPhone.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();

            if (input.isEmpty()) {
                etEmailPhone.setError("Vui lòng nhập Email hoặc SĐT");
                return;
            }

            User authenticatedUser = MockData.authenticate(input, pass);
            if (authenticatedUser != null) {
                MockData.setCurrentUser(authenticatedUser);
                AuthManager.setLoggedIn(SigninActivity.this, true);
                Toast.makeText(SigninActivity.this, String.format("Xin chào %s! Đăng nhập thành công", authenticatedUser.getName()), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(SigninActivity.this, "Tài khoản không tồn tại! Gợi ý: phuong@example.com hoặc hoangdung@gmail.com", Toast.LENGTH_LONG).show();
            }
        });

        btnGoogle.setOnClickListener(v -> 
            Toast.makeText(SigninActivity.this, "Tiếp tục với Google", Toast.LENGTH_SHORT).show()
        );

        btnFacebook.setOnClickListener(v -> 
            Toast.makeText(SigninActivity.this, "Tiếp tục với Facebook", Toast.LENGTH_SHORT).show()
        );

        tvForgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(SigninActivity.this, PasswordRestoreActivity.class);
            startActivity(intent);
        });

        tvSignUpPrompt.setOnClickListener(v -> {
            Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }
}
