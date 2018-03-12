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
- DROP TABLE trong SQL `DROP TABLE table_name`
- CREATE INDEX trong SQL `CREATE UNIQUE INDEX index_name ON table_name(col1,col2,...colN)`
- DROP INDEX trong SQL `ALTER TABLE table_name DROP INDEX index_name`
- ALTER TABLE trong SQL `ALTER TABLE table_name {ADD|DROP|MODIFY} col_name {type}`
                        `ALTER TABLE table_name RENAME TO new_table_name`
- INSERT INTO trong SQL `INSERT INTO table_name(col1,col2...colN) VALUES(vl1,vl2,...vlN)`
- UPDATE trong SQL `UPDATE table_name SET col1 = val1,..coln = valN [Where = Condi]`
- DELETE trong SQL `DELETE FROM table_name WHERE {Condition}`
### Logic operator
- Một số các toán tử logic có sẵn trong SQL
    + `ALL` được sử dụng để so sánh một giá trị với tất cả giá trị trong tập giá trị khác
    + `AND` cho phép sự tồn tại của nhiều điều kiện trong mệnh đề WHERE của một lệnh SQL
    + `ANY` được sử dụng để so sánh một giá trị với bất kỳ giá trị thích hợp nào trong danh sách tùy theo điều kiện
    + `BETWEEN` được sử dụng để tìm các giá trị mà là trong một tập giá trị, được cung cấp giá trị nhỏ nhất và giá trị lớn nhất
    + `EXISTS`được sử dụng để tìm sự có mặt của một row trong một bảng đã cho mà thỏa mãn điều kiện cụ thể
    + `IN` được sử dụng để so sánh một giá trị với một danh sách các giá trị hằng mà đã được xác định
    + `LIKE` được sử dụng để so sánh một giá trị với các giá trị tương tự bởi sử dụng các toán tử Wildcard
    + `NOT` đảo ngược ý nghĩa của toán tử logic khi được sử dụng cùng với toán tử logic đó
    + `OR` được sử dụng để kết hợp nhiều điều kiện trong mệnh đề WHERE của một lệnh SQL
    + `IS NULL` được sử dụng để so sánh một giá trị với một giá trị NULL
    + UNIQUE tìm kiếm tính đơn nhất trong mỗi row của một bảng đã cho
### Comparison & arithmetic operator
- SQL hỗ trợ các toán tử so sánh gần giống với các ngôn ngữ lập trình khác như: `=` `!=` `<>` `>` `<` `>=` `<=` `!>` `!<`. Và các toán tử số học: `+` `-` `*` `/` `%`