package com.example.unieats.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class SearchFragment extends Fragment {

    private ImageView tvBack, tvClearQuery, tvHeaderSearchIcon;
    private TextView tvResultCount, tvSortOrder;
    private EditText etSearchQuery;
    private RecyclerView rvSearchResults;

    private RestaurantAdapter adapter;
    private List<Restaurant> fullList;
    private List<Restaurant> filteredList;
    private boolean isAscending = true;
    private String initialQuery = "";

    public static SearchFragment newInstance(String initialQuery) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        if (initialQuery != null) {
            args.putString("ARG_QUERY", initialQuery);
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            initialQuery = getArguments().getString("ARG_QUERY", "");
        }

        initViews(view);
        setupData();
        setupListeners(view);

        if (initialQuery != null && !initialQuery.isEmpty()) {
            etSearchQuery.setText(initialQuery);
        }
    }

    private void initViews(View view) {
        tvBack = view.findViewById(R.id.tvBack);
        tvClearQuery = view.findViewById(R.id.tvClearQuery);
        tvHeaderSearchIcon = view.findViewById(R.id.tvHeaderSearchIcon);
        tvResultCount = view.findViewById(R.id.tvResultCount);
        tvSortOrder = view.findViewById(R.id.tvSortOrder);
        etSearchQuery = view.findViewById(R.id.etSearchQuery);
        rvSearchResults = view.findViewById(R.id.rvSearchResults);

        rvSearchResults.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void setupData() {
        fullList = MockData.getRestaurants();

        filteredList = new ArrayList<>(fullList);
        adapter = new RestaurantAdapter(filteredList);
        rvSearchResults.setAdapter(adapter);

        updateResultCount();
    }

    private String activeChipFilter = "";

    private void setupListeners(View view) {
        tvBack.setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).switchTab(FooterHelper.Tab.HOME);
            }
        });

        tvClearQuery.setOnClickListener(v -> etSearchQuery.setText(""));

        etSearchQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        tvSortOrder.setOnClickListener(v -> {
            isAscending = !isAscending;
            if (isAscending) {
                tvSortOrder.setText("Tăng dần (giá)");
                Toast.makeText(requireContext(), "Sắp xếp giá tăng dần", Toast.LENGTH_SHORT).show();
            } else {
                tvSortOrder.setText("Giảm dần (giá)");
                Toast.makeText(requireContext(), "Sắp xếp giá giảm dần", Toast.LENGTH_SHORT).show();
            }
            filter(etSearchQuery.getText().toString());
        });

        setupChipListeners(view);
    }

    private void setupChipListeners(View view) {
        TextView chip1 = view.findViewById(R.id.chip1);
        TextView chip2 = view.findViewById(R.id.chip2);
        TextView chip3 = view.findViewById(R.id.chip3);
        TextView chip4 = view.findViewById(R.id.chip4);
        TextView chip5 = view.findViewById(R.id.chip5);

        TextView[] chips = new TextView[]{chip1, chip2, chip3, chip4, chip5};
        for (TextView chip : chips) {
            if (chip != null) {
                chip.setOnClickListener(v -> {
                    String text = chip.getText().toString();
                    if (text.equalsIgnoreCase(activeChipFilter)) {
                        activeChipFilter = ""; // toggle off
                        chip.setBackgroundResource(R.drawable.bg_chip_normal);
                        chip.setTextColor(getResources().getColor(R.color.neutral_700, requireActivity().getTheme()));
                    } else {
                        activeChipFilter = text;
                        for (TextView c : chips) {
                            if (c != null) {
                                c.setBackgroundResource(R.drawable.bg_chip_normal);
                                c.setTextColor(getResources().getColor(R.color.neutral_700, requireActivity().getTheme()));
                            }
                        }
                        chip.setBackgroundResource(R.drawable.bg_chip_selected);
                        chip.setTextColor(getResources().getColor(R.color.orange_500, requireActivity().getTheme()));
                    }
                    filter(etSearchQuery.getText().toString());
                });
            }
        }
    }

    private void filter(String query) {
        filteredList.clear();
        String lowerQuery = (query != null) ? query.toLowerCase(Locale.getDefault()).trim() : "";

        for (Restaurant item : fullList) {
            // Text search match
            boolean matchesQuery = lowerQuery.isEmpty() ||
                    (item.getName() != null && item.getName().toLowerCase(Locale.getDefault()).contains(lowerQuery)) ||
                    (item.getAddress() != null && item.getAddress().toLowerCase(Locale.getDefault()).contains(lowerQuery)) ||
                    (item.getTag() != null && item.getTag().toLowerCase(Locale.getDefault()).contains(lowerQuery));

            if (!matchesQuery) continue;

            // Chip filter match
            boolean matchesChip = true;
            if (!activeChipFilter.isEmpty()) {
                String chipLower = activeChipFilter.toLowerCase(Locale.getDefault());
                if (chipLower.contains("giá tốt")) {
                    matchesChip = item.getPriceRangeMin() <= 35000;
                } else if (chipLower.contains("đánh giá cao")) {
                    matchesChip = item.getRatingAvg() >= 4.6;
                } else if (chipLower.contains("gần tôi") || chipLower.contains("1 km")) {
                    matchesChip = item.getDistance() != null && (item.getDistance().contains("m") || item.getDistance().contains("1.0"));
                } else if (chipLower.contains("đang mở")) {
                    matchesChip = !"CLOSED".equalsIgnoreCase(item.getStatus());
                } else {
                    matchesChip = (item.getTag() != null && item.getTag().toLowerCase(Locale.getDefault()).contains(chipLower))
                            || (item.getName() != null && item.getName().toLowerCase(Locale.getDefault()).contains(chipLower));
                }
            }

            if (matchesChip) {
                filteredList.add(item);
            }
        }

        sortList();
        updateResultCount();
    }

    private void sortList() {
        Collections.sort(filteredList, new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant o1, Restaurant o2) {
                if (isAscending) {
                    return Double.compare(o1.getPriceRangeMin(), o2.getPriceRangeMin());
                } else {
                    return Double.compare(o2.getPriceRangeMin(), o1.getPriceRangeMin());
                }
            }
        });
        adapter.notifyDataSetChanged();
    }

    private void updateResultCount() {
        tvResultCount.setText(String.format(Locale.getDefault(), "%d quán phù hợp với bạn", filteredList.size()));
    }
}
