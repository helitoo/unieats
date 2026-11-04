package com.example.unieats.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unieats.R;
import com.example.unieats.entities.User;

import java.util.List;

public class UserShortAdapter extends RecyclerView.Adapter<UserShortAdapter.ViewHolder> {

    private final List<User> userList;

    public UserShortAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_short, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User item = userList.get(position);
        holder.tvUserName.setText(item.getName());

        String roleStatus = String.format("%s · %s",
                item.getRole() != null ? item.getRole() : "User",
                item.getStatus() != null ? item.getStatus() : "Đang hoạt động");
        holder.tvUserRoleStatus.setText(roleStatus);
    }

    @Override
    public int getItemCount() {
        return userList != null ? userList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserName, tvUserRoleStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvUserRoleStatus = itemView.findViewById(R.id.tvUserRoleStatus);
        }
    }
}
