package com.example.unieats.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unieats.R;
import com.example.unieats.entities.Menu;

import java.text.DecimalFormat;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private final List<Menu> menuList;

    public MenuAdapter(List<Menu> menuList) {
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Menu item = menuList.get(position);
        holder.tvMenuName.setText(item.getName());
        holder.tvMenuDescription.setText(item.getDescription());

        DecimalFormat formatter = new DecimalFormat("#,###đ");
        holder.tvMenuPrice.setText(formatter.format(item.getPrice()));
        com.example.unieats.helpers.ImageHelper.loadDrawable(holder.ivMenuImage, item.getImageUrl());
    }

    @Override
    public int getItemCount() {
        return menuList != null ? menuList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMenuImage;
        TextView tvMenuName, tvMenuDescription, tvMenuPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivMenuImage = itemView.findViewById(R.id.ivMenuImage);
            tvMenuName = itemView.findViewById(R.id.tvMenuName);
            tvMenuDescription = itemView.findViewById(R.id.tvMenuDescription);
            tvMenuPrice = itemView.findViewById(R.id.tvMenuPrice);
        }
    }
}
