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
import com.example.unieats.entities.Restaurant;

import java.util.List;

public class RestaurantShortAdapter extends RecyclerView.Adapter<RestaurantShortAdapter.ViewHolder> {

    private final List<Restaurant> restaurantList;

    public RestaurantShortAdapter(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant_short, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant item = restaurantList.get(position);
        holder.tvRestaurantName.setText(item.getName());
        holder.tvRestaurantAddress.setText(item.getAddress());

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
        TextView tvRestaurantName, tvRestaurantAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRestaurantName = itemView.findViewById(R.id.tvRestaurantName);
            tvRestaurantAddress = itemView.findViewById(R.id.tvRestaurantAddress);
        }
    }
}
