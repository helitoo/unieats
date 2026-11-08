package com.example.unieats.helpers;

import android.content.Context;
import android.net.Uri;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;

import java.util.HashMap;
import java.util.Map;

public class CloudinaryManager {

    private static boolean isInitialized = false;

    public static final String CLOUD_NAME = "yugz9lo6";
    public static final String API_KEY = "216551858511721";
    public static final String API_SECRET = "PK4Uw6Lmx5A7JptHZ7yobRVYT00";
    public static final String UPLOAD_PRESET = "ml_default";

    public interface CloudinaryUploadListener {
        void onSuccess(String imageUrl);
        void onError(String error);
    }

    public static synchronized void init(Context context) {
        if (!isInitialized) {
            Map<String, String> config = new HashMap<>();
            config.put("cloud_name", CLOUD_NAME);
            config.put("api_key", API_KEY);
            config.put("api_secret", API_SECRET);

            try {
                MediaManager.init(context.getApplicationContext(), config);
                isInitialized = true;
            } catch (Exception e) {
                isInitialized = true;
            }
        }
    }

    /**
     * Upload an image from local Uri to Cloudinary
     * @param context Context
     * @param imageUri Uri of the image file
     * @param folder Folder name on Cloudinary (e.g. "users", "restaurants", "reviews")
     * @param listener Callback for result
     */
    public static void uploadImage(Context context, Uri imageUri, String folder, CloudinaryUploadListener listener) {
        init(context);

        MediaManager.get().upload(imageUri)
                .unsigned(UPLOAD_PRESET)
                .option("folder", "unieats/" + (folder != null ? folder : "general"))
                .callback(new UploadCallback() {
                    @Override
                    public void onStart(String requestId) {
                    }

                    @Override
                    public void onProgress(String requestId, long bytes, long totalBytes) {
                    }

                    @Override
                    public void onSuccess(String requestId, Map resultData) {
                        String secureUrl = (String) resultData.get("secure_url");
                        if (listener != null) {
                            listener.onSuccess(secureUrl);
                        }
                    }

                    @Override
                    public void onError(String requestId, ErrorInfo error) {
                        if (listener != null) {
                            listener.onError(error.getDescription());
                        }
                    }

                    @Override
                    public void onReschedule(String requestId, ErrorInfo error) {
                        if (listener != null) {
                            listener.onError("Upload rescheduled: " + error.getDescription());
                        }
                    }
                })
                .dispatch();
    }
}
