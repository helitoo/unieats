package com.example.unieats.activities.profiles;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unieats.R;
import com.example.unieats.adapters.MyRestaurantAdapter;
import com.example.unieats.entities.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class MyRestaurantActivity extends AppCompatActivity implements MyRestaurantAdapter.OnRestaurantActionListener {

    private ImageView tvBack;
    private RecyclerView rvMyRestaurants;
    private List<Restaurant> myRestaurantList;
    private MyRestaurantAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_restaurants);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupList();
        setupListeners();
    }

    private void initViews() {
        tvBack = findViewById(R.id.tvBack);
        rvMyRestaurants = findViewById(R.id.rvMyRestaurants);
        rvMyRestaurants.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupList() {
        myRestaurantList = new ArrayList<>();
        myRestaurantList.add(new Restaurant("Cơm nhà Góc Sân Trường", "Khu Phố 6, Linh Trung, Thủ Đức", 4.8, 128, 25000, 45000, "500 m", "Giá sinh viên"));
        myRestaurantList.add(new Restaurant("Bún bò Cô Ba", "Làng Đại Học, Thủ Đức", 4.7, 96, 30000, 45000, "800 m", "Được yêu thích"));
        myRestaurantList.add(new Restaurant("Trà sữa Gong Cha", "Đường Võ Văn Ngân, Thủ Đức", 4.5, 210, 35000, 60000, "1.2 km", "Giảm 20%"));

        adapter = new MyRestaurantAdapter(myRestaurantList, this);
        rvMyRestaurants.setAdapter(adapter);
    }

    private void setupListeners() {
        tvBack.setOnClickListener(v -> finish());
    }

    @Override
    public void onEdit(Restaurant restaurant, int position) {
        showEditDialog(restaurant, position);
    }

    @Override
    public void onDelete(Restaurant restaurant, int position) {
        showDeleteConfirmationDialog(restaurant, position);
    }

    private void showEditDialog(Restaurant restaurant, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_edit_restaurant, null);
        builder.setView(view);

        AlertDialog dialog = builder.create();

        EditText etEditName = view.findViewById(R.id.etEditName);
        EditText etEditAddress = view.findViewById(R.id.etEditAddress);
        EditText etEditPriceMin = view.findViewById(R.id.etEditPriceMin);
        EditText etEditPriceMax = view.findViewById(R.id.etEditPriceMax);
        EditText etEditTag = view.findViewById(R.id.etEditTag);

        Button btnCancelEdit = view.findViewById(R.id.btnCancelEdit);
        Button btnSaveEdit = view.findViewById(R.id.btnSaveEdit);

        // Pre-fill fields
        etEditName.setText(restaurant.getName());
        etEditAddress.setText(restaurant.getAddress());
        etEditPriceMin.setText(String.valueOf((long) restaurant.getPriceRangeMin()));
        etEditPriceMax.setText(String.valueOf((long) restaurant.getPriceRangeMax()));
        etEditTag.setText(restaurant.getTag());

        btnCancelEdit.setOnClickListener(v -> dialog.dismiss());

        btnSaveEdit.setOnClickListener(v -> {
            String name = etEditName.getText().toString().trim();
            String address = etEditAddress.getText().toString().trim();
            String minStr = etEditPriceMin.getText().toString().trim();
            String maxStr = etEditPriceMax.getText().toString().trim();
            String tag = etEditTag.getText().toString().trim();

            if (name.isEmpty()) {
                etEditName.setError("Vui lòng nhập tên quán");
                return;
            }

            double minPrice = minStr.isEmpty() ? restaurant.getPriceRangeMin() : Double.parseDouble(minStr);
            double maxPrice = maxStr.isEmpty() ? restaurant.getPriceRangeMax() : Double.parseDouble(maxStr);

            restaurant.setName(name);
            restaurant.setAddress(address);
            restaurant.setPriceRangeMin(minPrice);
            restaurant.setPriceRangeMax(maxPrice);
            restaurant.setTag(tag);

            adapter.notifyItemChanged(position);
            Toast.makeText(this, "Đã cập nhật thông tin quán ăn", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        dialog.show();
    }

    private void showDeleteConfirmationDialog(Restaurant restaurant, int position) {
        new AlertDialog.Builder(this)
                .setTitle("Xác nhận xóa")
                .setMessage(String.format("Bạn có chắc chắn muốn xóa \"%s\" không?", restaurant.getName()))
                .setPositiveButton("Xóa", (dialog, which) -> {
                    myRestaurantList.remove(position);
                    adapter.notifyItemRemoved(position);
                    adapter.notifyItemRangeChanged(position, myRestaurantList.size());
                    Toast.makeText(this, "Đã xóa quán ăn", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
