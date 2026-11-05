# UniEats

## Hệ Thống Icon (Vector Drawable)

Toàn bộ icon trong ứng dụng được lưu trữ tại thư mục `res/drawable/` dưới định dạng Vector Drawable XML:

- **Nguồn icon**:
  - **Heroicons** (https://heroicons.com): Sử dụng cho các icon hệ thống, điều hướng, nút bấm và đánh giá (`ic_home`, `ic_search`, `ic_star_filled`, `ic_arrow_left`, `ic_bookmark`,...).
  - **Lucide Icons** (https://lucide.dev): Sử dụng cho các icon danh mục món ăn (`ic_category_rice`, `ic_category_noodle`, `ic_category_snack`, `ic_category_coffee`, `ic_category_bbq`).

- **Cách thêm icon vào dự án**:
  1. Trong Android Studio, nhấp chuột phải vào thư mục `res/drawable` -> chọn **New** -> **Vector Asset**.
  2. Tại mục **Asset Type**, chọn **Local file (SVG, PSD)** và trỏ đến file SVG cần dùng.
  3. Đặt tên tài nguyên (ví dụ: `ic_star_filled`) rồi chọn **Next** -> **Finish**.
  4. File XML sẽ tự động sinh ra trong `res/drawable/` và có thể sử dụng trực tiếp qua `android:src="@drawable/..."` hoặc `app:drawableStartCompat="@drawable/..."`.
