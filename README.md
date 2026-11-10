# UniEats - Ứng Dụng Khám Phá Ẩm Thực Sinh Viên

Đồ án môn học: **Lập trình Thiết bị Di động (Mobile Programming)**  
Nền tảng: **Android (Java)**

---

## 1. Giới thiệu dự án
**UniEats** là ứng dụng di động hỗ trợ sinh viên tìm kiếm, khám phá các địa điểm ăn uống ngon - bổ - rẻ xung quanh khu vực trường đại học. Ứng dụng cung cấp đánh giá khách quan, hình ảnh thực tế và thực đơn chi tiết từ cộng đồng sinh viên.

---

## 2. Các chức năng chính
- **Khám phá & Trang chủ:** Hiển thị quán ăn nổi bật, quán được đánh giá cao và phân loại theo danh mục ẩm thực (Cơm, Bún/Phở, Đồ ăn vặt, Cafe, BBQ/Nướng).
- **Tìm kiếm:** Tìm kiếm quán ăn nhanh chóng theo từ khóa, danh mục món ăn và khu vực.
- **Chi tiết quán ăn:** Xem thông tin địa chỉ, giờ mở cửa, mức giá, thực đơn và các chương trình ưu đãi.
- **Đánh giá & Bình luận:** Đọc phản hồi thực tế và chia sẻ trải nghiệm, chấm điểm sao cho quán ăn.
- **Tài khoản cá nhân:** Quản lý thông tin cá nhân, xem lịch sử ăn uống và danh sách quán ăn đã lưu.
- **Đóng góp quán mới:** Cho phép người dùng gửi thông tin quán ăn mới vào hệ thống.

---

## 3. Công nghệ và môi trường phát triển
- **Ngôn ngữ:** Java
- **IDE:** Android Studio
- **Min SDK:** Android 7.0 (API 24) / **Target SDK:** Android 14 (API 34)
- **Kiến trúc & Thư viện:** Android Jetpack, RecyclerView, CardView, ConstraintLayout.

---

## 4. Tài nguyên giao diện (Vector Drawable)
Toàn bộ icon trong giao diện ứng dụng được sử dụng dưới định dạng **Vector Drawable (XML)** đặt trong thư mục `app/src/main/res/drawable/` để đảm bảo hiển thị sắc nét trên mọi độ phân giải màn hình:

- **Nguồn icon SVG:**
  - **Heroicons** (https://heroicons.com): Sử dụng cho các icon hệ thống, điều hướng, tìm kiếm và nút bấm (`ic_home`, `ic_search`, `ic_star_filled`, `ic_bookmark`, `ic_arrow_left`,...).
  - **Lucide Icons** (https://lucide.dev): Sử dụng cho các biểu tượng danh mục món ăn (`ic_category_rice`, `ic_category_noodle`, `ic_category_snack`, `ic_category_coffee`, `ic_category_bbq`).
- **Cách thêm icon vào dự án:**
  - Tải file SVG từ nguồn trên về máy.
  - Trong Android Studio: Chuột phải vào `res/drawable` -> chọn **New** -> **Vector Asset**.
  - Chọn **Local file (SVG, PSD)**, chọn file SVG và đặt tên tài nguyên (ví dụ: `ic_star_filled.xml`).
  - Sử dụng trực tiếp trong layout bằng `android:src="@drawable/..."` hoặc `app:drawableStartCompat="@drawable/..."`.

---

## 5. Hướng dẫn cài đặt và chạy ứng dụng
1. Clone hoặc tải mã nguồn về máy tính.
2. Mở thư mục dự án bằng **Android Studio**.
3. Chờ Android Studio đồng bộ Gradle (`Sync Project with Gradle Files`).
4. Kết nối thiết bị Android thật (bật USB Debugging) hoặc khởi động máy ảo (Android Emulator).
5. Nhấn **Run** (phím tắt `Shift + F10`) để biên dịch và trải nghiệm ứng dụng.
