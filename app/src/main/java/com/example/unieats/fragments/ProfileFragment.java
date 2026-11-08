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
import com.example.unieats.activities.profiles.ManageStatisticsActivity;
import com.example.unieats.activities.profiles.ManageUserActivity;
import com.example.unieats.activities.profiles.MyRestaurantActivity;
import com.example.unieats.activities.profiles.SavedRestaurantsActivity;
import com.example.unieats.entities.User;
import com.example.unieats.helpers.AuthManager;
import com.example.unieats.helpers.FirebaseManager;

public class ProfileFragment extends Fragment {

    private TextView tvUserName, tvUserRole;
    private View cvEditProfile, cvHistory, cvSavedRestaurants, cvMyRestaurants;
    private View llManagementSection, cvManageUsers, cvManageStatistics, cvManageRestaurants;
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
        cvManageStatistics = view.findViewById(R.id.cvManageStatistics);
        cvManageRestaurants = view.findViewById(R.id.cvManageRestaurants);

        btnLogout = view.findViewById(R.id.btnLogout);
    }

    private void loadUserData() {
        if (FirebaseManager.getInstance().isLoggedIn() && FirebaseManager.getInstance().getCurrentUser() != null) {
            String uid = FirebaseManager.getInstance().getCurrentUser().getUid();
            FirebaseManager.getInstance().getUsersRef().document(uid)
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            User user = documentSnapshot.toObject(User.class);
                            if (user != null) {
                                currentUser = user;
                                String displayName = user.getName() != null && !user.getName().isEmpty()
                                        ? user.getName() : "Người dùng";
                                tvUserName.setText(String.format("Xin chào, %s!", displayName));
                                tvUserRole.setText(user.getRole() != null ? user.getRole() : "USER");
                                applyRolePermissions(user.getRole());
                                return;
                            }
                        }
                        setFallbackUserData();
                    })
                    .addOnFailureListener(e -> setFallbackUserData());
        } else {
            setFallbackUserData();
        }
    }

    private void setFallbackUserData() {
        currentUser = new User("Phương", "phuong@example.com", "0901234567", "ADMIN");
        tvUserName.setText(String.format("Xin chào, %s!", currentUser.getName()));
        tvUserRole.setText(currentUser.getRole());
        applyRolePermissions(currentUser.getRole());
    }

    private void applyRolePermissions(String role) {
        if (role == null) role = "";
        String upperRole = role.toUpperCase();

        if (upperRole.contains("ADMIN")) {
            llManagementSection.setVisibility(View.VISIBLE);
            cvManageUsers.setVisibility(View.VISIBLE);
            cvManageStatistics.setVisibility(View.VISIBLE);
            cvManageRestaurants.setVisibility(View.VISIBLE);
        } else if (upperRole.contains("MODERATOR")) {
            llManagementSection.setVisibility(View.VISIBLE);
            cvManageUsers.setVisibility(View.GONE);
            cvManageStatistics.setVisibility(View.GONE);
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

        cvManageStatistics.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), ManageStatisticsActivity.class);
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
