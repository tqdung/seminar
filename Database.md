DATABASE
===========
Database
---------
- Hiểu đơn giản thì `database` là mọi thứ người dùng thao tác trên internet hằng ngày, khi mở một trang web lên thì database là tệp những hình ảnh, mô tả, những thông tin đăng nhập, list nhạc

SQL
--------
- `SQL(Structured Query Language)` là một ngôn ngữ truy vấn có cấu trúc
- SQL, hay tất cả các hệ thống quản lý cơ sở dữ liệu đều dựa trên `RDBMS(Relational Database Management System)`. Trong RDBMS dữ liệu được biểu diễn bởi các `line(hàng/dòng)`. Chứa các `bảng` và mỗi bảng thường sẽ có các `Primary Key` riêng để giúp dễ dàng truy cập dữ liệu
- Trong SQL, dữ liệu sẽ được lưu dưới dạng `Table(bảng)`, mỗi bảng sẽ được định nghĩa bởi `Field(trường)` `Line/records(hàng)` `Column(cột)` và các `Key (Primary key, Foreign key)`
### Constraint
- SQL sử dụng các `Constraint(ràng buộc)` để đặt các quy tắc trong bảng, các ràng buộc phổ biến và có sẵn trong SQL:
    + `NOT NULL` Đảm bảo cột này không thể có giá trị NULL
    + `DEFAULT` Cung cấp một giá trị đã xác định khi không có dữ liệu truyền vào
    + `UNIQUE` Đảm bảo tất cả giá trị trong một cột là khác nhau
    + `PRIMARY KEY` Dùng để nhận diện theo một cách duy nhất
    + `FOREIGN KEY` Khóa ngoại được nhận diện duy nhất trong một bảng khác
    + `CHECK` Đảm bảo giá trị của cột phải thỏa mãn điều kiện nào đó
    + `INDEX` Sử dụng để tạo và lấy dữ liệu từ DB nhanh chóng
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
    + `UNIQUE` tìm kiếm tính đơn nhất trong mỗi row của một bảng đã cho
### Comparison & arithmetic operator
- SQL hỗ trợ các toán tử so sánh gần giống với các ngôn ngữ lập trình khác như: `=` `!=` `<>` `>` `<` `>=` `<=` `!>` `!<`, và các toán tử số học: `+` `-` `*` `/` `%`
### Alias
- Sử dụng từ khóa `AS` để đặt `Alias (Định danh)` cho một bảng hoặc một trường trong bảng `SELECT price AS money` hay `SELECT * FROM products AS PR`
### Join
- `INNER JOIN` trả về kết quả các record mà hai bảng khớp nhau
- `OUTER JOIN` lấy về các record xuất hiện 1 trong 2 bảng
- `CROSS JOIN` trả về kết quả là tích của 2 bảng
- `LEFT/RIGHT JOIN` trả về kết quả là record xuất hiện 2 bảng nhưng ưu tiên cho bên trái/phải (left/right)
### Subquery
- SQL hỗ trợ các query lồng nhau để thực hiện các cú pháp query phức tạp hơn mà cách select bình thường khó có thể lọc được, `SELECT * FROM A WHERE A.SOMETHING IN (SELECT B.SOMETHING FROM B)`, tương đương với lọc ra những dữ liệu từ bảng A mà có trong bảng B
### CTE
- `SQL CTE (Common Table Expression)` nhằm giúp người viết đơn giản việc query những câu có nhiều joinss và subqueries, CTE dùng để sử dụng kết quả của một query khác nhiều lần trong cùng 1 query, dùng CTE bằng từ khóa `WITH`, sau đó một bảng tạm sẽ được tạo ra để người dùng có thể gọi lại `WITH st (SELECT * FROM AB)`
### Set Operator
- `UNION | UNION ALL` ,toán tử `UNION` giúp người dùng kết hợp kết quả của 2 query, sau đó trả về những record của cả 2 query `Select * From A UNION Select * From B`, nhưng với `UNION ALL` thì kết quả trả về sẽ có cả những kết qủa trùng nhau
- `INTERSECT` là toán tử trả về những kết quả trùng nhau (giao nhau) giữa 2 query cú pháp tương tự UNION
- `MINUS` là toán tử trừ, ta có query A MINUS query B sẽ cho ra kết quả của A mà B không có

MySQL
---------
- MySQL là một hệ quản trị cơ sử dữ liệu open source, tốc độ cao, ổn định và dễ sử dụng, hoạt động được trên nhiều OS khác nhau như UNIX, Linux, Windows, MacOS,....
- MySQL sử dụng ngôn ngữ truy vấn SQL
- Về căn bản MySQL cũng có các lệnh lọc dữ liệu `SELECT FROM WHERE` hay thao tác dữ liệu `INSERT` `UPDATE` `CREATE` `DELETE`, toán tử giống bên SQL như `ALL` `AND` `ANY` `IN`,...  cũng có các Constraint `PRIMARY KEY` `FOREIGN KEY` `UNIQUE` `INDEX`,...
- MySQL hỗ trợ thêm từ khóa như:
    + `LIMIT` để hạn chế số record được lấy lên
    + `INSERT IGNORE`, Với `INSERT` khi insert nhiều rows mà có 1 rows bị lỗi thì sẽ return về lỗi và không có dòng nào được insert vào, với `INSERT IGNORE` thì những dòng nào bị lỗi sẽ bị bỏ qua, những dòng đúng vẫn được insert vào db 
- Còn lại hầu hết MySQL đều giống SQL, và có những `Procedure`, `Triggers`, `Views`,...

JDBC
----------
- JDBC là bộ API cung cấp các interface cho người dùng bao gồm các thành phần
    1. JDBC Driver
    2. Connection
    3. Statement
    4. ResultSet
- Flow của JDBC: Load Driver & khởi tạo Connection > Tạo một Statement và thực hiện query > Sử dụng và xử lý các data > Đóng/hủy ResultSet, Statement, Connection
### JDBC Driver
- `JDBC driver` là tập hợp các `class của Java` tương tác với một `CSDL cụ thể `dựa trên `MySQL`
### Connection
- Trong Java App, thông qua `Connection object`, người dùng có thể tương tác với CSDL
### Statement 
- Để thực hiện một câu `query`, người dùng cần sử dụng một `Statement object` thông qua `Connection object`
### ResultSet
- Sau khi thực hiện một lệnh query từ DB, người dùng sẽ nhận được ResultSet object. ResultSet object sẽ cung cấp các API cho phép truy cập kết quả của lệnh query
### Connecting to MySQL
- Để connect vào MySQL bằng JDBC ta cần thực hiện các lệnh
* Khai báo Connection : `Connection conn = null;`
* khai báo các param:
`String url = "jdbc:mysql://localhost:3306/mysqljdbc";`
`String user = "root";`
`String password = "secret";`
Với `localhost` là `servername` ,`3306` là `port` và `mysqljdbc` là `tên database`, `user` và `password` là tùy theo cấu hình MySQL của người dùng
* Tạo connection: `conn = DriverManager.getConnection(url,user,password);`
Nên sử dụng `SQLException` để bắt lỗi của SQL vì có thể sai các thông số ở trên
- Khi thao tác xong với db thì ta sử dụng `conn.close();` để đóng kết nối Connection
### Query Data MySQL
- Tao một `Statement object` thông qua Connection object: `Statement stmt = conn.createStatement();`
- Sau khi tạo Statement object, người dùng có thể đưa vào lệnh query để thực hiện với DB và nhận kết quả bằng `ResultSet`:
`ResultSet rs = stmt.executeQuery("Select * From a_table);`
hoặc `String sql = "Select * From a_table;`
     `ResultSet rs = stmt.executeQuery(sql);`
- Sau khi có `ResultSet`, người dùng có thể lấy dữ liệu để thao tác bằng function `next()` để lấy một record hoặc `getString("fieldname")` để lấy trường `fieldname`
- Khi thao tác xong thì đóng các object `rs.close();` `stmt.close()`
### 
- Ngoài ra khi muốn thực hiện các lệnh `non-query` như `INSERT` `UPDATE` `DELETE` thì người dùng cần sử dụng class `PreparedStatement`. Thao tác: `String sqlInsert = "insert into skills values(6,'C++')";` để tạo 1 insert mới vô bảng `skills` với giá trị `id = 6` và `name = "C++"`, tạo 1 preparedstatement `PreparedStatement pstm = conn.prepareStatement(sqlInsert);`, execute các dòng lệnh đã được đưa vào preparedstatement `pstm.executeUpdate();` 
### Batch query
- Khi muốn thực hiện `non-query` một loạt nhiều dòng khác nhau thì người dùng cần sử dụng tới `Batch`
- Khi muốn Insert nhiều dòng thì câu lệnh sql sẽ chứa giá trị là `?` 
```
String sql = "insert into skills values(?)";
PreparedStatement pstm = conn.prepareStatement(sql);
pstm.setString("New name");
pstm.addBatch();
```
- Với mỗi lần `set` và `addBatch` thì đã có một record được thêm vào. để Update lại CSDL cần sử dụng `pstm.executeBatch()`. Các lệnh `UPDATE` `DELETE` cũng thực hiện tương tự
### Return generated id
- Khi thực hiện một lệnh `INSERT`, người dùng có thể trả vệ một ID mà hệ thống tự tạo ra trong CSDL, vậy để lấy và sử dụng ID đó, JDBC có hỗ trợ sử dụng
```
String sql = "insert into skills values(?)";
PreparedStatement pstm = conn.prepareStatement(sql);
pstm.setString("New name");
pstm.executeUpdate();
//Get the ID
ResultSet rs = pstm.getGeneratedKeys();
rs.next();
int newID = rs.getLong(1);
```
### Connection pooling
- Trọng JDBC có hỗ trợ `Connection pooling`, là một nhóm các connection được tạo ra khi khai báo
- Mục đích sử dụng Connection pooling là để lưu lại các Connection có thể sử dụng lại thay vì hủy connection sau khi xong công việc
- Khai báo connection pooling, cần phải có class ComboPooledDataSource
```
comboPooledDataSource = new ComboPooledDataSource();
comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/testdb");
comboPooledDataSource.setUser("root");
comboPooledDataSource.setPassword("secret");
```