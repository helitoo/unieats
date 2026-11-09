package com.example.unieats.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unieats.R;
import com.example.unieats.entities.Restaurant;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

public class MyRestaurantAdapter extends RecyclerView.Adapter<MyRestaurantAdapter.ViewHolder> {

    public interface OnRestaurantActionListener {
        void onDelete(Restaurant restaurant, int position);
    }

    private final List<Restaurant> restaurantList;
    private final OnRestaurantActionListener actionListener;

    public MyRestaurantAdapter(List<Restaurant> restaurantList, OnRestaurantActionListener actionListener) {
        this.restaurantList = restaurantList;
        this.actionListener = actionListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_restaurant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant item = restaurantList.get(position);

        holder.tvRestaurantName.setText(item.getName());
        holder.tvRestaurantAddress.setText(item.getAddress());

        String ratingText = String.format(Locale.getDefault(), "%.1f", item.getRatingAvg());
        holder.tvRating.setText(ratingText);

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

        holder.btnDelete.setOnClickListener(v -> {
            int pos = holder.getBindingAdapterPosition();
            if (pos != RecyclerView.NO_POSITION && actionListener != null) {
                actionListener.onDelete(item, pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantList != null ? restaurantList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvRestaurantName, tvRestaurantAddress, tvTag, tvRating, tvPriceRange;
        Button btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRestaurantName = itemView.findViewById(R.id.tvRestaurantName);
            tvRestaurantAddress = itemView.findViewById(R.id.tvRestaurantAddress);
            tvTag = itemView.findViewById(R.id.tvTag);
            tvRating = itemView.findViewById(R.id.tvRating);
            tvPriceRange = itemView.findViewById(R.id.tvPriceRange);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
