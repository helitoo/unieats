package com.example.unieats.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unieats.R;
import com.example.unieats.entities.Restaurant;

import java.text.DecimalFormat;
import java.util.List;

public class ManageRestaurantAdapter extends RecyclerView.Adapter<ManageRestaurantAdapter.ViewHolder> {

    public interface OnStatusChangeListener {
        void onStatusChanged();
    }

    private final List<Restaurant> restaurantList;
    private OnStatusChangeListener statusChangeListener;

    public ManageRestaurantAdapter(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public ManageRestaurantAdapter(List<Restaurant> restaurantList, OnStatusChangeListener listener) {
        this.restaurantList = restaurantList;
        this.statusChangeListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant_manage, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant item = restaurantList.get(position);

        holder.tvRestaurantName.setText(item.getName() != null ? item.getName() : "Tên quán");
        holder.tvRestaurantInfo.setText(item.getAddress() != null ? item.getAddress() : "Chưa có địa chỉ");

        holder.tvDetailAddress.setText(String.format("Địa chỉ: %s", item.getAddress() != null ? item.getAddress() : "Chưa có"));
        
        String opening = item.getOpeningTime() != null ? item.getOpeningTime() : "06:00";
        String closing = item.getClosingTime() != null ? item.getClosingTime() : "21:00";
        holder.tvDetailHours.setText(String.format("Giờ mở cửa: %s – %s", opening, closing));

        DecimalFormat df = new DecimalFormat("#,###");
        String priceText = String.format("Khoảng giá: %s – %s đ",
                df.format(item.getPriceRangeMin() > 0 ? item.getPriceRangeMin() : 20000),
                df.format(item.getPriceRangeMax() > 0 ? item.getPriceRangeMax() : 50000));
        holder.tvDetailPrice.setText(priceText);

        // Status styling
        String status = item.getStatus() != null ? item.getStatus().toUpperCase() : "PENDING";
        String statusDisplayName;
        switch (status) {
            case "APPROVED":
                statusDisplayName = "Đã duyệt";
                holder.tvStatusBadge.setTextColor(Color.parseColor("#16A34A"));
                break;
            case "REJECTED":
                statusDisplayName = "Đã từ chối";
                holder.tvStatusBadge.setTextColor(Color.parseColor("#DC2626"));
                break;
            case "CLOSED":
                statusDisplayName = "Đã đóng cửa";
                holder.tvStatusBadge.setTextColor(Color.parseColor("#737373"));
                break;
            case "PENDING":
            default:
                statusDisplayName = "Đang chờ";
                holder.tvStatusBadge.setTextColor(Color.parseColor("#D97706"));
                break;
        }
        holder.tvStatusBadge.setText(statusDisplayName);

        // Expand/Collapse state
        if (item.isExpanded()) {
            holder.llExpandableDetail.setVisibility(View.VISIBLE);
            holder.ivChevron.setImageResource(R.drawable.ic_chevron_down);
        } else {
            holder.llExpandableDetail.setVisibility(View.GONE);
            holder.ivChevron.setImageResource(R.drawable.ic_chevron_right);
        }

        // Listeners
        holder.llHeader.setOnClickListener(v -> {
            int pos = holder.getBindingAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                item.setExpanded(!item.isExpanded());
                notifyItemChanged(pos);
            }
        });

        holder.btnApprove.setOnClickListener(v -> {
            int pos = holder.getBindingAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                item.setStatus("APPROVED");
                notifyItemChanged(pos);
                if (statusChangeListener != null) statusChangeListener.onStatusChanged();
            }
        });

        holder.btnReject.setOnClickListener(v -> {
            int pos = holder.getBindingAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                item.setStatus("REJECTED");
                notifyItemChanged(pos);
                if (statusChangeListener != null) statusChangeListener.onStatusChanged();
            }
        });

        holder.btnClose.setOnClickListener(v -> {
            int pos = holder.getBindingAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                item.setStatus("CLOSED");
                notifyItemChanged(pos);
                if (statusChangeListener != null) statusChangeListener.onStatusChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantList != null ? restaurantList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llHeader, llExpandableDetail;
        TextView tvRestaurantName, tvRestaurantInfo, tvStatusBadge;
        TextView tvDetailAddress, tvDetailHours, tvDetailPrice;
        ImageView ivChevron;
        Button btnApprove, btnReject, btnClose;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            llHeader = itemView.findViewById(R.id.llHeader);
            llExpandableDetail = itemView.findViewById(R.id.llExpandableDetail);
            tvRestaurantName = itemView.findViewById(R.id.tvRestaurantName);
            tvRestaurantInfo = itemView.findViewById(R.id.tvRestaurantInfo);
            tvStatusBadge = itemView.findViewById(R.id.tvStatusBadge);
            tvDetailAddress = itemView.findViewById(R.id.tvDetailAddress);
            tvDetailHours = itemView.findViewById(R.id.tvDetailHours);
            tvDetailPrice = itemView.findViewById(R.id.tvDetailPrice);
            ivChevron = itemView.findViewById(R.id.ivChevron);
            btnApprove = itemView.findViewById(R.id.btnApprove);
            btnReject = itemView.findViewById(R.id.btnReject);
            btnClose = itemView.findViewById(R.id.btnClose);
        }
    }
}
