package com.example.unieats.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.unieats.R;
import com.example.unieats.activities.auth.SigninActivity;
import com.example.unieats.fragments.HomeFragment;
import com.example.unieats.fragments.ProfileFragment;
import com.example.unieats.fragments.SearchFragment;
import com.example.unieats.fragments.TrendingFragment;
import com.example.unieats.helpers.AuthManager;
import com.example.unieats.helpers.FooterHelper;

public class MainActivity extends AppCompatActivity {

    private FooterHelper.Tab currentTab = FooterHelper.Tab.HOME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check authentication state on launch
        if (!AuthManager.isLoggedIn(this)) {
            Intent intent = new Intent(this, SigninActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FooterHelper.setupFooter(this, currentTab);

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (intent == null) {
            switchTab(FooterHelper.Tab.HOME);
            return;
        }

        String tabExtra = intent.getStringExtra("EXTRA_TAB");
        String queryExtra = intent.getStringExtra("EXTRA_SEARCH_QUERY");

        if (tabExtra != null) {
            try {
                FooterHelper.Tab tab = FooterHelper.Tab.valueOf(tabExtra);
                switchTab(tab, queryExtra);
            } catch (Exception e) {
                switchTab(FooterHelper.Tab.HOME);
            }
        } else if (queryExtra != null) {
            switchTab(FooterHelper.Tab.SEARCH, queryExtra);
        } else if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) == null) {
            switchTab(FooterHelper.Tab.HOME);
        }
    }

    public void switchTab(FooterHelper.Tab tab) {
        switchTab(tab, null);
    }

    public void switchTab(FooterHelper.Tab tab, String query) {
        currentTab = tab;
        FooterHelper.highlightTab(this, tab);

        Fragment fragment;
        switch (tab) {
            case SEARCH:
                fragment = SearchFragment.newInstance(query);
                break;
            case TRENDING:
                fragment = new TrendingFragment();
                break;
            case PROFILE:
                fragment = new ProfileFragment();
                break;
            case HOME:
            default:
                fragment = new HomeFragment();
                break;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
