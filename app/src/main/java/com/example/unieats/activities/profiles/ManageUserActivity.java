package com.example.unieats.activities.profiles;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unieats.R;
import com.example.unieats.adapters.ManageUserAdapter;
import com.example.unieats.entities.User;

import java.util.ArrayList;
import java.util.List;

public class ManageUserActivity extends AppCompatActivity {

    private ImageView tvBack;
    private RecyclerView rvUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manage_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupUserList();
        setupListeners();
    }

    private void initViews() {
        tvBack = findViewById(R.id.tvBack);
        rvUsers = findViewById(R.id.rvUsers);
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupUserList() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("1", "Nguyễn Minh Anh", "minhanh@gmail.com", "0901234567", "USER", "Đang hoạt động", null));
        userList.add(new User("2", "Trần Thị Bình", "thibinh@gmail.com", "0912345678", "MODERATOR", "Đang hoạt động", null));
        userList.add(new User("3", "Lê Hoài Cường", "hoaicuong@gmail.com", "0987654321", "USER", "Tạm dừng", null));
        userList.add(new User("4", "Phạm Hoàng Dung", "hoangdung@gmail.com", "0971122334", "ADMIN", "Đang hoạt động", null));
        userList.add(new User("5", "Đỗ Quốc Emp", "quocemp@gmail.com", "0934455667", "USER", "Đang hoạt động", null));

        ManageUserAdapter adapter = new ManageUserAdapter(userList);
        rvUsers.setAdapter(adapter);
    }

    private void setupListeners() {
        tvBack.setOnClickListener(v -> finish());
    }
}
