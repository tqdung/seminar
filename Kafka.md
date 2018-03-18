KAFKA
==============
- `Kafka (Apache Kafka)` là một hệ thống truyền `message` phân tán, cung cấp chỉ số `offset` để lấy `message` Một cách linh hoạt
- Kafka thường được sử dụng cho các việc `thu thập dữ liệu thời gian thực`
- Đặc điểm của Kafka:
    + Tốc độ: Kafka có thể xử lý đọc và ghi lên tới hàng trăm GB trong một giây trên một máy
    + Mở rộng: Kafka không có thời gian chết, chỉ ngừng hoạt động khi thêm một `Cluster` mới
    + Tin cây: `Data` từ `Queue` sẽ được lưu trên ổ đĩa và copy tới các `Cluster` khác để ngăn ngừa việc mất Data
- Apache Kafka được xây dựng kết hợp với `Apache Zookeeper`