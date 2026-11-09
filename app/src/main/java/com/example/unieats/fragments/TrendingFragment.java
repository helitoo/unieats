package com.example.unieats.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unieats.R;
import com.example.unieats.activities.MainActivity;
import com.example.unieats.adapters.RestaurantAdapter;
import com.example.unieats.entities.Restaurant;
import com.example.unieats.helpers.FooterHelper;
import com.example.unieats.helpers.MockData;

import java.util.ArrayList;
import java.util.List;

public class TrendingFragment extends Fragment {

    private ImageView tvBack, tvHeaderSearchIcon;
    private RecyclerView rvHotRestaurants;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trending, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        setupHotRestaurants();
        setupListeners();
    }

    private void initViews(View view) {
        tvBack = view.findViewById(R.id.tvBack);
        tvHeaderSearchIcon = view.findViewById(R.id.tvHeaderSearchIcon);
        rvHotRestaurants = view.findViewById(R.id.rvHotRestaurants);

        rvHotRestaurants.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void setupHotRestaurants() {
        List<Restaurant> hotList = MockData.getHotRestaurants();

        RestaurantAdapter adapter = new RestaurantAdapter(hotList);
        rvHotRestaurants.setAdapter(adapter);
    }

    private void setupListeners() {
        tvBack.setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).switchTab(FooterHelper.Tab.HOME);
            }
        });

        tvHeaderSearchIcon.setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).switchTab(FooterHelper.Tab.SEARCH);
            }
        });
    }
}
