package com.example.unieats.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unieats.R;

import java.util.List;

public class RestaurantImageAdapter extends RecyclerView.Adapter<RestaurantImageAdapter.ViewHolder> {

    private final List<String> photoList;

    public RestaurantImageAdapter(List<String> photoList) {
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant_photo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String photo = photoList.get(position);
        if (holder.ivRestaurantPhoto != null) {
            holder.ivRestaurantPhoto.setImageResource(R.mipmap.ic_launcher);
        }
    }

    @Override
    public int getItemCount() {
        return photoList != null ? photoList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivRestaurantPhoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivRestaurantPhoto = itemView.findViewById(R.id.ivRestaurantPhoto);
        }
    }
}
