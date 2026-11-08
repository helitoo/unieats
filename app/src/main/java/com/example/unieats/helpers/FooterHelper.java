package com.example.unieats.helpers;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.unieats.R;
import com.example.unieats.activities.MainActivity;

public class FooterHelper {

    public enum Tab {
        HOME, SEARCH, TRENDING, PROFILE
    }

    public static void setupFooter(Activity activity, Tab activeTab) {
        highlightTab(activity, activeTab);

        LinearLayout llTabHome = activity.findViewById(R.id.llTabHome);
        LinearLayout llTabSearch = activity.findViewById(R.id.llTabSearch);
        LinearLayout llTabTrending = activity.findViewById(R.id.llTabTrending);
        LinearLayout llTabProfile = activity.findViewById(R.id.llTabProfile);

        if (llTabHome == null) return;

        llTabHome.setOnClickListener(v -> navigateToTab(activity, Tab.HOME));
        llTabSearch.setOnClickListener(v -> navigateToTab(activity, Tab.SEARCH));
        llTabTrending.setOnClickListener(v -> navigateToTab(activity, Tab.TRENDING));
        llTabProfile.setOnClickListener(v -> navigateToTab(activity, Tab.PROFILE));
    }

    public static void highlightTab(Activity activity, Tab activeTab) {
        LinearLayout llTabHome = activity.findViewById(R.id.llTabHome);
        LinearLayout llTabSearch = activity.findViewById(R.id.llTabSearch);
        LinearLayout llTabTrending = activity.findViewById(R.id.llTabTrending);
        LinearLayout llTabProfile = activity.findViewById(R.id.llTabProfile);

        TextView tvHomeText = activity.findViewById(R.id.tvHomeText);
        TextView tvSearchText = activity.findViewById(R.id.tvSearchText);
        TextView tvTrendingText = activity.findViewById(R.id.tvTrendingText);
        TextView tvProfileText = activity.findViewById(R.id.tvProfileText);

        if (llTabHome == null) return;

        // Reset backgrounds
        llTabHome.setBackgroundResource(0);
        llTabSearch.setBackgroundResource(0);
        llTabTrending.setBackgroundResource(0);
        llTabProfile.setBackgroundResource(0);

        int activeColor = ContextCompat.getColor(activity, R.color.orange_500);
        int inactiveColor = Color.parseColor("#64748B");

        ImageView ivHomeIcon = activity.findViewById(R.id.ivHomeIcon);
        ImageView ivSearchIcon = activity.findViewById(R.id.ivSearchIcon);
        ImageView ivTrendingIcon = activity.findViewById(R.id.ivTrendingIcon);
        ImageView ivProfileIcon = activity.findViewById(R.id.ivProfileIcon);

        if (tvHomeText != null) tvHomeText.setTextColor(inactiveColor);
        if (tvSearchText != null) tvSearchText.setTextColor(inactiveColor);
        if (tvTrendingText != null) tvTrendingText.setTextColor(inactiveColor);
        if (tvProfileText != null) tvProfileText.setTextColor(inactiveColor);

        if (ivHomeIcon != null) ivHomeIcon.setColorFilter(inactiveColor);
        if (ivSearchIcon != null) ivSearchIcon.setColorFilter(inactiveColor);
        if (ivTrendingIcon != null) ivTrendingIcon.setColorFilter(inactiveColor);
        if (ivProfileIcon != null) ivProfileIcon.setColorFilter(inactiveColor);

        // Highlight active tab
        switch (activeTab) {
            case HOME:
                llTabHome.setBackgroundResource(R.drawable.bg_footer_active_tab);
                if (tvHomeText != null) tvHomeText.setTextColor(activeColor);
                if (ivHomeIcon != null) ivHomeIcon.setColorFilter(activeColor);
                break;
            case SEARCH:
                llTabSearch.setBackgroundResource(R.drawable.bg_footer_active_tab);
                if (tvSearchText != null) tvSearchText.setTextColor(activeColor);
                if (ivSearchIcon != null) ivSearchIcon.setColorFilter(activeColor);
                break;
            case TRENDING:
                llTabTrending.setBackgroundResource(R.drawable.bg_footer_active_tab);
                if (tvTrendingText != null) tvTrendingText.setTextColor(activeColor);
                if (ivTrendingIcon != null) ivTrendingIcon.setColorFilter(activeColor);
                break;
            case PROFILE:
                llTabProfile.setBackgroundResource(R.drawable.bg_footer_active_tab);
                if (tvProfileText != null) tvProfileText.setTextColor(activeColor);
                if (ivProfileIcon != null) ivProfileIcon.setColorFilter(activeColor);
                break;
        }
    }

    private static void navigateToTab(Activity activity, Tab targetTab) {
        if (activity instanceof MainActivity) {
            ((MainActivity) activity).switchTab(targetTab);
        } else {
            Intent intent = new Intent(activity, MainActivity.class);
            intent.putExtra("EXTRA_TAB", targetTab.name());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            activity.startActivity(intent);
            activity.finish();
        }
    }
}
