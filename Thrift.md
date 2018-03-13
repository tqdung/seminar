THRIFT(JAVA)
==============
-`Thrift` là `IDL (interface definition language)` và là một giao thức (protocol) truyền tải dữ liệu dưới dạng `nhị phân (binary)`. Là opensource thuộc `Apache`
- Thrift cho phép người dùng định nghĩa `datatypes`, `service interface` bằng ngôn ngữ trung lập (file định dạng .thrift). Sau đó `Thrift compiler` sẽ build các file này thành file class. Ở server, người dùng sẽ implement method từ file class Thrift cho client gọi thông qua service interface được định nghĩa
- Hiện thrift hỗ trợ các ngôn ngữ `C++` `Java` `PHP` `Python` `Ruby`
- Tóm lại thirft cung cấp các giao thức để giao tiếp giữa client với server

### Thrift compiler
-`Thrift compiler` là tool được sử dụng để build file .thrift thành các class file tương ứng mà người dùng có thể cài đặt

### Type
- Các loại dữ liệu mà Thrift đơn giản: `bool` `byte` `i16` `i32` `i64` `double` `string`, ngoài ra còn có kiểu `binary`

### Containers
- `Containers` của Thrift gồm `list` `set` và `map`
    + `list` tương tự vector của c++, ArrayList của Java,...
    + `set` tương tự như set của C++, HashSet của Java,...
    + `map` tương tự map của C++ và HashMap Java,...

### Services
- Một service là một set các tên function(hàm), mỗi hàm có các parameter và kiểu dữ liệu: `service NewService{void set(1: i32 key, 2: string value), string get1:i32 key()}` như ví dụ đây là một service tên `NewService` với 2 hàm là `get` `set` với param trong hàm

### Writing thrift
- Tạo file một file `testservice.thrift` sau đó demo các lệnh 
```
namespace java tutorial
namespace py tutorial
typedef i32 int
service TestService
{
    int testService(1: int n1, 2:int n2);
}
```
Generate code ra, ở đây gen ra ngôn ngữ Java thì sẽ thực hiện lệnh này trong cmd `thrift --gen java testservice.thrift` sẽ thấy file `gen-java` > `tutorial` folder > `TestService.java`. Copy vào trong thư mục `src` của Project và có thể sử dụng thrift
- Bây giờ đã có thể sử dụng `TestService` bằng cách implements `TestService`