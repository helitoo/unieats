package com.example.unieats.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unieats.R;
import com.example.unieats.activities.restaurants.DetailRestaurantActivity;
import com.example.unieats.entities.Restaurant;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    public interface OnUnsaveListener {
        void onUnsave(Restaurant restaurant, int position);
    }

    private final List<Restaurant> restaurantList;
    private OnUnsaveListener unsaveListener;

    public RestaurantAdapter(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public RestaurantAdapter(List<Restaurant> restaurantList, OnUnsaveListener unsaveListener) {
        this.restaurantList = restaurantList;
        this.unsaveListener = unsaveListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant item = restaurantList.get(position);
        holder.tvRestaurantName.setText(item.getName());

        String ratingText = String.format(Locale.getDefault(), "%.1f", item.getRatingAvg());
        holder.tvRating.setText(ratingText);

        String reviewDistance = String.format(Locale.getDefault(), " (%d đánh giá)%s",
                item.getReviewCount(),
                item.getDistance() != null ? " · " + item.getDistance() : "");
        holder.tvReviewCountDistance.setText(reviewDistance);

        DecimalFormat df = new DecimalFormat("#,###");
        String priceText = String.format("%s – %s đ / người",
                df.format(item.getPriceRangeMin()),
                df.format(item.getPriceRangeMax()));
        holder.tvPriceRange.setText(priceText);

        if (item.getTag() != null && !item.getTag().isEmpty()) {
            holder.tvTag.setVisibility(View.VISIBLE);
            holder.tvTag.setText(item.getTag());
        } else {
            holder.tvTag.setVisibility(View.GONE);
        }

        if (unsaveListener != null && holder.btnUnsave != null) {
            holder.btnUnsave.setVisibility(View.VISIBLE);
            holder.btnUnsave.setOnClickListener(v -> {
                int pos = holder.getBindingAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && unsaveListener != null) {
                    unsaveListener.onUnsave(item, pos);
                }
            });
        } else if (holder.btnUnsave != null) {
            holder.btnUnsave.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, DetailRestaurantActivity.class);
            intent.putExtra("EXTRA_RESTAURANT_NAME", item.getName());
            intent.putExtra("EXTRA_RESTAURANT_ADDRESS", item.getAddress() != null ? item.getAddress() : "Làng Đại học");
            intent.putExtra("EXTRA_RESTAURANT_RATING", item.getRatingAvg());
            intent.putExtra("EXTRA_RESTAURANT_REVIEW_COUNT", item.getReviewCount());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return restaurantList != null ? restaurantList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvRestaurantName, tvRating, tvReviewCountDistance, tvPriceRange, tvTag;
        Button btnUnsave;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRestaurantName = itemView.findViewById(R.id.tvRestaurantName);
            tvRating = itemView.findViewById(R.id.tvRating);
            tvReviewCountDistance = itemView.findViewById(R.id.tvReviewCountDistance);
            tvPriceRange = itemView.findViewById(R.id.tvPriceRange);
            tvTag = itemView.findViewById(R.id.tvTag);
            btnUnsave = itemView.findViewById(R.id.btnUnsave);
        }
    }
}
