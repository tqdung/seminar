REST và RESTful
============
- ` REST(REpresentational State Transfer)` là một bộ các ràng buộc và quy ước , khi áp dụng đầy đủ vào hệ thống thì ta có 1 hệ thống REST
+ Hệ thống hoạt động theo mô hình client-server, trong đó server là tập hợp các service nhỏ lắng nghe các request từ client.
+ Stateless (phi trạng thái). Server và client không lưu trạng thái của nhau -> mỗi request lên server thì client phải đóng gói thông tin đầy đủ để thằng server hiểu được.
+ Khả năng caching : Các response có thể lấy ra từ cache. Bằng cách cache các response , server giảm tải việc xử lý request
+ Chuẩn hóa các interface : Đây là một trong những đặc tính quan trọng của hệ thống REST. Bằng cách tạo ra các quy ước chuẩn để giao tiếp giữa các thành phần trong hệ thống, đơn giản hóa việc client có thể tương tác với server.
+ Phân lớp hệ thống : trong hệ thống REST, chia tách các thành phần hệ thống theo từng lớp, mỗi lớp chỉ sử dụng lớp ở dưới nó và giao tiếp với lớp ở ngay trên nó 
