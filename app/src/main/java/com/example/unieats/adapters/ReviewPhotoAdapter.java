package com.example.unieats.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unieats.R;
import com.example.unieats.entities.ReviewPhoto;

import java.util.List;

public class ReviewPhotoAdapter extends RecyclerView.Adapter<ReviewPhotoAdapter.ViewHolder> {

    private final List<ReviewPhoto> photoList;

    public ReviewPhotoAdapter(List<ReviewPhoto> photoList) {
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review_photo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReviewPhoto item = photoList.get(position);
        // Photo binding placeholder / URL loading
    }

    @Override
    public int getItemCount() {
        return photoList != null ? photoList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivReviewPhoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivReviewPhoto = itemView.findViewById(R.id.ivReviewPhoto);
        }
    }
}
