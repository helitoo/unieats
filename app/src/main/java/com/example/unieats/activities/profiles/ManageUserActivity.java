package com.example.unieats.activities.profiles;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.example.unieats.helpers.MockData;

import java.util.ArrayList;
import java.util.List;

public class ManageUserActivity extends AppCompatActivity {

    private ImageView tvBack;
    private EditText etUserSearch;
    private RecyclerView rvUsers;
    private TextView tvTotalUsersCount, tvActiveUsersCount, tvDisabledUsersCount;

    private List<User> fullUserList;
    private List<User> displayUserList;
    private ManageUserAdapter adapter;

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
        etUserSearch = findViewById(R.id.etUserSearch);
        rvUsers = findViewById(R.id.rvUsers);
        rvUsers.setLayoutManager(new LinearLayoutManager(this));

        tvTotalUsersCount = findViewById(R.id.tvTotalUsersCount);
        tvActiveUsersCount = findViewById(R.id.tvActiveUsersCount);
        tvDisabledUsersCount = findViewById(R.id.tvDisabledUsersCount);
    }

    private void setupUserList() {
        fullUserList = MockData.getUsers();
        displayUserList = new ArrayList<>(fullUserList);

        updateStatistics(fullUserList);

        adapter = new ManageUserAdapter(displayUserList);
        rvUsers.setAdapter(adapter);
    }

    private void filterUsers(String query) {
        displayUserList.clear();
        if (query == null || query.trim().isEmpty()) {
            displayUserList.addAll(fullUserList);
        } else {
            String lower = query.trim().toLowerCase();
            for (User u : fullUserList) {
                if ((u.getName() != null && u.getName().toLowerCase().contains(lower)) ||
                    (u.getEmail() != null && u.getEmail().toLowerCase().contains(lower)) ||
                    (u.getPhone() != null && u.getPhone().contains(lower)) ||
                    (u.getRole() != null && u.getRole().toLowerCase().contains(lower))) {
                    displayUserList.add(u);
                }
            }
        }
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    private void updateStatistics(List<User> userList) {
        int total = userList.size();
        int activeCount = 0;
        int disabledCount = 0;

        for (User u : userList) {
            if ("Đang hoạt động".equalsIgnoreCase(u.getStatus()) || u.isActive()) {
                activeCount++;
            } else {
                disabledCount++;
            }
        }

        if (tvTotalUsersCount != null) tvTotalUsersCount.setText(String.valueOf(total));
        if (tvActiveUsersCount != null) tvActiveUsersCount.setText(String.valueOf(activeCount));
        if (tvDisabledUsersCount != null) tvDisabledUsersCount.setText(String.valueOf(disabledCount));
    }

    private void setupListeners() {
        tvBack.setOnClickListener(v -> finish());

        if (etUserSearch != null) {
            etUserSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    filterUsers(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {}
            });
        }
    }
}
