package com.example.unieats.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unieats.R;
import com.example.unieats.activities.restaurants.DetailRestaurantActivity;
import com.example.unieats.entities.ReviewShort;

import java.util.List;

public class ReviewShortAdapter extends RecyclerView.Adapter<ReviewShortAdapter.ViewHolder> {

    private final List<ReviewShort> reviewShortList;

    public ReviewShortAdapter(List<ReviewShort> reviewShortList) {
        this.reviewShortList = reviewShortList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant_short, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReviewShort item = reviewShortList.get(position);
        holder.tvRestaurantName.setText(item.getRestaurantName());
        holder.tvRestaurantAddress.setText(item.getRestaurantAddress());

        holder.itemView.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, DetailRestaurantActivity.class);
            intent.putExtra("EXTRA_RESTAURANT_NAME", item.getRestaurantName());
            intent.putExtra("EXTRA_RESTAURANT_ADDRESS", item.getRestaurantAddress());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return reviewShortList != null ? reviewShortList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvRestaurantName, tvRestaurantAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRestaurantName = itemView.findViewById(R.id.tvRestaurantName);
            tvRestaurantAddress = itemView.findViewById(R.id.tvRestaurantAddress);
        }
    }
}
