package com.example.unieats.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unieats.R;
import com.example.unieats.activities.MainActivity;
import com.example.unieats.activities.restaurants.ContributeRestaurantActivity;
import com.example.unieats.adapters.CategoryAdapter;
import com.example.unieats.adapters.RestaurantAdapter;
import com.example.unieats.entities.Category;
import com.example.unieats.entities.Restaurant;
import com.example.unieats.helpers.FooterHelper;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private View llSearchBar, llHotSectionHeader;
    private TextView tvAddRestaurant;
    private RecyclerView rvCategories;
    private RecyclerView rvNearbyRestaurants;
    private RecyclerView rvHotRestaurants;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        setupCategories();
        setupNearbyRestaurants();
        setupHotRestaurants();
        setupListeners();
    }

    private void initViews(View view) {
        tvAddRestaurant = view.findViewById(R.id.tvAddRestaurant);
        llSearchBar = view.findViewById(R.id.llSearchBar);
        llHotSectionHeader = view.findViewById(R.id.llHotSectionHeader);
        rvCategories = view.findViewById(R.id.rvCategories);
        rvNearbyRestaurants = view.findViewById(R.id.rvNearbyRestaurants);
        rvHotRestaurants = view.findViewById(R.id.rvHotRestaurants);
    }

    private void setupCategories() {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("Cơm", "com", R.drawable.ic_category_rice));
        categoryList.add(new Category("Bún/Phở/Mì", "bun-pho-mi", R.drawable.ic_category_noodle));
        categoryList.add(new Category("Ăn vặt", "an-vat", R.drawable.ic_category_snack));
        categoryList.add(new Category("Trà sữa/Cà phê", "tra-sua-ca-phe", R.drawable.ic_category_coffee));
        categoryList.add(new Category("Lẩu & Nướng", "lau-nuong", R.drawable.ic_category_bbq));

        rvCategories.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList, category -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).switchTab(FooterHelper.Tab.SEARCH, category.getName());
            }
        });
        rvCategories.setAdapter(categoryAdapter);
    }

    private void setupNearbyRestaurants() {
        List<Restaurant> nearbyList = new ArrayList<>();
        nearbyList.add(new Restaurant("Cơm nhà Góc Sân Trường", "Khu Phố 6, Linh Trung, Thủ Đức", 4.8, 128, 25000, 45000, "500 m", "Giá sinh viên"));
        nearbyList.add(new Restaurant("Bún bò Cô Ba", "Làng Đại Học, Thủ Đức", 4.7, 96, 30000, 45000, "800 m", "Được yêu thích"));

        rvNearbyRestaurants.setLayoutManager(new LinearLayoutManager(requireContext()));
        RestaurantAdapter adapter = new RestaurantAdapter(nearbyList);
        rvNearbyRestaurants.setAdapter(adapter);
    }

    private void setupHotRestaurants() {
        List<Restaurant> hotList = new ArrayList<>();
        hotList.add(new Restaurant("Bún bò Cô Ba", "Làng Đại Học, Thủ Đức", 4.7, 96, 30000, 45000, "800 m", "Được yêu thích"));
        hotList.add(new Restaurant("Cơm Tấm Ba Ghiền", "Xa Lộ Hà Nội, Thủ Đức", 4.9, 340, 40000, 75000, "2.0 km", "Quán HOT"));

        rvHotRestaurants.setLayoutManager(new LinearLayoutManager(requireContext()));
        RestaurantAdapter adapter = new RestaurantAdapter(hotList);
        rvHotRestaurants.setAdapter(adapter);
    }

    private void setupListeners() {
        if (tvAddRestaurant != null) {
            tvAddRestaurant.setOnClickListener(v -> {
                Intent intent = new Intent(requireContext(), ContributeRestaurantActivity.class);
                startActivity(intent);
            });
        }

        llSearchBar.setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).switchTab(FooterHelper.Tab.SEARCH);
            }
        });

        View.OnClickListener openTrending = v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).switchTab(FooterHelper.Tab.TRENDING);
            }
        };

        if (llHotSectionHeader != null) {
            llHotSectionHeader.setOnClickListener(openTrending);
        }
    }
}
