package com.example.unieats.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.unieats.R;
import com.example.unieats.activities.profiles.EditProfileActivity;
import com.example.unieats.activities.profiles.HistoryActivity;
import com.example.unieats.activities.profiles.ManageRestaurantActivity;
import com.example.unieats.activities.profiles.ManageUserActivity;
import com.example.unieats.activities.profiles.MyRestaurantActivity;
import com.example.unieats.activities.profiles.SavedRestaurantsActivity;
import com.example.unieats.entities.User;
import com.example.unieats.helpers.AuthManager;
import com.example.unieats.helpers.MockData;

public class ProfileFragment extends Fragment {

    private TextView tvUserName, tvUserRole;
    private View cvEditProfile, cvHistory, cvSavedRestaurants, cvMyRestaurants;
    private View llManagementSection, cvManageUsers, cvManageRestaurants;
    private Button btnLogout;
    private User currentUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        loadUserData();
        setupListeners();
    }

    private void initViews(View view) {
        tvUserName = view.findViewById(R.id.tvUserName);
        tvUserRole = view.findViewById(R.id.tvUserRole);
        cvEditProfile = view.findViewById(R.id.cvEditProfile);
        cvHistory = view.findViewById(R.id.cvHistory);
        cvSavedRestaurants = view.findViewById(R.id.cvSavedRestaurants);
        cvMyRestaurants = view.findViewById(R.id.cvMyRestaurants);

        llManagementSection = view.findViewById(R.id.llManagementSection);
        cvManageUsers = view.findViewById(R.id.cvManageUsers);
        cvManageRestaurants = view.findViewById(R.id.cvManageRestaurants);

        btnLogout = view.findViewById(R.id.btnLogout);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadUserData();
    }

    private void loadUserData() {
        currentUser = MockData.getCurrentUser();
        if (currentUser != null) {
            tvUserName.setText(String.format("Xin chào, %s!", currentUser.getName()));
            tvUserRole.setText(currentUser.getRole());
            applyRolePermissions(currentUser.getRole());
        }
    }

    private void applyRolePermissions(String role) {
        if (role == null) role = "";
        String upperRole = role.toUpperCase();

        if (upperRole.contains("ADMIN")) {
            llManagementSection.setVisibility(View.VISIBLE);
            cvManageUsers.setVisibility(View.VISIBLE);
            cvManageRestaurants.setVisibility(View.VISIBLE);
        } else if (upperRole.contains("MODERATOR")) {
            llManagementSection.setVisibility(View.VISIBLE);
            cvManageUsers.setVisibility(View.GONE);
            cvManageRestaurants.setVisibility(View.VISIBLE);
        } else {
            llManagementSection.setVisibility(View.GONE);
        }
    }

    private void setupListeners() {
        cvEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), EditProfileActivity.class);
            startActivity(intent);
        });

        cvHistory.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), HistoryActivity.class);
            startActivity(intent);
        });

        cvSavedRestaurants.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), SavedRestaurantsActivity.class);
            startActivity(intent);
        });

        cvMyRestaurants.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), MyRestaurantActivity.class);
            startActivity(intent);
        });

        cvManageUsers.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), ManageUserActivity.class);
            startActivity(intent);
        });

        cvManageRestaurants.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), ManageRestaurantActivity.class);
            startActivity(intent);
        });

        btnLogout.setOnClickListener(v -> 
            AuthManager.logout(requireActivity())
        );
    }
}
