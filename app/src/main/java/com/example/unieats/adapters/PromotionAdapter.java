package com.example.unieats.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unieats.R;
import com.example.unieats.entities.Promotion;

import java.util.List;

public class PromotionAdapter extends RecyclerView.Adapter<PromotionAdapter.ViewHolder> {

    private final List<Promotion> promotionList;

    public PromotionAdapter(List<Promotion> promotionList) {
        this.promotionList = promotionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_promotion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Promotion item = promotionList.get(position);
        holder.tvPromoTitle.setText(item.getTitle());
        holder.tvPromoDescription.setText(item.getDescription());

        String dateText = String.format("Hạn: %s - %s",
                item.getStartDate() != null ? item.getStartDate() : "",
                item.getEndDate() != null ? item.getEndDate() : "");
        holder.tvPromoDate.setText(dateText);

        if (item.isActive()) {
            holder.tvPromoStatus.setText("Hoạt động");
            holder.tvPromoStatus.setVisibility(View.VISIBLE);
        } else {
            holder.tvPromoStatus.setText("Hết hạn");
            holder.tvPromoStatus.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return promotionList != null ? promotionList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPromoTitle, tvPromoDescription, tvPromoDate, tvPromoStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPromoTitle = itemView.findViewById(R.id.tvPromoTitle);
            tvPromoDescription = itemView.findViewById(R.id.tvPromoDescription);
            tvPromoDate = itemView.findViewById(R.id.tvPromoDate);
            tvPromoStatus = itemView.findViewById(R.id.tvPromoStatus);
        }
    }
}
