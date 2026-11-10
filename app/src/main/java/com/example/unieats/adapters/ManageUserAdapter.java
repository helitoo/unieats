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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unieats.R;
import com.example.unieats.entities.User;

import java.util.List;

public class ManageUserAdapter extends RecyclerView.Adapter<ManageUserAdapter.ViewHolder> {

    private final List<User> userList;

    public ManageUserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_manage, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userList.get(position);

        holder.tvUserName.setText(user.getName() != null ? user.getName() : "Người dùng");
        
        String rawRole = user.getRole() != null ? user.getRole() : "User";
        String roleStr = ("ADMIN".equalsIgnoreCase(rawRole) || "ADMINISTRATOR".equalsIgnoreCase(rawRole)) ? "Quản trị viên" : "Người dùng";

        String rawStatus = user.getStatus() != null ? user.getStatus() : "Đang hoạt động";
        String statusStr = ("DISABLED".equalsIgnoreCase(rawStatus) || "INACTIVE".equalsIgnoreCase(rawStatus) || "Tạm dừng".equalsIgnoreCase(rawStatus)) ? "Tạm dừng" : "Đang hoạt động";

        holder.tvUserRoleStatus.setText(String.format("%s · %s", roleStr, statusStr));

        holder.tvUserEmail.setText(String.format("Email: %s", user.getEmail() != null ? user.getEmail() : "N/A"));
        holder.tvUserPhone.setText(String.format("Số điện thoại: %s", user.getPhone() != null ? user.getPhone() : "N/A"));
        holder.tvUserRoleDetail.setText(String.format("Vai trò: %s", roleStr));

        // Update active/inactive state UI
        if (user.isActive()) {
            holder.tvStatusBadge.setText("Đang hoạt động");
            holder.tvStatusBadge.setTextColor(Color.parseColor("#16A34A"));
            holder.btnToggleStatus.setText("Tạm dừng");
            holder.btnToggleStatus.setBackgroundResource(R.drawable.bg_button_orange);
            holder.btnToggleStatus.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.neutral_0));
        } else {
            holder.tvStatusBadge.setText("Tạm dừng");
            holder.tvStatusBadge.setTextColor(Color.parseColor("#DC2626"));
            holder.btnToggleStatus.setText("Kích hoạt");
            holder.btnToggleStatus.setBackgroundResource(R.drawable.bg_button_google);
            holder.btnToggleStatus.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.orange_500));
        }

        // Expandable visibility
        if (user.isExpanded()) {
            holder.llExpandableDetail.setVisibility(View.VISIBLE);
            holder.ivChevron.setImageResource(R.drawable.ic_chevron_down);
        } else {
            holder.llExpandableDetail.setVisibility(View.GONE);
            holder.ivChevron.setImageResource(R.drawable.ic_chevron_right);
        }

        // Toggle Expand
        holder.llHeader.setOnClickListener(v -> {
            int pos = holder.getBindingAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                user.setExpanded(!user.isExpanded());
                notifyItemChanged(pos);
            }
        });

        // Toggle Status
        holder.btnToggleStatus.setOnClickListener(v -> {
            int pos = holder.getBindingAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                user.setActive(!user.isActive());
                notifyItemChanged(pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList != null ? userList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llHeader, llExpandableDetail;
        TextView tvUserName, tvUserRoleStatus, tvStatusBadge;
        TextView tvUserEmail, tvUserPhone, tvUserRoleDetail;
        ImageView ivChevron;
        Button btnToggleStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            llHeader = itemView.findViewById(R.id.llHeader);
            llExpandableDetail = itemView.findViewById(R.id.llExpandableDetail);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvUserRoleStatus = itemView.findViewById(R.id.tvUserRoleStatus);
            tvStatusBadge = itemView.findViewById(R.id.tvStatusBadge);
            tvUserEmail = itemView.findViewById(R.id.tvUserEmail);
            tvUserPhone = itemView.findViewById(R.id.tvUserPhone);
            tvUserRoleDetail = itemView.findViewById(R.id.tvUserRoleDetail);
            ivChevron = itemView.findViewById(R.id.ivChevron);
            btnToggleStatus = itemView.findViewById(R.id.btnToggleStatus);
        }
    }
}
