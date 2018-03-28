### Servlet
- là API 
- Là các chương trình chạy trên web server
- Tầng trung gian giữa HTTP Client với HTTP Server/Database
- Chạy bên trong server, không cần tạo process mới
- Chạy độc lập
- Bảo mật (vì được viết bằng Java / Java lại cung cấp được tính bảo mật cao)
- Servlet được tạo khi được gọi lần đầu và tồn tại đến khi kết thúc (destroy)
- init() -> services() (doGet(), doPost()) -> destroy()

### Jetty
- Use version 9.4 (Support HTTP/2)
- Nhúng web server vào app (server die sẽ không ảnh hưởng tới các server khác, standard alone)
- join(), để block thread lại nếu server không chạy được, không có join thì jetty vẫn hoạt động bình thường
- Jetty có hỗ trợ mã hóa dữ liệu và mã hóa password riêng
