package com.example.unieats.activities.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.example.unieats.helpers.FirebaseManager;

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
        btnSignup.setOnClickListener(v -> performSignup());

        tvSignInPrompt.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void performSignup() {
        String fullName = etFullName.getText().toString().trim();
        String emailPhone = etEmailPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        if (TextUtils.isEmpty(fullName)) {
            etFullName.setError("Vui lòng nhập họ tên");
            return;
        }
        if (TextUtils.isEmpty(emailPhone)) {
            etEmailPhone.setError("Vui lòng nhập email");
            return;
        }
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            etPassword.setError("Mật khẩu phải có ít nhất 6 ký tự");
            return;
        }
        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Mật khẩu xác nhận không khớp");
            return;
        }
        if (!cbTerms.isChecked()) {
            Toast.makeText(this, "Vui lòng đồng ý với điều khoản sử dụng!", Toast.LENGTH_SHORT).show();
            return;
        }

        btnSignup.setEnabled(false);

        FirebaseManager.getInstance().getAuth()
                .createUserWithEmailAndPassword(emailPhone, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null && task.getResult().getUser() != null) {
                        String uid = task.getResult().getUser().getUid();
                        User newUser = new User(uid, fullName, emailPhone, "", "USER", "Đang hoạt động", "");

                        FirebaseManager.getInstance().getUsersRef()
                                .document(uid)
                                .set(newUser)
                                .addOnSuccessListener(aVoid -> {
                                    AuthManager.setLoggedIn(SignupActivity.this, true);
                                    Toast.makeText(SignupActivity.this, "Tạo tài khoản thành công!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                })
                                .addOnFailureListener(e -> {
                                    btnSignup.setEnabled(true);
                                    Toast.makeText(SignupActivity.this, "Lỗi lưu dữ liệu: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                });
                    } else {
                        btnSignup.setEnabled(true);
                        String errorMsg = task.getException() != null ? task.getException().getMessage() : "Đăng ký thất bại";
                        Toast.makeText(SignupActivity.this, "Lỗi: " + errorMsg, Toast.LENGTH_LONG).show();
                    }
                });
    }
}
