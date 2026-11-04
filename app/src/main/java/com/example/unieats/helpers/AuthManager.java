package com.example.unieats.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.unieats.activities.auth.SigninActivity;

public class AuthManager {

    private static final String PREF_NAME = "unieats_auth_pref";
    private static final String KEY_IS_LOGGED_IN = "key_is_logged_in";

    public static boolean isLoggedIn(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public static void setLoggedIn(Context context, boolean loggedIn) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        pref.edit().putBoolean(KEY_IS_LOGGED_IN, loggedIn).apply();
    }

    public static void logout(Activity activity) {
        setLoggedIn(activity, false);
        Intent intent = new Intent(activity, SigninActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
        activity.finish();
    }
}
