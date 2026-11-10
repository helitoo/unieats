package com.example.unieats.helpers;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;

import com.example.unieats.R;

/**
 * Helper for loading local drawable resources by name into ImageViews.
 */
public final class ImageHelper {

    private ImageHelper() {
    }

    public static void loadDrawable(ImageView imageView, String imageName) {
        loadDrawable(imageView, imageName, R.mipmap.ic_launcher);
    }

    public static void loadDrawable(ImageView imageView, String imageName, @DrawableRes int fallbackResId) {
        if (imageView == null) {
            return;
        }
        if (imageName == null || imageName.trim().isEmpty()) {
            imageView.setImageResource(fallbackResId);
            return;
        }

        String normalized = imageName.trim();
        int dotIndex = normalized.lastIndexOf('.');
        if (dotIndex > 0) {
            normalized = normalized.substring(0, dotIndex);
        }

        Context context = imageView.getContext();
        int resId = context.getResources().getIdentifier(normalized, "drawable", context.getPackageName());
        imageView.setImageResource(resId != 0 ? resId : fallbackResId);
    }
}
