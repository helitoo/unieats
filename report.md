# CHƯƠNG 1 TỔNG QUAN ĐỀ TÀI

## 1.1 Giới thiệu đề tài

Trong kỷ nguyên số hóa hiện nay, ẩm thực không chỉ đơn thuần đáp ứng nhu cầu dinh dưỡng hàng ngày mà đã trở thành một phần trải nghiệm văn hóa và lối sống của người tiêu dùng, đặc biệt là giới trẻ và học sinh - sinh viên. Với sự bùng nổ của các địa điểm ăn uống, từ các quán ăn bình dân, xe đẩy lề đường cho đến các nhà hàng sang trọng, việc tìm kiếm một địa điểm phù hợp với túi tiền, vị trí địa lý, khẩu vị và thời gian hoạt động trở nên vô cùng phức tạp. Người tiêu dùng thường xuyên phải đối mặt với tình trạng thiếu thông tin minh bạch, đánh giá thiếu khách quan hoặc mất nhiều thời gian tra cứu trên nhiều nền tảng truyền thông xã hội khác nhau.

Xuất phát từ thực tế đó, dự án **UniEats – Ứng dụng Tìm kiếm & Gợi ý Ẩm thực** được nghiên cứu và phát triển trên hệ điều hành Android nhằm tạo ra một nền tảng toàn diện, kết nối trực tiếp người dùng với các điểm đến ẩm thực xung quanh. Hệ thống tối ưu hóa quy trình tìm kiếm dựa trên đa tiêu chí như ngân sách (price range), danh mục ẩm thực, giờ mở cửa (opening/closing time) và xu hướng đánh giá từ cộng đồng (community rating & reviews).

Ngoài việc hỗ trợ người dùng cuối (Customer), **UniEats** còn cung cấp công cụ quản trị mạnh mẽ cho Quản trị viên hệ thống (Admin) trong việc kiểm duyệt địa điểm ẩm thực, quản lý người dùng, duyệt danh sách hình ảnh đính kèm và thống kê toàn hệ thống.

Đề tài được xây dựng nhằm vận dụng các kiến thức chuyên sâu về lập trình ứng dụng di động Android (Java/Kotlin), kiến trúc phần mềm hiện đại MVVM, thiết kế giao diện tùy biến tối ưu, tích hợp kiến trúc Fragment điều hướng đa màn hình, kết hợp cùng dịch vụ điện toán đám mây Firebase Firestore, Firebase Authentication, giải pháp lưu trữ hình ảnh Cloudinary và bộ biểu tượng chuẩn **Heroicons** / **Lucide Icons**.

## 1.2 Nhu cầu thực tế

Thực trạng tìm kiếm và lựa chọn địa điểm ẩm thực của người dùng hiện nay gặp phải nhiều thách thức:

1. **Thiếu thông tin tập trung và minh bạch**: Thông tin về giá cả, hình ảnh thực đơn, giờ đóng/mở cửa của nhiều quán ăn nhỏ lẻ thường không được cập nhật thường xuyên hoặc phân tán rải rác trên các mạng xã hội.
2. **Khó khăn trong tìm kiếm theo ngân sách và khu vực**: Đa số các ứng dụng hiện hữu tập trung vào mảng giao đồ ăn trực tuyến (Food Delivery) với chi phí dịch vụ cao, chưa có công cụ tối ưu cho việc tìm kiếm quán ăn tại chỗ (Dine-in) phù hợp với khoảng ngân sách cá nhân.
3. **Chưa tối ưu hóa trải nghiệm tương tác cộng đồng**: Người dùng có nhu cầu chia sẻ hình ảnh thực tế, trải nghiệm ăn uống và đóng góp thông tin về các quán ăn mới chưa xuất hiện trên hệ thống.
4. **Nhu cầu đóng góp và ban quản trị**: Người dùng có nhu cầu đóng góp quán mới kèm 1 ảnh bìa đại diện và danh sách ảnh chụp thực đơn đính kèm để cộng đồng cùng trải nghiệm. Bên cạnh đó, Ban quản trị cần cơ chế kiểm duyệt (Moderation) chặt chẽ để duyệt thông tin quán ăn và đảm bảo chất lượng dữ liệu.

Do đó, việc phát triển ứng dụng di động **UniEats** đáp ứng trôn vẹn các yêu cầu:

- Tìm kiếm & lọc quán ăn thông minh theo tên, danh mục và khoảng giá.
- Đánh giá khách quan kèm hình ảnh thực tế từ người dùng.
- Cơ chế đóng góp quán mới kèm ảnh bìa & ảnh thực đơn đính kèm và xét duyệt từ Admin.
- Đồng bộ dữ liệu thời gian thực trên mây và tối ưu hiệu năng thiết bị.

## 1.3 Phạm vi và chức năng chính

### 1.3.1 Phạm vi đề tài

Đề tài tập trung nghiên cứu, thiết kế và phát triển ứng dụng di động chạy bản địa (Native Android App) trên hệ điều hành Android (hỗ trợ từ Android 7.0 - API level 24 trở lên đến Android 14/15 - API level 36), hướng tới 02 nhóm đối tượng mục tiêu chính:

1. **Người dùng phổ thông (Users / Customers)**: Đăng ký/Đăng nhập, tìm kiếm địa điểm, lọc theo từ khóa/danh mục/khoảng giá/khoảng cách, xem chi tiết quán, xem bộ sưu tập hình ảnh thực đơn đính kèm, đọc & viết đánh giá (chỉnh sửa/xóa bài viết cá nhân), lưu/bỏ lưu quán yêu thích, định vị chỉ đường qua Google Maps và gửi đóng góp quán ăn mới (chọn 1 ảnh bìa đại diện và danh sách ảnh thực đơn đính kèm).
2. **Quản trị viên (Admin)**: Quản lý danh sách người dùng (tìm kiếm người dùng theo tên/email/SĐT, phân quyền Role `USER`/`ADMIN`, chuyển đổi trạng thái hoạt động `ACTIVE`/`DISABLED`), kiểm duyệt quán ăn (`PENDING`, `APPROVED`, `REJECTED`, `CLOSED`), duyệt các đóng góp từ cộng đồng và xem chỉ số thống kê tổng quan hệ thống.

**Giới hạn đề tài (Out of Scope)**:

- Hệ thống tập trung vào bài toán Tìm kiếm, Gợi ý, Đánh giá và Quản lý thông tin địa điểm ẩm thực. Các chức năng như thanh toán trực tuyến (E-payment gateway integration), đặt món giao hàng trực tiếp (Food delivery logistics & driver tracking) chưa nằm trong phạm vi triển khai của đồ án này.

### 1.3.2 Chức năng chính của hệ thống

#### a) Phân hệ Xác thực & Quản lý tài khoản (Authentication & Account Management)

- Đăng ký, đăng nhập hệ thống an toàn thông qua Email/Password tích hợp Firebase Authentication (kiểm tra tài khoản tồn tại trong hệ thống).
- Tính năng khôi phục/đổi mật khẩu qua Email (Password Restore).
- Bộ lọc kiểm tra ràng buộc dữ liệu đầu vào (Validation) tại Client (Email format, độ dài mật khẩu, số điện thoại).
- Quản lý phiên đăng nhập (Session Management) bằng `SharedPreferences` và `AuthManager`, hỗ trợ duy trì đăng nhập và đăng xuất an toàn.
- Cập nhật thông tin hồ sơ cá nhân (Edit Profile): Họ tên, số điện thoại, ảnh đại diện (upload qua Cloudinary SDK).

#### b) Phân hệ Quản lý & Tìm kiếm Quán ăn (Restaurant Search & Discovery)

- Hiển thị danh sách quán ăn nổi bật (Trending), quán ăn mới và đề xuất gần bạn trên các màn hình Fragment.
- Bộ lọc tìm kiếm nâng cao (Search & Filter): Tìm theo tên quán, từ khóa, danh mục ẩm thực, khoảng giá (min-max price) và trạng thái hoạt động.
- Xem chi tiết quán ăn (Restaurant Detail): Thông tin địa chỉ, giờ mở/đóng cửa, điểm rating trung bình, tổng lượt review, ảnh bìa đại diện (`cover_image_url`), định vị chỉ đường Google Maps, bộ sưu tập hình ảnh & thực đơn đính kèm (`attached_photos`) và các bài đánh giá từ cộng đồng.

#### c) Phân hệ Hình ảnh & Danh mục Quán ăn (Restaurant Photos & Category Management)

- Phân loại danh mục ẩm thực (Cơm, Bún/Phở/Mì, Ăn vặt, Trà sữa/Cà phê, Lẩu & Nướng) đi kèm biểu tượng (icon) và mô tả.
- Mỗi quán ăn (Restaurant) quản lý 01 ảnh bìa đại diện nền quán (`coverImageUrl`) và tập hợp danh sách các hình ảnh đính kèm (`attachedPhotos` bao gồm ảnh bảng giá, thực đơn và không gian quán).
- Hiển thị bộ sưu tập hình ảnh & thực đơn đính kèm cuộn ngang tại màn hình chi tiết nhà hàng (`DetailRestaurantActivity`).
- Quy trình cập nhật thực đơn & hình ảnh: Người dùng gửi đóng góp quán mới chọn 1 ảnh bìa đại diện và chọn các ảnh thực đơn đính kèm (`ContributeRestaurantActivity`). Admin duyệt đóng góp và phê duyệt nhà hàng hiển thị chính thức trên ứng dụng.

#### d) Phân hệ Đánh giá & Phản hồi (Reviews & Rating System)

- Người dùng đã đăng nhập có thể viết bài đánh giá (Write Review) cho quán ăn gồm: Số sao chấm điểm (1 - 5 sao), nội dung nhận xét (comment).
- Đính kèm và tải lên nhiều hình ảnh thực tế cho bài viết (Review Photos) qua Cloudinary Cloud Storage.
- Phân định quyền sở hữu bài viết: Người dùng chỉ được phép chỉnh sửa hoặc xóa bài đánh giá do chính mình viết.
- Tự động tính toán và cập nhật lại điểm đánh giá trung bình (`rating_avg`) và tổng số lượt đánh giá (`review_count`) của quán ăn trên hệ thống Firestore.
- Hiển thị danh sách bài đánh giá (Review Adapter) kèm hình ảnh thumbnail và danh sách ảnh chụp tương ứng.

#### e) Phân hệ Lưu trữ & Yêu thích (Saved Restaurants)

- Người dùng có thể đánh dấu lưu/bỏ lưu các quán ăn yêu thích (`saved_restaurants`).
- Quản lý danh sách quán ăn đã lưu riêng biệt với nút **"Hủy lưu"** giúp quản lý dễ dàng.

#### f) Phân hệ Đóng góp Quán ăn Mới (Contributions)

- Người dùng có thể gửi yêu cầu đóng góp địa điểm ăn uống mới (Contribute Restaurant) bao gồm: Tên quán, địa chỉ chi tiết, danh mục món ăn (Dropdown list), khoảng giá, chọn 1 ảnh bìa đại diện và tập hợp ảnh thực đơn đính kèm từ thư viện thiết bị.
- Yêu cầu sẽ ở trạng thái chờ duyệt (`PENDING`) cho đến khi được Admin phê duyệt (`APPROVED`) hoặc từ chối (`REJECTED`).

#### g) Phân hệ Quản trị & Xét duyệt (Admin Dashboard)

- **Quản lý Người dùng (Manage User)**: Xem danh sách tài khoản, tìm kiếm người dùng theo tên/email/sđt thời gian thực, phân quyền (`USER`, `ADMIN`), chuyển đổi trạng thái hoạt động (`ACTIVE` / `DISABLED`).
- **Quản lý Quán ăn (Manage Restaurant)**: Phê duyệt hoặc thay đổi trạng thái hoạt động của quán (`PENDING`, `APPROVED`, `REJECTED`, `CLOSED`).
- **Duyệt Đóng góp**: Xem danh sách đóng góp từ cộng đồng, chuyển thông tin đóng góp hợp lệ thành quán ăn chính thức trên hệ thống.
- **Thống kê Hệ thống (System Statistics)**: Các chỉ số tổng quan về số lượng người dùng, trạng thái tài khoản, tổng số quán ăn và tiến độ kiểm duyệt được tích hợp trực tiếp vào màn hình Quản lý Người dùng (`ManageUserActivity`) và Kiểm duyệt Quán ăn (`ManageRestaurantActivity`).

---

## 1.4 Công nghệ sử dụng

### 1.4.1 Ngôn ngữ lập trình

- **Java (JDK 11)**: Ngôn ngữ lập trình chính được sử dụng để xây dựng toàn bộ Activity, Fragment, Adapter, Helper và Business Logic trong ứng dụng UniEats.
- **Kotlin**: Hỗ trợ mở rộng thông qua các thư viện Android Jetpack KTX nhằm tối ưu hóa xử lý bất đồng bộ và thành phần giao diện.

### 1.4.2 Nền tảng và kiến trúc

```
+-------------------------------------------------------------------+
|                        PRESENTATION LAYER                         |
|   Activities / Fragments (Home, Search, Trending, Profile) /      |
|           Adapters / ViewBinding / Custom XML Layouts             |
+-------------------------------------------------------------------+
                                  |
                                  v
+-------------------------------------------------------------------+
|                          BUSINESS LAYER                           |
|       AuthManager / FirebaseManager / CloudinaryManager           |
+-------------------------------------------------------------------+
                                  |
                                  v
+-------------------------------------------------------------------+
|                            DATA LAYER                             |
|  Firebase Auth  |  Cloud Firestore  | Cloudinary  | SharedPrefs  |
+-------------------------------------------------------------------+
```

_Hình 1.1: Kiến trúc hệ thống UniEats và Mô hình xử lý dữ liệu Cloud-Mobile_

- **Android SDK (Compile SDK 36, Min SDK 24)**: Nền tảng phát triển ứng dụng di động Android chính thức.
- **Kiến trúc MVVM / Controller-Helper Pattern**: Tách biệt rõ ràng giữa tầng hiển thị giao diện (View), tầng xử lý logic dịch vụ (Helper/Manager) và tầng mô hình dữ liệu (Entity/Model).
- **Android Jetpack**:
  - `AppCompat` & `Activity KTX`: Đảm bảo tính tương thích cao trên nhiều thế hệ thiết bị Android.
  - `ConstraintLayout`: Tối ưu hiệu năng dựng hình giao diện phức tạp mà không gây lồng ghép view sâu.

### 1.4.3 Công nghệ giao diện người dùng & Kiến trúc Fragment

- **Kiến trúc Fragment (Fragment Architecture)**:
  Ứng dụng UniEats sử dụng mô hình điều hướng đa Fragment linh hoạt lồng trong `MainActivity`, giúp quản lý giao diện chính mượt mà, tiết kiệm tài nguyên hệ thống và tối ưu hóa vòng đời ứng dụng (Lifecycle):
  - **`HomeFragment`**: Đảm nhận chức năng hiển thị trang chủ, thanh tìm kiếm nhanh, các banner khuyến mãi, danh mục ẩm thực cuộn ngang và danh sách quán ăn gợi ý theo địa điểm.
  - **`SearchFragment`**: Quản lý giao diện tìm kiếm chuyên sâu, xử lý nhập từ khóa thời gian thực, lọc theo khoảng giá, thời gian đóng/mở cửa và danh mục ẩm thực.
  - **`TrendingFragment`**: Đảm nhận hiển thị các nhà hàng đang dẫn đầu xu hướng ẩm thực, có điểm đánh giá cao và số lượng phản hồi áp đảo từ cộng đồng.
  - **`ProfileFragment`**: Quản lý toàn bộ thông tin cá nhân của người dùng, liên kết các chức năng cập nhật hồ sơ, xem lịch sử, chuyển đổi quyền truy cập và đăng xuất.
  - **Cơ chế điều hướng (Fragment Navigation)**: Sử dụng `FragmentManager` và `FragmentTransaction` kết hợp `BottomNavigationView` để thực hiện chuyển đổi giữa các Fragment nhanh chóng mà không cần tái khởi tạo Activity, giúp loại bỏ hiện tượng nhấp nháy màn hình và duy trì trạng thái dữ liệu (State Retention) hoàn hảo.

- **Custom Layout & ViewBinding**:
  - Toàn bộ giao diện được xây dựng bằng hệ thống mã nguồn XML tùy biến (Custom XML Layouts), không sử dụng bộ thư viện Google Material Components nhằm đảm bảo tính chủ động, tối ưu hóa kích thước file APK và đạt hiệu năng dựng hình mượt mà nhất.
  - Tích hợp **ViewBinding** giúp truy xuất và tương tác với các phần tử UI an toàn về kiểu dữ liệu (Type-safe) và loại bỏ hoàn toàn nguy cơ NullPointerException.

- **RecyclerView & ViewHolder Pattern**: Hiển thị danh sách dữ liệu linh hoạt (Danh sách quán ăn, bộ sưu tập ảnh đính kèm, bài đánh giá, hình ảnh review) với cơ chế tái sử dụng View tối ưu bộ nhớ.

- **Hệ thống các Thành phần Giao diện XML Layout (XML Layout Elements)**:
  Hệ thống giao diện của UniEats được xây dựng hoàn toàn dựa trên mã nguồn XML tùy biến với sự kết hợp chặt chẽ của các thành phần giao diện nền tảng:
  - **`LinearLayout`**: Quản lý và bố trí các thành phần giao diện theo một hướng duy nhất (chiều dọc `vertical` hoặc chiều ngang `horizontal`), ứng dụng trong việc dựng layout các mục danh sách, form nhập thông tin, các dòng thẻ thông tin và thanh tác vụ.
  - **`RelativeLayout`**: Cho phép định vị và sắp xếp các View con phụ thuộc vào vị trí của View khác hoặc căn chỉnh linh hoạt so với View cha (`layout_centerInParent`, `layout_alignParentStart`, `layout_alignParentEnd`, `layout_toEndOf`...), giúp xây dựng thanh HeaderBar và ô tìm kiếm tối ưu.
  - **`ScrollView` & `HorizontalScrollView`**: Cung cấp khả năng cuộn dọc và cuộn ngang dành cho các vùng nội dung vượt quá kích thước hiển thị của màn hình thiết bị (như form đóng góp, chi tiết nhà hàng, các chip danh mục ẩm thực cuộn ngang).
  - **`CardView` (`androidx.cardview.widget.CardView`)**: Thành phần tạo các thẻ chứa nội dung nổi bật với tính năng bo tròn góc tùy biến (`cardCornerRadius`) và hiệu ứng bóng nổi 3D (`cardElevation`), ứng dụng trong các thẻ nhà hàng, thẻ đánh giá, thẻ thống kê và bộ sưu tập ảnh.
  - **`RecyclerView` (`androidx.recyclerview.widget.RecyclerView`)**: Thành phần cuộn danh sách hiệu năng cao, tái sử dụng các View đã cuộn khỏi màn hình thông qua mô hình ViewHolder, dùng cho danh sách nhà hàng, danh sách tìm kiếm, danh sách người dùng và bộ sưu tập hình ảnh đính kèm.
  - **`TextView`**: Hiển thị văn bản, tiêu đề, nhãn tag tùy biến, số liệu thống kê và đính kèm biểu tượng đồ họa linh hoạt (`app:drawableStartCompat`, `app:drawableEndCompat`, `android:drawablePadding`).
  - **`EditText`**: Các ô nhập liệu họ tên, email, sđt, giá tiền min-max, ô tìm kiếm từ khóa thời gian thực có kết hợp xử lý sự kiện lắng nghe thay đổi văn bản (`TextWatcher`).
  - **`ImageView`**: Hiển thị hình ảnh biểu tượng vector (`app:tint`), avatar tài khoản, ảnh bìa nhà hàng và các hình ảnh thực đơn đính kèm.
  - **`AppCompatButton` (`androidx.appcompat.widget.AppCompatButton`)**: Nút bấm thao tác với background tùy biến bo tròn (`bg_button_orange`, `bg_button_google`), chữ viết hoa/thường tùy chỉnh (`textAllCaps="false"`).
  - **`View`**: Thành phần vẽ đường kẻ phân cách mờ 1dp (`neutral_200`), khoảng trắng dãn layout và dải màu đè mờ gradient (overlay dark gradient).

### 1.4.4 Cơ sở dữ liệu và lưu trữ

- **Firebase Authentication**: Xác thực người dùng qua Email/Password, quản lý Token và trạng thái đăng nhập bảo mật.
- **Cloud Firestore**: Cơ sở dữ liệu NoSQL trên mây (Cloud Database) lưu trữ toàn bộ các Collection dữ liệu của hệ thống (`users`, `categories`, `restaurants`, `menus`, `reviews`, `saved_restaurants`, `contributions`) với cơ chế đồng bộ thời gian thực (Real-time update).
- **Cloudinary SDK**: Nền tảng lưu trữ và quản lý đa phương tiện đám mây (Cloud Image Management), xử lý tối ưu hóa kích thước và đường dẫn CDN cho ảnh đại diện, ảnh đại diện quán ăn, ảnh thực đơn và ảnh đánh giá.
- **SharedPreferences**: Lưu trữ cục bộ trạng thái phiên đăng nhập (`AuthManager`) và các cài đặt cá nhân của ứng dụng.

### 1.4.5 Thư viện hỗ trợ & Bộ biểu tượng

- **Glide (Bumptech Glide 4.x)**: Thư viện tải và hiển thị hình ảnh từ URL bất đồng bộ, hỗ trợ caching (Disk & Memory cache), hiển thị placeholder và xử lý ảnh bo tròn.
- **Bộ biểu tượng Heroicons & Lucide Icons**: Toàn bộ hệ thống icon trong ứng dụng UniEats được chuẩn hóa và trích xuất từ hai bộ thư viện icon hiện đại hàng đầu hiện nay là **Heroicons** và **Lucide Icons**. Các biểu tượng được chuyển đổi thành định dạng Vector Drawable (`.xml`) sắc nét, giúp giao diện đạt tính thẩm mỹ cao, hiển thị mượt mà trên mọi độ phân giải màn hình (HD, Full HD, 2K, 4K) mà không làm tăng dung lượng ứng dụng.
- **Cloudinary Android SDK**: Thư viện hỗ trợ upload ảnh trực tiếp từ Android Uri lên Cloudinary server không qua intermediate server.
- **Firebase BoM (Bill of Materials)**: Đồng bộ phiên bản các thư viện Firebase trong dự án.

### 1.4.6 Công cụ phát triển

- **Android Studio (Ladybug / Iguana)**: Môi trường phát triển tích hợp (IDE) chính thức.
- **Gradle (Kotlin DSL - build.gradle.kts)**: Công cụ tự động hóa biên dịch và quản lý phụ thuộc (Dependencies).
- **Git & GitHub**: Quản lý phiên bản mã nguồn và phối hợp làm việc nhóm.
- **dbdiagram.io**: Thiết kế và trực quan hóa lược đồ cơ sở dữ liệu quan hệ (ERD).

---

\newpage

# CHƯƠNG 2 PHÂN TÍCH VÀ THIẾT KẾ HỆ THỐNG

## 2.1 Phân tích yêu cầu

### 2.1.1 Yêu cầu chức năng

#### a) Phân hệ Người dùng phổ thông (Customer)

- **Đăng ký / Đăng nhập / Khôi phục mật khẩu**: Cho phép tạo tài khoản mới, đăng nhập bằng Email/Mật khẩu và gửi email khôi phục khi quên mật khẩu.
- **Quản lý trang cá nhân**: Chỉnh sửa thông tin cá nhân (Họ tên, SĐT), cập nhật ảnh đại diện avatar qua `ProfileFragment` và `EditProfileActivity`.
- **Tìm kiếm & Gợi ý quán ăn**:
  - Tìm theo tên quán hoặc từ khóa trên thanh tìm kiếm (`SearchFragment`).
  - Lọc theo danh mục ẩm thực (`CategoryAdapter`), khoảng giá (`price_range_min` - `price_range_max`), giờ mở cửa.
  - Xem danh sách quán đang hot/nổi bật (`TrendingFragment`).
- **Xem chi tiết quán ăn & Thực đơn**:
  - Xem vị trí địa chỉ, điểm đánh giá trung bình, ảnh bìa quán.
  - Xem danh sách món ăn phân theo thực đơn (`MenuAdapter`), giá bán và trạng thái còn món.
- **Đánh giá & Nhận xét**:
  - Viết đánh giá cho quán ăn (`WriteReviewActivity`), chọn số sao từ 1 đến 5.
  - Tải lên nhiều ảnh chụp thực tế đi kèm nhận xét.
- **Yêu thích & Lưu vị trí**: Lưu quán ăn vào danh sách yêu thích (`SavedRestaurantsActivity`) để xem lại dễ dàng.
- **Đóng góp quán ăn mới**: Gửi thông tin đóng góp quán ăn mới cho hệ thống (`ContributeRestaurantActivity`).

#### b) Phân hệ Đóng góp & Kiểm duyệt Thực đơn

- **Đóng góp thông tin & Ảnh thực đơn**: Người dùng chụp/tải ảnh thực đơn và vị trí khi đề xuất quán mới (`ContributeRestaurantActivity`).
- **Cập nhật thực đơn chính thức**: Quản trị viên duyệt đóng góp từ người dùng và tạo thực đơn hiển thị tại trang chi tiết nhà hàng.

#### c) Phân hệ Quản trị viên (Admin / Moderator)

- **Quản lý Người dùng (Manage User)**: Xem danh sách tài khoản, tìm kiếm, phân quyền (`USER`, `MODERATOR`, `ADMIN`), khóa hoặc mở khóa tài khoản.
- **Quản lý Quán ăn (Manage Restaurant)**: Duyệt quán mới tạo, thay đổi trạng thái hoạt động của quán (`PENDING`, `APPROVED`, `REJECTED`, `CLOSED`).
- **Xét duyệt Đóng góp (Manage Contributions)**: Xem danh sách đóng góp từ cộng đồng, phê duyệt để đưa vào danh sách quán chính thức hoặc từ chối nếu thông tin không chính xác.
- **Thống kê Hệ thống**: Hiển thị các chỉ số tổng quan về số lượng người dùng, phân loại trạng thái hoạt động và số lượng quán ăn theo từng trạng thái kiểm duyệt trực tiếp tại giao diện quản trị.

---

### 2.1.2 Yêu cầu phi chức năng

- **Hiệu năng (Performance)**:
  - Thời gian tải danh sách và tìm kiếm dữ liệu dưới 2 giây đối với kết nối mạng tiêu chuẩn.
  - Tối ưu bộ nhớ đệm (Caching) khi tải hình ảnh bằng Glide, tránh hiện tượng giật lag (Jank) khi cuộn danh sách `RecyclerView` trong các Fragment.
- **Tính ổn định & Tương thích (Reliability & Compatibility)**:
  - Tương thích tốt với các phiên bản Android từ SDK 24 (Android 7.0) đến SDK 36 (Android 15).
  - Xử lý mượt mà trên các màn hình có tỉ lệ và độ phân giải khác nhau.
- **Bảo mật & An toàn dữ liệu (Security)**:
  - Mật khẩu đăng nhập được xác thực an toàn qua Firebase Auth.
  - Phân quyền truy cập các API/Collection Firestore dựa trên vai trò của người dùng (`role`).
- **Khả năng mở rộng & Bảo trì (Maintainability & Scalability)**:
  - Cấu trúc mã nguồn được mô-đun hóa rõ ràng theo mô hình MVVM, Fragment & Helper.
  - Dễ dàng tích hợp thêm các dịch vụ bản đồ (Google Maps API), cổng thanh toán hoặc mô hình trí tuệ nhân tạo (AI Recommendation) trong tương lai.
- **Giao diện & Trải nghiệm người dùng (UI/UX)**:
  - Giao diện tùy biến trực quan, sử dụng màu sắc hài hòa và bộ icon **Heroicons** / **Lucide Icons** sắc nét.
  - Thông báo lỗi hoặc trạng thái rõ ràng qua `Toast`, `Snackbar` hoặc dialog cảnh báo.

---

## 2.2 Use case

### 2.2.1 Các Tác nhân (Actors)

Hệ thống **UniEats** xác định 02 nhóm tác nhân chính:

1. **Customer (Người dùng phổ thông)**: Đăng ký, đăng nhập theo danh sách tài khoản, tìm kiếm & lọc quán ăn, xem chi tiết & chỉ đường Google Maps, lưu/hủy lưu quán yêu thích, đánh giá & quản lý bài đánh giá cá nhân, đóng góp quán ăn mới (chọn 1 ảnh bìa đại diện và danh sách ảnh thực đơn đính kèm).
2. **Admin (Quản trị viên hệ thống)**: Kiểm duyệt danh sách quán ăn (`PENDING`, `APPROVED`, `REJECTED`, `CLOSED`), quản lý & tìm kiếm người dùng (theo tên, email, sđt), duyệt đóng góp từ cộng đồng và xem chỉ số thống kê hệ thống.

---

### 2.2.2 Các mối quan hệ đặc biệt (Include / Extend)

- **Relationship Include**:
  - Chức năng _Viết đánh giá (Write Review)_ bắt buộc phải `<<include>>` hành vi _Chấm điểm Sao (Rating)_ và _Xác thực đăng nhập (Log In)_.
  - Chức năng _Đóng góp quán mới (Contribute)_ bắt buộc `<<include>>` việc _Nhập thông tin quán_ và _Upload hình ảnh thực đơn đính kèm_.
- **Relationship Extend**:
  - Chức năng _Xem chi tiết quán (View Detail)_ được `<<extend>>` bởi hành vi _Lưu vào danh sách yêu thích (Save Restaurant)_ và _Đính kèm ảnh review (Upload Review Photos)_.
  - Chức năng _Quản lý hệ thống (Admin System)_ được `<<extend>>` chi tiết bởi _Khóa tài khoản_, _Duyệt đóng góp_ và _Thay đổi trạng thái quán_.

---

### 2.2.3 Bảng tổng hợp chức năng Use-case

_Bảng 2.1: Tổng hợp danh sách Use-case hệ thống UniEats_

| Mã UC    | Tên Use-case                                | Actor chính       | Mô tả chức năng                                                                                |
| :------- | :------------------------------------------ | :---------------- | :--------------------------------------------------------------------------------------------- |
| **UC01** | Sign Up (Đăng ký)                           | Customer          | Cho phép người dùng khởi tạo tài khoản mới với Email, Mật khẩu, Họ tên và SĐT.                 |
| **UC02** | Sign In (Đăng nhập)                         | Customer          | Xác thực thông tin người dùng vào hệ thống theo danh sách user sẵn có.                         |
| **UC03** | Restore Password (Khôi phục mật khẩu)       | Customer          | Gửi email hướng dẫn đặt lại mật khẩu khi quên.                                                 |
| **UC04** | Edit Profile (Sửa hồ sơ)                    | Customer          | Cập nhật tên, sđt và avatar cá nhân.                                                           |
| **UC05** | Search & Filter (Tìm kiếm & Lọc)            | Customer          | Tìm kiếm quán ăn theo từ khóa, lọc theo danh mục, khoảng giá và xếp giá qua `SearchFragment`.  |
| **UC06** | View Restaurant Detail (Xem chi tiết quán)  | Customer          | Xem thông tin liên hệ, bộ sưu tập hình ảnh thực đơn đính kèm và bài đánh giá của quán.         |
| **UC07** | Save / Favorite (Lưu yêu thích)             | Customer          | Đánh dấu hoặc hủy lưu quán ăn vào danh sách cá nhân.                                           |
| **UC08** | Write Review (Viết đánh giá)                | Customer          | Gửi điểm số (1-5 sao), lời bình luận và đăng nhiều ảnh thực tế (chỉnh sửa/xóa bài cá nhân).    |
| **UC09** | Contribute Restaurant (Đóng góp quán mới)   | Customer          | Gửi thông tin đề xuất quán ăn mới kèm ảnh bìa & ảnh thực đơn cho Admin kiểm duyệt.             |
| **UC10** | Contribute Photos (Đóng góp ảnh đính kèm)   | Customer          | Người dùng chọn ảnh bìa đại diện và danh sách ảnh thực đơn đính kèm khi đề xuất quán mới.      |
| **UC11** | Approve Photos (Duyệt hình ảnh quán)        | Admin             | Admin kiểm duyệt đóng góp và phê duyệt nhà hàng hiển thị chính thức.                           |
| **UC12** | Manage Users (Quản lý người dùng)           | Admin             | Xem danh sách tài khoản, tìm kiếm người dùng thời gian thực, khóa/mở khóa tài khoản, Role.     |
| **UC13** | Manage Restaurants (Quản lý quán ăn)        | Admin             | Duyệt quán mới, đổi trạng thái (APPROVED/REJECTED/CLOSED).                                     |
| **UC14** | Approve Contributions (Duyệt đóng góp)      | Admin             | Phê duyệt hoặc từ chối các yêu cầu đề xuất quán mới từ cộng đồng.                              |
| **UC15** | View System Statistics (Xem thống kê)       | Admin             | Xem các chỉ số thống kê tổng quan (số lượng người dùng, số lượng quán ăn) tích hợp trong quản trị. |

---

## 2.3 Thiết kế cơ sở dữ liệu

### 2.3.1 Lược đồ dữ liệu

Cơ sở dữ liệu của ứng dụng **UniEats** được thiết kế chuẩn hóa theo mô hình quan hệ, đồng thời được tổ chức thành các Collections tương ứng trên nền tảng Cloud Firestore nhằm đảm bảo tính linh hoạt và khả năng truy vấn thời gian thực.

Sơ đồ ERD trực tuyến được lưu trữ tại đường dẫn:  
🔗 [https://dbdiagram.io/d/Mobile-6ad81392f65bbc60f6e71bf3](https://dbdiagram.io/d/Mobile-6ad81392f65bbc60f6e71bf3)

---

### 2.3.2 Mô tả chi tiết các bảng

#### 1. Bảng `users` (Người dùng)

Lưu trữ thông tin tài khoản người dùng, phân quyền và trạng thái hoạt động trong hệ thống UniEats.

_Bảng 2.2: Cấu trúc bảng users_

| STT | Tên thuộc tính  | Kiểu dữ liệu             | Ràng buộc / Giá trị mặc định                                   | Mô tả thuộc tính                        |
| :-: | :-------------- | :----------------------- | :------------------------------------------------------------- | :-------------------------------------- |
|  1  | `user_id`       | UUID / String            | PRIMARY KEY, Mặc định: `gen_random_uuid()`                     | Mã định danh duy nhất của người dùng    |
|  2  | `full_name`     | VARCHAR(100)             | NOT NULL                                                       | Họ và tên hiển thị của người dùng       |
|  3  | `email`         | VARCHAR(150)             | UNIQUE, NOT NULL                                               | Địa chỉ Email dùng để đăng nhập         |
|  4  | `phone_number`  | VARCHAR(15)              | UNIQUE                                                         | Số điện thoại liên hệ                   |
|  5  | `password_hash` | VARCHAR(255)             |                                                                | Mật khẩu đã được mã hóa (Firebase Auth) |
|  6  | `avatar_url`    | TEXT                     |                                                                | Đường dẫn ảnh đại diện (Cloudinary URL) |
|  7  | `role`          | VARCHAR(20)              | Mặc định: `'USER'`, CHECK (`'USER'`, `'ADMIN'`)                | Vai trò người dùng trong hệ thống       |
|  8  | `is_active`     | BOOLEAN                  | Mặc định: `TRUE`                                               | Trạng thái tài khoản (Kích hoạt / Khóa) |
|  9  | `created_at`    | TIMESTAMP WITH TIME ZONE | Mặc định: `CURRENT_TIMESTAMP`                                  | Thời gian khởi tạo tài khoản            |
| 10  | `updated_at`    | TIMESTAMP WITH TIME ZONE | Mặc định: `CURRENT_TIMESTAMP`                                  | Thời gian cập nhật thông tin mới nhất   |

---

#### 2. Bảng `categories` (Danh mục ẩm thực)

Quản lý các loại hình ẩm thực (Ăn sáng, Món Việt, Trà sữa, Đồ ăn vặt,...) để phân loại nhà hàng.

_Bảng 2.3: Cấu trúc bảng categories_

| STT | Tên thuộc tính | Kiểu dữ liệu | Ràng buộc / Giá trị mặc định | Mô tả thuộc tính                       |
| :-: | :------------- | :----------- | :--------------------------- | :------------------------------------- |
|  1  | `category_id`  | SERIAL / INT | PRIMARY KEY, Auto Increment  | Mã danh mục tự tăng                    |
|  2  | `name`         | VARCHAR(100) | NOT NULL, UNIQUE             | Tên danh mục ẩm thực                   |
|  3  | `slug`         | VARCHAR(100) | NOT NULL, UNIQUE             | Đường dẫn thân thiện (URL Slug)        |
|  4  | `icon_url`     | TEXT         |                              | Đường dẫn biểu tượng đại diện danh mục |
|  5  | `description`  | TEXT         |                              | Mô tả chi tiết về danh mục             |

---

#### 3. Bảng `restaurants` (Quán ăn / Nhà hàng - Đã hợp nhất từ Đóng góp)

Lưu trữ thông tin chi tiết các địa điểm ăn uống trên hệ thống UniEats.

_Bảng 2.4: Cấu trúc bảng restaurants_

| STT | Tên thuộc tính    | Kiểu dữ liệu             | Ràng buộc / Giá trị mặc định                                                       | Mô tả thuộc tính                         |
| :-: | :---------------- | :----------------------- | :--------------------------------------------------------------------------------- | :--------------------------------------- |
|  1  | `restaurant_id`   | UUID / String            | PRIMARY KEY, Mặc định: `gen_random_uuid()`                                         | Mã định danh duy nhất của quán ăn        |
|  2  | `name`            | VARCHAR(200)             | NOT NULL                                                                           | Tên quán ăn / nhà hàng                   |
|  3  | `slug`            | VARCHAR(200)             | NOT NULL, UNIQUE                                                                   | Đường dẫn thân thiện                     |
|  4  | `address`         | TEXT                     | NOT NULL                                                                           | Địa chỉ chi tiết của quán ăn             |
|  5  | `price_range_min` | DECIMAL(10, 2)           | Mặc định: `0`                                                                      | Mức giá thấp nhất                        |
|  6  | `price_range_max` | DECIMAL(10, 2)           | Mặc định: `0`                                                                      | Mức giá cao nhất                         |
|  7  | `opening_time`    | TIME                     |                                                                                    | Giờ mở cửa hàng ngày                     |
|  8  | `closing_time`    | TIME                     |                                                                                    | Giờ đóng cửa hàng ngày                   |
|  9  | `rating_avg`      | DECIMAL(2, 1)            | Mặc định: `0.0`                                                                    | Điểm đánh giá trung bình (1.0 - 5.0)     |
| 10  | `review_count`    | INT                      | Mặc định: `0`                                                                      | Tổng số lượt đánh giá từ người dùng      |
| 11  | `cover_image_url` | TEXT                     |                                                                                    | 01 Ảnh bìa đại diện nền quán ăn          |
| 12  | `status`          | VARCHAR(20)              | Mặc định: `'PENDING'`, CHECK (`'PENDING'`, `'APPROVED'`, `'REJECTED'`, `'CLOSED'`) | Trạng thái duyệt và hoạt động của quán   |
| 13  | `created_by`      | UUID / String            | FOREIGN KEY `users(user_id)` ON DELETE SET NULL                                    | Người tạo / Người đóng góp               |
| 14  | `created_at`      | TIMESTAMP WITH TIME ZONE | Mặc định: `CURRENT_TIMESTAMP`                                                      | Thời gian tạo nhà hàng trên hệ thống     |
| 15  | `updated_at`      | TIMESTAMP WITH TIME ZONE | Mặc định: `CURRENT_TIMESTAMP`                                                      | Thời gian cập nhật gần nhất              |

---

#### 4. Bảng `restaurant_categories` (Trung gian Quán ăn - Danh mục)

Bảng liên kết nhiều - nhiều giữa quán ăn và các danh mục ẩm thực tương ứng.

_Bảng 2.5: Cấu trúc bảng restaurant_categories_

| STT | Tên thuộc tính  | Kiểu dữ liệu  | Ràng buộc / Giá trị mặc định                                            | Mô tả thuộc tính       |
| :-: | :-------------- | :------------ | :---------------------------------------------------------------------- | :--------------------- |
|  1  | `restaurant_id` | UUID / String | PRIMARY KEY, FOREIGN KEY `restaurants(restaurant_id)` ON DELETE CASCADE | Mã quán ăn tham chiếu  |
|  2  | `category_id`   | INT           | PRIMARY KEY, FOREIGN KEY `categories(category_id)` ON DELETE CASCADE    | Mã danh mục tham chiếu |

---

#### 5. Bảng `restaurant_photos` (Hình ảnh đính kèm của Quán ăn)

Lưu trữ tập hợp đường dẫn các hình ảnh thực đơn, bảng giá và không gian quán đính kèm của từng nhà hàng.

_Bảng 2.6: Cấu trúc bảng restaurant_photos_

| STT | Tên thuộc tính  | Kiểu dữ liệu             | Ràng buộc / Giá trị mặc định                                         | Mô tả thuộc tính                          |
| :-: | :-------------- | :----------------------- | :------------------------------------------------------------------- | :---------------------------------------- |
|  1  | `photo_id`      | UUID / String            | PRIMARY KEY, Mặc định: `gen_random_uuid()`                           | Mã định danh hình ảnh quán                |
|  2  | `restaurant_id` | UUID / String            | NOT NULL, FOREIGN KEY `restaurants(restaurant_id)` ON DELETE CASCADE | Mã quán ăn sở hữu hình ảnh                |
|  3  | `photo_url`     | TEXT                     | NOT NULL                                                             | Đường dẫn hình ảnh (Cloudinary CDN / Uri) |
|  4  | `created_at`    | TIMESTAMP WITH TIME ZONE | Mặc định: `CURRENT_TIMESTAMP`                                        | Thời điểm tải hình ảnh lên                |

---

#### 6. Bảng `reviews` (Đánh giá / Review)

Lưu trữ các bài đánh giá, chấm điểm và nhận xét của người dùng dành cho các quán ăn.

_Bảng 2.7: Cấu trúc bảng reviews_

| STT | Tên thuộc tính  | Kiểu dữ liệu             | Ràng buộc / Giá trị mặc định                                         | Mô tả thuộc tính                     |
| :-: | :-------------- | :----------------------- | :------------------------------------------------------------------- | :----------------------------------- |
|  1  | `review_id`     | UUID / String            | PRIMARY KEY, Mặc định: `gen_random_uuid()`                           | Mã định danh bài đánh giá            |
|  2  | `user_id`       | UUID / String            | NOT NULL, FOREIGN KEY `users(user_id)` ON DELETE CASCADE             | Mã người dùng thực hiện đánh giá     |
|  3  | `restaurant_id` | UUID / String            | NOT NULL, FOREIGN KEY `restaurants(restaurant_id)` ON DELETE CASCADE | Mã quán ăn được đánh giá             |
|  4  | `rating`        | INT                      | NOT NULL, CHECK (`rating` BETWEEN 1 AND 5)                           | Số sao chấm điểm (1 đến 5 sao)       |
|  5  | `comment`       | TEXT                     |                                                                      | Nội dung văn bản bình luận, nhận xét |
|  6  | `like_count`    | INT                      | Mặc định: `0`                                                        | Số lượt thích bài viết từ cộng đồng  |
|  7  | `created_at`    | TIMESTAMP WITH TIME ZONE | Mặc định: `CURRENT_TIMESTAMP`                                        | Thời điểm đăng bài đánh giá          |
|  8  | `updated_at`    | TIMESTAMP WITH TIME ZONE | Mặc định: `CURRENT_TIMESTAMP`                                        | Thời điểm chỉnh sửa gần nhất         |

---

#### 7. Bảng `review_photos` (Hình ảnh Review)

Lưu trữ tập hợp đường dẫn các hình ảnh thực tế đính kèm theo từng bài đánh giá.

_Bảng 2.8: Cấu trúc bảng review_photos_

| STT | Tên thuộc tính | Kiểu dữ liệu             | Ràng buộc / Giá trị mặc định                                 | Mô tả thuộc tính                    |
| :-: | :------------- | :----------------------- | :----------------------------------------------------------- | :---------------------------------- |
|  1  | `photo_id`     | UUID / String            | PRIMARY KEY, Mặc định: `gen_random_uuid()`                   | Mã định danh hình ảnh review        |
|  2  | `review_id`    | UUID / String            | NOT NULL, FOREIGN KEY `reviews(review_id)` ON DELETE CASCADE | Mã bài đánh giá tương ứng           |
|  3  | `photo_url`    | TEXT                     | NOT NULL                                                     | Đường dẫn hình ảnh (Cloudinary CDN) |
|  4  | `created_at`   | TIMESTAMP WITH TIME ZONE | Mặc định: `CURRENT_TIMESTAMP`                                | Thời gian tải hình ảnh lên          |

---

#### 8. Bảng `saved_restaurants` (Quán ăn đã lưu / Yêu thích)

Lưu danh sách các quán ăn mà người dùng đã đánh dấu yêu thích để xem lại.

_Bảng 2.9: Cấu trúc bảng saved_restaurants_

| STT | Tên thuộc tính  | Kiểu dữ liệu             | Ràng buộc / Giá trị mặc định                                            | Mô tả thuộc tính                 |
| :-: | :-------------- | :----------------------- | :---------------------------------------------------------------------- | :------------------------------- |
|  1  | `user_id`       | UUID / String            | PRIMARY KEY, FOREIGN KEY `users(user_id)` ON DELETE CASCADE             | Mã người dùng lưu quán           |
|  2  | `restaurant_id` | UUID / String            | PRIMARY KEY, FOREIGN KEY `restaurants(restaurant_id)` ON DELETE CASCADE | Mã quán ăn được lưu              |
|  3  | `created_at`    | TIMESTAMP WITH TIME ZONE | Mặc định: `CURRENT_TIMESTAMP`                                           | Thời điểm thực hiện thao tác lưu |

---

#### 9. Bảng `promotions` (Khuyến mãi)

Lưu trữ các chương trình ưu đãi, giảm giá do chủ quán hoặc hệ thống thiết lập.

_Bảng 2.10: Cấu trúc bảng promotions_

| STT | Tên thuộc tính     | Kiểu dữ liệu             | Ràng buộc / Giá trị mặc định                                         | Mô tả thuộc tính                         |
| :-: | :----------------- | :----------------------- | :------------------------------------------------------------------- | :--------------------------------------- |
|  1  | `promotion_id`     | UUID / String            | PRIMARY KEY, Mặc định: `gen_random_uuid()`                           | Mã chương trình khuyến mãi               |
|  2  | `restaurant_id`    | UUID / String            | NOT NULL, FOREIGN KEY `restaurants(restaurant_id)` ON DELETE CASCADE | Mã quán ăn áp dụng ưu đãi                |
|  3  | `title`            | VARCHAR(200)             | NOT NULL                                                             | Tiêu đề chương trình khuyến mãi          |
|  4  | `description`      | TEXT                     |                                                                      | Mô tả điều kiện & chi tiết ưu đãi        |
|  5  | `discount_percent` | INT                      | CHECK (`discount_percent` BETWEEN 0 AND 100)                         | Phần trăm giảm giá (0% - 100%)           |
|  6  | `discount_amount`  | DECIMAL(10, 2)           | Mặc định: `0`                                                        | Số tiền giảm giá trực tiếp (VNĐ)         |
|  7  | `start_date`       | TIMESTAMP WITH TIME ZONE | NOT NULL                                                             | Thời gian bắt đầu áp dụng                |
|  8  | `end_date`         | TIMESTAMP WITH TIME ZONE | NOT NULL                                                             | Thời gian kết thúc chương trình          |
|  9  | `is_active`        | BOOLEAN                  | Mặc định: `TRUE`                                                     | Trạng thái áp dụng (Đang chạy / Hết hạn) |
| 10  | `created_at`       | TIMESTAMP WITH TIME ZONE | Mặc định: `CURRENT_TIMESTAMP`                                        | Thời điểm tạo mã khuyến mãi              |

---

#### 10. Bảng `contributions` (Đóng góp quán mới)

Lưu trữ danh sách thông tin địa điểm ăn uống do người dùng đề xuất cho ban quản trị kiểm duyệt.

_Bảng 2.11: Cấu trúc bảng contributions_

| STT | Tên thuộc tính    | Kiểu dữ liệu             | Ràng buộc / Giá trị mặc định                                           | Mô tả thuộc tính                          |
| :-: | :---------------- | :----------------------- | :--------------------------------------------------------------------- | :---------------------------------------- |
|  1  | `contribution_id` | UUID / String            | PRIMARY KEY, Mặc định: `gen_random_uuid()`                             | Mã yêu cầu đóng góp                       |
|  2  | `user_id`         | UUID / String            | NOT NULL, FOREIGN KEY `users(user_id)` ON DELETE CASCADE               | Mã người dùng gửi đề xuất                 |
|  3  | `restaurant_name` | VARCHAR(200)             | NOT NULL                                                               | Tên quán ăn được đề xuất                  |
|  4  | `address`         | TEXT                     | NOT NULL                                                               | Địa chỉ địa điểm đề xuất                  |
|  5  | `menu_images`     | JSONB / Array            |                                                                        | Mảng danh sách đường dẫn URL ảnh thực đơn |
|  6  | `note`            | TEXT                     |                                                                        | Ghi chú thêm từ người đóng góp            |
|  7  | `status`          | VARCHAR(20)              | Mặc định: `'PENDING'`, CHECK (`'PENDING'`, `'APPROVED'`, `'REJECTED'`) | Trạng thái kiểm duyệt của bài đóng góp    |
|  8  | `created_at`      | TIMESTAMP WITH TIME ZONE | Mặc định: `CURRENT_TIMESTAMP`                                          | Thời điểm gửi đề xuất đóng góp            |

---

\newpage

# CHƯƠNG 3 THIẾT KẾ VÀ XÂY DỰNG ỨNG DỤNG

## 3.1 Thiết kế giao diện

### 3.1.1 Triết lý thiết kế và Phong cách chủ đạo

Giao diện ứng dụng **UniEats** được thiết kế dựa trên triết lý **"Tối giản – Trực quan – Tập trung vào Trải nghiệm Ẩm thực"**. Mục tiêu quan trọng nhất là giúp người dùng tìm kiếm được thông tin món ăn và vị trí địa điểm một cách nhanh chóng nhất chỉ qua vài lần chạm màn hình.

- **Phong cách Flat & Clean Modern UI**: Sử dụng khoảng trắng hợp lý, phân tách nội dung bằng các thẻ `CardView` tùy biến giúp giao diện không bị rối mắt dù chứa nhiều thông tin.
- **Thiết kế lấy hình ảnh làm trung tâm (Content & Visual First)**: Đồ ăn và không gian quán được thể hiện sinh động thông qua các khung hình tỉ lệ chuẩn, hiển thị sắc nét nhờ thư viện Glide và Cloudinary CDN.
- **Tùy biến Layout XML linh hoạt**: Không sử dụng bộ thư viện Google Material Components, dự án xây dựng toàn bộ hệ thống style, drawable shape và màu sắc trong các thư mục tài nguyên `res/values` và `res/drawable`.

---

### 3.1.2 Hệ thống màu sắc và Bộ biểu tượng (Heroicons & Lucide Icons)

Màu sắc trong **UniEats** được lựa chọn kỹ lưỡng nhằm kích thích thị giác và mang tính định hướng người dùng:

- **Màu chủ đạo (Brand / Primary - Cam Đỏ Ẩm thực `orange_500` `#F97316`)**: Tượng trưng cho sự năng động, ấm cúng và kích thích vị giác của các món ăn ngon.
- **Tông màu Trung tính (Neutral Tokens)**: Phân cấp độ đậm nhạt từ `neutral_0` (`#FFFFFF`) đến `neutral_900` (`#171717`) tạo độ tương phản rõ ràng cho văn bản và nền.
- **Bộ biểu tượng Heroicons & Lucide Icons**: Toàn bộ hệ thống icon được tích hợp dạng Vector Drawable (`.xml`) từ **Heroicons** và **Lucide Icons** cho các nút bấm điều hướng (Home, Search, Sparkles/Trending, User/Profile), icon sao đánh giá, vị trí địa lý, thời gian mở cửa, điện thoại liên hệ và tìm kiếm.

---

### 3.1.3 Chi tiết các biến cấu hình giao diện (Values & Values-night)

Nhằm đảm bảo giao diện được cấu trúc chặt chẽ, dễ dàng bảo trì và hỗ trợ chế độ ban đêm (Dark Mode), toàn bộ các biến giao diện trong thư mục `res/values/` và `res/values-night/` được định nghĩa chi tiết như sau:

#### a) Chi tiết bảng màu (Color Tokens) trong `values/colors.xml`

_Bảng 3.1: Danh sách chi tiết toàn bộ các biến màu sắc (Colors Tokens) trong values/colors.xml_

| Thể loại              | Tên biến (`name`) | Mã màu / Tham chiếu  | Ý nghĩa & Vị trí áp dụng                             |
| :-------------------- | :---------------- | :------------------- | :--------------------------------------------------- |
| **Neutral Primitive** | `neutral_0`       | `#FFFFFF`            | Màu trắng tuyệt đối, dùng làm nền thẻ Surface.       |
|                       | `neutral_50`      | `#FAFAFA`            | Màu xám rất nhẹ, làm màu nền Canvas chính.           |
|                       | `neutral_75`      | `#F5F5F5`            | Màu xám nhẹ, dùng làm khung Placeholder tải ảnh.     |
|                       | `neutral_200`     | `#E5E5E5`            | Màu xám nhạt, làm đường viền Divider / Border.       |
|                       | `neutral_300`     | `#D4D4D4`            | Màu xám vừa, dùng cho trạng thái Disabled.           |
|                       | `neutral_500`     | `#737373`            | Màu xám phụ, dùng cho văn bản Secondary / Subtitle.  |
|                       | `neutral_700`     | `#404040`            | Màu xám đậm, dùng cho văn bản nội dung Default.      |
|                       | `neutral_900`     | `#171717`            | Màu đen xám, dùng cho tiêu đề chính Primary.         |
| **Dark Primitive**    | `dark_700`        | `#2C303E`            | Màu nền tối trung cấp cho chế độ Dark Mode.          |
|                       | `dark_800`        | `#1C202B`            | Màu surface tối cho thẻ thông tin trong Dark Mode.   |
|                       | `dark_900`        | `#12151C`            | Màu canvas nền tối tuyệt đối.                        |
| **Brand Orange**      | `orange_50`       | `#FFF7ED`            | Màu cam nhạt (Soft Brand), làm nền Badge/Chip.       |
|                       | `orange_500`      | `#F97316`            | Màu cam thương hiệu (Primary Brand) chính UniEats.   |
| **Status Colors**     | `red_50`          | `#FEF2F2`            | Màu đỏ nhạt, làm nền thông báo lỗi.                  |
|                       | `red_600`         | `#DC2626`            | Màu đỏ đậm, hiển thị trạng thái Error / Closed.      |
|                       | `green_50`        | `#F0FDF4`            | Màu xanh lá nhạt, làm nền thông báo thành công.      |
|                       | `green_600`       | `#16A34A`            | Màu xanh lá đậm, hiển thị trạng thái Success / Open. |
|                       | `amber_600`       | `#D97706`            | Màu vàng hổ phách, hiển thị cảnh báo & Rating sao.   |
| **Standard Colors**   | `black`           | `#FF000000`          | Màu đen chuẩn hệ thống Android.                      |
|                       | `white`           | `#FFFFFFFF`          | Màu trắng chuẩn hệ thống Android.                    |
| **Semantic Tokens**   | `canvas`          | `@color/neutral_50`  | Nền canvas giao diện chế độ sáng (Light Mode).       |
|                       | `surface`         | `@color/neutral_0`   | Nền bề mặt các Card/Item ở Light Mode.               |
|                       | `brand`           | `@color/orange_500`  | Định màu thương hiệu chính toàn ứng dụng.            |
|                       | `brand_soft`      | `@color/orange_50`   | Định màu nền cam nhạt cho các thành phần chọn.       |
|                       | `placeholder`     | `@color/neutral_75`  | Định màu vùng chờ tải dữ liệu/hình ảnh.              |
|                       | `primary`         | `@color/neutral_900` | Định màu chữ tiêu đề chính.                          |
|                       | `secondary`       | `@color/neutral_500` | Định màu chữ phụ, thời gian, phụ chú.                |
|                       | `disabled`        | `@color/neutral_300` | Định màu chữ và nút bị vô hiệu hóa.                  |
|                       | `text_default`    | `@color/neutral_700` | Định màu chữ văn bản nội dung bình thường.           |
|                       | `border_default`  | `@color/neutral_200` | Đường viền ngăn cách giữa các item.                  |
|                       | `border_brand`    | `@color/orange_500`  | Đường viền làm nổi bật thẻ được chọn.                |
|                       | `border_error`    | `@color/red_600`     | Đường viền báo lỗi nhập liệu TextInput.              |
|                       | `error`           | `@color/red_600`     | Màu chữ và icon báo lỗi.                             |
|                       | `error_soft`      | `@color/red_50`      | Nền khung chứa thông điệp lỗi.                       |
|                       | `success`         | `@color/green_600`   | Màu chữ và icon báo thành công.                      |
|                       | `success_soft`    | `@color/green_50`    | Nền khung chứa thông điệp thành công.                |
|                       | `warning`         | `@color/amber_600`   | Màu chữ và icon cảnh báo/Rating.                     |

---

#### b) Chi tiết biến kích thước (Dimension Tokens) trong `values/dimens.xml`

_Bảng 3.2: Danh sách chi tiết các biến kích thước và khoảng cách trong values/dimens.xml_

| Loại biến                 | Tên biến (`name`) | Giá trị (`dp`) | Ý nghĩa & Phạm vi áp dụng                                |
| :------------------------ | :---------------- | :------------- | :------------------------------------------------------- |
| **Spacing (Khoảng cách)** | `spacing_xs`      | `4dp`          | Khoảng cách cực nhỏ (lề icon, padding nội bộ chip).      |
|                           | `spacing_sm`      | `8dp`          | Khoảng cách nhỏ (padding giữa các view con).             |
|                           | `spacing_md`      | `12dp`         | Khoảng cách vừa (margin giữa các item danh sách).        |
|                           | `spacing_lg`      | `16dp`         | Khoảng cách tiêu chuẩn (padding lề màn hình Left/Right). |
|                           | `spacing_xl`      | `24dp`         | Khoảng cách lớn (margin giữa các phân đoạn chính).       |
|                           | `spacing_2xl`     | `32dp`         | Khoảng cách rất lớn (khoảng trống giữa các khối lớn).    |
|                           | `spacing_3xl`     | `48dp`         | Khoảng cách cực lớn (chiều cao nút bấm chuẩn).           |
| **Radius (Bo góc)**       | `radius_sm`       | `4dp`          | Bo góc nhẹ cho ô nhập thông tin / Tag.                   |
|                           | `radius_md`       | `8dp`          | Bo góc vừa cho ảnh thumbnail món ăn.                     |
|                           | `radius_lg`       | `12dp`         | Bo góc chuẩn cho thẻ CardView nhà hàng.                  |
|                           | `radius_xl`       | `16dp`         | Bo góc lớn cho Dialog / BottomSheet.                     |
|                           | `radius_2xl`      | `24dp`         | Bo góc cực lớn cho khung Banner.                         |
|                           | `radius_full`     | `999dp`        | Bo tròn tuyệt đối cho Avatar / Nút tròn.                 |

---

### 3.1.4 Các thành phần giao diện tiêu chuẩn (UI Components)

Ứng dụng được mô-đun hóa với các thành phần tái sử dụng cao:

- **Custom CardView**: Nhóm các thông tin quán ăn, món ăn, đánh giá thành từng khối độc lập với bo góc `radius_lg` (`12dp`).
- **SearchView & Filter Chips**: Thanh tìm kiếm tích hợp các Chip chọn nhanh danh mục (Trà sữa, Món Việt, Ăn vặt,...) giúp thao tác lọc dữ liệu nhanh chóng.
- **Bottom Navigation Bar**: Thanh điều hướng chính cố định dưới đáy màn hình lồng trong `MainActivity`, chuyển đổi mượt mà giữa các **Fragment**: **Trang chủ (`HomeFragment`)**, **Tìm kiếm (`SearchFragment`)**, **Xu hướng (`TrendingFragment`)** và **Trang cá nhân (`ProfileFragment`)**.
- **TextInputLayout & InputValidation**: Hiển thị báo lỗi trực tiếp trên giao diện khi người dùng nhập sai định dạng Email, bỏ trống Mật khẩu hoặc nhập sai giá bán.

---

### 3.1.5 Trực quan hóa dữ liệu & Tìm kiếm / Đánh giá

- **Rating Stars Bar**: Hiển thị số sao đánh giá vàng (`amber_600`) trực quan từ bộ icon **Lucide/Heroicons** cùng điểm số trung bình (ví dụ: `4.8 / 5.0 ★`).
- **Review Photos Grid & Carousel**: Danh sách ảnh thực tế đính kèm trong bài đánh giá được hiển thị dạng lưới thumbnail (`ReviewPhotoAdapter`), cho phép nhấn để mở rộng xem ảnh chất lượng cao.
- **Badge trạng thái & Khoảng giá**: Hiển thị rõ ràng khoảng giá min-max (ví dụ: `25.000đ - 85.000đ`) giúp người dùng cân đối ngân sách cá nhân.

---

### 3.1.6 Thiết kế thích ứng và Tương tác (Responsiveness & UX)

- **FitsSystemWindows & Edge-to-Edge**: Tự động điều chỉnh khoảng trống với thanh trạng thái (Status Bar) và thanh điều hướng hệ thống.
- **NestedScrollView & RecyclerView**: Đảm bảo cuộn danh sách thực đơn và bài đánh giá trong các Fragment mượt mà, không bị xung đột sự kiện vuốt.
- **Swipe-to-Refresh**: Cho phép kéo xuống để làm mới danh sách quán ăn và đánh giá thời gian thực.

---

## 3.2 Các giao diện chính

### 3.2.1 Giao diện Dành cho Người dùng (Customer Mode)

1. **Màn hình Trang chủ (`HomeFragment`)**: Hiển thị thanh tìm kiếm nhanh, banner khuyến mãi nổi bật, danh mục ẩm thực cuộn ngang và danh sách quán ăn đề xuất gần bạn.
2. **Màn hình Tìm kiếm (`SearchFragment`)**: Cho phép nhập từ khóa tìm kiếm, chọn lọc theo khoảng giá, thời gian hoạt động và danh mục tương ứng.
3. **Màn hình Xu hướng (`TrendingFragment`)**: Liệt kê các nhà hàng có điểm đánh giá cao nhất và số lượng bài review áp đảo trong tuần.
4. **Màn hình Chi tiết Quán ăn (`DetailRestaurantActivity`)**: Hiển thị đầy đủ ảnh bìa đại diện, tên quán, địa chỉ, khoảng giá, các nút chức năng (_Lưu yêu thích_, _Đánh giá_, _Chỉ đường trên Google Maps_), và bộ sưu tập Hình ảnh & Thực đơn đính kèm cuộn ngang (`rvAttachedPhotos`).
5. **Màn hình Quán ăn đã lưu (`SavedRestaurantsActivity`)**: Quản lý danh sách các địa điểm mà người dùng đã bấm lưu.
6. **Màn hình Hồ sơ cá nhân (`ProfileFragment`)**: Hiển thị thông tin cá nhân, nút truy cập _Sửa hồ sơ_, _Lịch sử đánh giá_, _Chuyển sang chế độ Chủ quán/Admin_ và _Đăng xuất_.

---

### 3.2.2 Giao diện Đóng góp & Quản lý Quán ăn cá nhân

1. **Màn hình Đóng góp Quán mới (`ContributeRestaurantActivity`)**: Form nhập thông tin quán ăn đề xuất, chọn danh mục món ăn từ danh sách xổ xuống (Dropdown list) và tải ảnh không gian/thực đơn.
2. **Màn hình Quán ăn của tôi (`MyRestaurantActivity`)**: Hiển thị danh sách các quán ăn cá nhân đã đăng ký hoặc được cấp quyền quản lý, hỗ trợ thao tác xóa.

---

### 3.2.3 Giao diện Dành cho Quản trị viên (Admin / Moderator Mode)

1. **Màn hình Quản lý Người dùng (`ManageUserActivity`)**: Danh sách tài khoản hệ thống (`ManageUserAdapter`), cho phép tìm kiếm, đổi vai trò Role và bật/tắt trạng thái hoạt động tài khoản (`is_active`).
2. **Màn hình Quản lý Quán ăn (`ManageRestaurantActivity`)**: Danh sách các quán ăn đăng ký mới, thực hiện duyệt (`APPROVED`), từ chối (`REJECTED`) hoặc tạm đóng cửa (`CLOSED`).
3. **Tích hợp Thống kê Báo cáo**: Các chỉ số thống kê người dùng (tổng số, đang hoạt động, tạm dừng) và thống kê quán ăn (tổng số, chờ duyệt, đã duyệt, từ chối) được tích hợp trực tiếp trên đầu màn hình `ManageUserActivity` và `ManageRestaurantActivity` giúp quản trị viên dễ dàng theo dõi.

---

### 3.2.4 Giao diện Đánh giá & Đóng góp Quán ăn

1. **Màn hình Viết Đánh giá (`WriteReviewActivity` & `ReviewActivity`)**: Cho phép chọn số sao rating, nhập nhận xét bình luận và chọn nhiều ảnh từ bộ nhớ thiết bị để tải lên.
2. **Màn hình Đóng góp Quán mới (`ContributeRestaurantActivity`)**: Form nhập thông tin quán ăn đề xuất từ người dùng để gửi lên Server cho Admin duyệt.

---

## 3.3 Luồng xử lý hoạt động

```
[Khởi động ứng dụng (Splash Screen)]
                 |
                 v
    <Kiểm tra Đăng nhập? (AuthManager)>
       /                         \
    (Chưa)                      (Đã Đăng nhập)
     /                             \
[SigninActivity]             <Kiểm tra Role người dùng>
     |                             /               \
     +--> [SignupActivity]      USER              ADMIN
     |                            |                 |
     +--> [PasswordRestore]       v                 v
                                [Home]         [AdminDash]
                              (Fragments)
```

_Hình 3.1: Luồng điều hướng và xử lý phân quyền người dùng trong UniEats_

Khi ứng dụng UniEats được khởi động:

1. `AuthManager` kiểm tra xem phiên làm việc trước đó có hợp lệ hay không.
2. Nếu chưa đăng nhập, chuyển hướng người dùng đến `SigninActivity`.
3. Khi đăng nhập thành công, hệ thống đọc thuộc tính `role` của tài khoản từ Firestore (`USER`, `MODERATOR`, `ADMIN`).
4. Hệ thống điều hướng đến `MainActivity`, tự động tải các **Fragment** tương ứng với vai trò và quyền hạn được cấp.

---

## 3.4 Xử lý chức năng

### 3.4.1 Xử lý đăng nhập và xác thực

Hệ thống kết hợp **Firebase Authentication** và **SharedPreferences** qua class `AuthManager`:

- Khi đăng nhập thành công, thông tin ID người dùng và cờ `KEY_IS_LOGGED_IN` được lưu cục bộ giúp tự động đăng nhập ở các lần khởi động sau.
- Chức năng Đăng xuất (`AuthManager.logout()`) thực hiện xóa Session cục bộ, đăng xuất khỏi Firebase Auth và dọn dẹp Stack Activity để quay về màn hình Signin.

---

### 3.4.2 Xử lý dữ liệu quán ăn và menu

Dữ liệu nhà hàng và thực đơn được quản lý trên **Cloud Firestore**:

- `FirebaseManager` cung cấp các phương thức truy xuất Collection Reference (`getRestaurantsRef()`, `getMenusRef()`).
- Khi người dùng tải ảnh món ăn hoặc ảnh bìa nhà hàng khi đóng góp, ứng dụng gọi `CloudinaryManager.uploadImage()`. Ảnh được đẩy trực tiếp lên Cloudinary, trả về URL CDN an toàn lưu vào Firestore document.

---

### 3.4.3 Xử lý tìm kiếm và lọc dữ liệu

- `SearchFragment` lắng nghe sự kiện thay đổi văn bản trên `SearchView`.
- Hệ thống lọc dữ liệu trên danh sách quán ăn theo từ khóa tên quán, địa chỉ hoặc slug.
- Kết hợp lọc điều kiện theo khoảng giá (`price_range_min <= targetPrice <= price_range_max`) và thuộc tính danh mục (`category_id`).

---

### 3.4.4 Xử lý đánh giá và tính toán Rating trung bình

Khi bài đánh giá mới được gửi thành công:

1. Bản ghi bài viết được thêm vào Collection `reviews`.
2. Danh sách hình ảnh review được upload qua `CloudinaryManager` và ghi vào Collection `review_photos`.
3. Ứng dụng kích hoạt thủ tục tính toán lại số sao trung bình:
   $$\text{Rating}_{avg} = \frac{\sum \text{Rating}_{reviews}}{\text{Total Reviews}}$$
4. Cập nhật ngay lập tức trường `rating_avg` và `review_count` trên document của quán ăn trong Firestore.

---

### 3.4.5 Xử lý lưu quán yêu thích và Đóng góp quán mới

- Thao tác lưu/bỏ lưu thực hiện thêm hoặc xóa document tương ứng trong Sub-collection `users/{userId}/saved_restaurants`.
- Form đóng góp quán ăn mới khởi tạo document trong Collection `contributions` với trạng thái ban đầu là `'PENDING'`.

---

### 3.4.6 Đồng bộ dữ liệu và Quản trị hệ thống

- Các Admin/Moderator truy cập giao diện quản trị có thể thay đổi trạng thái của `contributions` từ `'PENDING'` sang `'APPROVED'`.
- Khi đóng góp được duyệt, hệ thống tự động khởi tạo bản ghi nhà hàng tương ứng trong Collection `restaurants` và gửi thông báo hoàn tất.

---

\newpage

# CHƯƠNG 4 KẾT QUẢ VÀ ĐÁNH GIÁ

## 4.1 Kết quả đạt được

Sau quá trình nghiên cứu lý thuyết, phân tích hệ thống và tiến hành thực nghiệm phát triển mã nguồn, đồ án **UniEats – Ứng dụng Tìm kiếm & Gợi ý Ẩm thực** đã hoàn thành đầy đủ các mục tiêu đề ra:

1. **Xây dựng ứng dụng Android hoàn chỉnh**: Sản phẩm vận hành ổn định, giao diện tùy biến hiện đại, tích hợp kiến trúc Fragment điều hướng linh hoạt, tương thích tốt trên các thiết bị di động Android.
2. **Triển khai thành công đầy đủ các phân hệ chức năng**:
   - Phân hệ Xác thực & Quản lý tài khoản bảo mật (Firebase Auth).
   - Phân hệ Tìm kiếm, Lọc quán ăn thông minh qua `SearchFragment` theo đa tiêu chí.
   - Phân hệ Đánh giá (Review), Chấm điểm sao và Upload nhiều hình ảnh thực tế qua Cloudinary.
   - Phân hệ Lưu danh sách quán yêu thích và Đóng góp quán mới từ cộng đồng.
   - Phân hệ Đóng góp & Kiểm duyệt Thực đơn/Nhà hàng.
   - Phân hệ Quản trị & Xét duyệt nội dung dành cho Admin/Moderator.
3. **Chuẩn hóa kiến trúc mã nguồn & Cơ sở dữ liệu**:
   - Áp dụng thành công mô hình MVVM kết hợp Fragment & Helper Pattern giúp mã nguồn rõ ràng, dễ bảo trì.
   - Chuẩn hóa hệ thống giao diện trong `values/` và `values-night/` với đầy đủ các biến màu sắc, kích thước, chủ đề.
   - Tích hợp bộ biểu tượng vector **Heroicons** và **Lucide Icons** cho trải nghiệm thị giác hiện đại.
   - Thiết kế lược đồ cơ sở dữ liệu NoSQL/Relational hoàn chỉnh với 10 bảng/collections chuẩn hóa.

---

## 4.2 Ưu điểm

- **Giải pháp Cloud-Mobile hiện đại**: Việc kết hợp giữa Firebase Firestore (lưu dữ liệu thời gian thực) và Cloudinary (tối ưu hóa truyền tải hình ảnh) giúp ứng dụng hoạt động nhanh, mượt mà và không tốn chi phí hạ tầng máy chủ riêng.
- **Kiến trúc Fragment & Tùy biến UI linh hoạt**: Sử dụng các Fragment (`HomeFragment`, `SearchFragment`, `TrendingFragment`, `ProfileFragment`) giúp chuyển đổi màn hình cực nhanh, không hao tốn tài nguyên thiết bị và không phụ thuộc thư viện bên ngoài.
- **Bộ biểu tượng sắc nét (Heroicons & Lucide Icons)**: Đồng bộ thiết kế icon hiện đại, tối ưu chuẩn Vector Drawable.
- **Trải nghiệm người dùng (UX) tối ưu**: Giao diện trực quan, tìm kiếm linh hoạt, hình ảnh hiển thị sinh động.
- **Phân quyền người dùng chặt chẽ (Role-Based Access Control)**: Phân định rành mạch quyền hạn giữa Người dùng cuối và Ban quản trị hệ thống.

---

## 4.3 Hạn chế

Mặc dù đạt được nhiều kết quả tích cực, ứng dụng vẫn còn một số điểm hạn chế cần cải thiện:

- **Chưa tích hợp bản đồ trực tuyến (Google Maps API)**: Việc hiển thị vị trí hiện tại mới dừng lại ở địa chỉ dạng văn bản, chưa trực quan hóa đường đi trực tiếp trên bản đồ tương tác.
- **Thuật toán gợi ý ở mức cơ bản**: Việc đề xuất quán ăn chủ yếu dựa trên điểm số đánh giá và lượt review, chưa áp dụng các mô hình Học máy (Machine Learning / Recommendation System) để phân tích sâu thói quen tiêu dùng cá nhân.
- **Ứng dụng thuần Native Android**: Chưa hỗ trợ nền tảng iOS hoặc phiên bản Web Dashboard cho chủ quán quản trị trên máy tính.

---

## 4.4 Hướng phát triển

Từ các hạn chế đã nhận diện, nhóm đề xuất hướng mở rộng dự án UniEats trong thời gian tới:

1. **Tích hợp Google Maps SDK & Routing API**: Hiển thị bản đồ trực quan, hỗ trợ tính toán khoảng cách thực tế và định vị chỉ đường từ vị trí hiện tại của người dùng đến quán ăn.
2. **Ứng dụng Trí tuệ Nhân tạo (AI)**: Triển khai thuật toán Gợi ý thông minh (Collaborative Filtering / Content-based Filtering) để đề xuất món ăn và nhà hàng phù hợp chính xác với sở thích cá nhân từng người dùng.
3. **Tích hợp Cổng thanh toán & Đặt bàn trước**: Mở rộng tính năng đặt chỗ (Table Reservation) và thanh toán tiền cọc qua ví điện tử (MoMo, VNPay, ZaloPay).
4. **Phát triển ứng dụng Đa nền tảng (Flutter / React Native)**: Mở rộng phiên bản ứng dụng trên hệ điều hành iOS và Web Admin Dashboard.

---

\newpage

# TÀI LIỆU THAM KHẢO

[1] Android Developers, _"Guide to app architecture & Fragment lifecycle,"_ Google, 2024. [Online]. Available: https://developer.android.com/topic/architecture

[2] Firebase Documentation, _"Firebase Documentation for Android,"_ Google, 2024. [Online]. Available: https://firebase.google.com/docs/android/setup

[3] Cloudinary Ltd., _"Cloudinary Android SDK Documentation,"_ Cloudinary, 2024. [Online]. Available: https://cloudinary.com/documentation/android_integration

[4] Heroicons & Lucide Icons, _"SVG Vector Icons Library for Modern Web & Mobile Apps,"_ 2024. [Online]. Available: https://heroicons.com / https://lucide.dev

[5] Glide Documentation, _"Glide: An image loading and caching library for Android,"_ Bumptech, 2024. [Online]. Available: https://bumptech.github.io/glide/

---
