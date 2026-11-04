package com.example.unieats.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unieats.R;
import com.example.unieats.activities.restaurants.WriteReviewActivity;
import com.example.unieats.entities.Review;

import java.util.List;
import java.util.Locale;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private final List<Review> reviewList;

    public ReviewAdapter(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Review item = reviewList.get(position);
        holder.tvUserName.setText(item.getUserName() != null ? item.getUserName() : "Người dùng");
        
        String formattedComment = String.format("“%s”", item.getComment() != null ? item.getComment() : "");
        holder.tvComment.setText(formattedComment);

        // Like button handling
        final int[] likes = {item.getLikeCount()};
        final boolean[] isLiked = {false};
        holder.tvLike.setText(String.format(Locale.getDefault(), "👍 %d", likes[0]));

        holder.tvLike.setOnClickListener(v -> {
            if (!isLiked[0]) {
                likes[0]++;
                isLiked[0] = true;
                holder.tvLike.setBackgroundResource(R.drawable.bg_chip_selected);
                holder.tvLike.setTextColor(v.getContext().getResources().getColor(R.color.orange_500, v.getContext().getTheme()));
            } else {
                likes[0]--;
                isLiked[0] = false;
                holder.tvLike.setBackgroundResource(R.drawable.bg_chip_normal);
                holder.tvLike.setTextColor(v.getContext().getResources().getColor(R.color.neutral_700, v.getContext().getTheme()));
            }
            item.setLikeCount(likes[0]);
            holder.tvLike.setText(String.format(Locale.getDefault(), "👍 %d", likes[0]));
        });

        // Review photos handling
        if (item.getPhotos() != null && !item.getPhotos().isEmpty()) {
            holder.rvReviewPhotos.setVisibility(View.VISIBLE);
            holder.rvReviewPhotos.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            ReviewPhotoAdapter photoAdapter = new ReviewPhotoAdapter(item.getPhotos());
            holder.rvReviewPhotos.setAdapter(photoAdapter);
        } else {
            holder.rvReviewPhotos.setVisibility(View.GONE);
        }

        // Author Actions (Edit / Delete) - Shown if user is author of review
        if (item.isAuthor() && holder.llAuthorActions != null) {
            holder.llAuthorActions.setVisibility(View.VISIBLE);

            if (holder.btnEditReview != null) {
                holder.btnEditReview.setOnClickListener(v -> {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, WriteReviewActivity.class);
                    intent.putExtra("EXTRA_RESTAURANT_NAME", item.getUserName());
                    intent.putExtra("EXTRA_COMMENT", item.getComment());
                    context.startActivity(intent);
                });
            }

            if (holder.btnDeleteReview != null) {
                holder.btnDeleteReview.setOnClickListener(v -> {
                    int currentPos = holder.getBindingAdapterPosition();
                    if (currentPos != RecyclerView.NO_POSITION && currentPos < reviewList.size()) {
                        reviewList.remove(currentPos);
                        notifyItemRemoved(currentPos);
                        notifyItemRangeChanged(currentPos, reviewList.size());
                        Toast.makeText(v.getContext(), "Đã xóa đánh giá của bạn!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } else if (holder.llAuthorActions != null) {
            holder.llAuthorActions.setVisibility(View.GONE);
        }

        // Reason & Admin Actions
        if (item.getReason() != null && !item.getReason().isEmpty()) {
            holder.tvReason.setVisibility(View.VISIBLE);
            holder.tvReason.setText(String.format("Lý do: %s", item.getReason()));
            if (holder.llAdminActions != null) {
                holder.llAdminActions.setVisibility(View.VISIBLE);
            }
        } else {
            holder.tvReason.setVisibility(View.GONE);
            if (holder.llAdminActions != null) {
                holder.llAdminActions.setVisibility(View.GONE);
            }
        }

        if (holder.btnHide != null) {
            holder.btnHide.setOnClickListener(v -> 
                Toast.makeText(v.getContext(), "Đã ẩn nội dung", Toast.LENGTH_SHORT).show()
            );
        }

        if (holder.btnWarn != null) {
            holder.btnWarn.setOnClickListener(v -> 
                Toast.makeText(v.getContext(), "Đã gửi cảnh báo / xóa", Toast.LENGTH_SHORT).show()
            );
        }
    }

    @Override
    public int getItemCount() {
        return reviewList != null ? reviewList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserName, tvComment, tvReason, tvLike;
        RecyclerView rvReviewPhotos;
        View llAdminActions, llAuthorActions;
        Button btnHide, btnWarn, btnEditReview, btnDeleteReview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvComment = itemView.findViewById(R.id.tvComment);
            tvReason = itemView.findViewById(R.id.tvReason);
            tvLike = itemView.findViewById(R.id.tvLike);
            rvReviewPhotos = itemView.findViewById(R.id.rvReviewPhotos);
            llAdminActions = itemView.findViewById(R.id.llAdminActions);
            btnHide = itemView.findViewById(R.id.btnHide);
            btnWarn = itemView.findViewById(R.id.btnWarn);

            llAuthorActions = itemView.findViewById(R.id.llAuthorActions);
            btnEditReview = itemView.findViewById(R.id.btnEditReview);
            btnDeleteReview = itemView.findViewById(R.id.btnDeleteReview);
        }
    }
}
