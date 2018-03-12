DATABASE
===========
Database
---------
- Hiểu đơn giản thì `database` là mọi thứ mình thao tác trên internet hằng ngày, khi mở một trang web lên thì database là tệp những hình ảnh, mô tả, những thông tin đăng nhập, list nhạc

SQL
--------
- `SQL(Structured Query Language)` là một ngôn ngữ truy vấn có cấu trúc
- SQL, hay tất cả các hệ thống quản lý cơ sở dữ liệu đều dựa trên `RDBMS(Relational Database Management System)`. Trong RDBMS dữ liệu được biểu diễn bởi các `line(hàng/dòng)`. Chứa các `bảng` và mỗi bảng thường sẽ có các `Primary Key` riêng để giúp dễ dàng truy cập dữ liệu
- Trong SQL, dữ liệu sẽ được lưu dưới dạng `Table(bảng)`, mỗi bảng sẽ được định nghĩa bởi `Field(trường)` `Line/records(hàng)` `Column(cột)` và các `Key (Primary key, Foreign key)`
### Constraint
- SQL sử dụng các `Constraint(ràng buộc)` để đặt các quy tắc trong bảng, các ràng buộc phổ biến và có sẵn trong SQL:
    + NOT NULL: Đảm bảo cột này không thể có giá trị NULL
    + DEFAULT: Cung cấp một giá trị đã xác định khi không có dữ liệu truyền vào
    + UNIQUE: Đảm bảo tất cả giá trị trong một cột là khác nhau
    + PRIMARY KEY: Dùng để nhận diện theo một cách duy nhất
    + FOREIGN KEY: Khóa ngoại được nhận diện duy nhất trong một bảng khác
    + CHECK: Đảm bảo giá trị của cột phải thỏa mãn điều kiện nào đó
    + INDEX: Sử dụng để tạo và lấy dữ liệu từ DB nhanh chóng
### NULL
- Giá trị `NULL` của một bảng xác định rằng trường này bị đã bị `để trống` trong khi tạo record. Khác hòan toàn với giá trị `0` hoặc chứa `khoảng trống (space)`, lúc này 0 và space vẫn đc xem là giá trị
### SQL Basic Syntax
- Trong SQL không phân biệt kiểu chữ, có thể ghi CREATE hoặc create tùy ý
- Cú pháp cơ bản nhất của SQL là `SELECT Col1, Col2 FROM Table1 Where Condition` có ý nghĩa show dữ liệu `cột 1,2` của bảng `Table1` theo điều kiện `Condition`, có thể dùng `Select *` để show hết tất cả dữ liệu của một record, dùng `SELECT DISTINCT` để đưa ra các dữ liệu không trùng lắp. Trong Where có thể sử dụng `AND/OR` để xét thêm các điều kiện khác. Ngoài ra còn có thể sử dụng một số từ khóa khác để lọc dữ liệu như `IN` `BETWEEN` `LIKE` `ORDER BY` `GROUP` `COUNT` `HAVING`
- CREATE TABLE trong SQL 
`CREATE TABLE [TableName](
Col1 DataType, 
Col2 DataType,
.....,
ColN DataType,
PRIMARY KEY(1 or N Col)
);`