NETWORKING
===========
### Protocol
- `Protocol` (giao thức) là một tập hợp các quy tắc chuẩn dành cho việc biểu diễn dữ liệu, phát tín hiệu,
chứng thực và phát hiện lỗi dữ liệu
- Các máy tính và thiết bị muốn trao đổi thông tin với nhau đều phải có một protocol
- Các protocol thường sẽ được chia thành nhiều bước, bên gửi sẽ thực hiện từ trên xuống để gửi,
còn bên nhận sẽ thực hiện từ dưới lên để nhận
- Một số giao thức thông dụng như: `TCP/IP, HTTP, POP3, FTP, SMTP` (Các chữ "P" trong này là Protocol)
- Giao thức không kết nối: Không giúp kiểm soát đường truyền, bên `gửi` gửi đi dưới dạng gói dữ liệu (`datagrams`) và mặc kệ bên `đích` có nhận được không
- Giao thức kết nối: Kiểm soát đường truyền, dữ liệu gửi đi tuần tự (`sequence datagrams`), bên nhận sẽ gửi file `ACK` khi nhận được
- Giao thức còn chia thành các giao thức định tuyến/không định tuyến để cho phép/không cho phép đi qua các thiết bị mạng như router

### IP
- `IP` (Internet Protocol) là một giao thức truyền thông chính trên internet 
- IP là giao thức hướng dữ liệu sử dụng bởi host nguồn và đích để truyền dữ liệu liền mạch
- Các dữ liệu mà IP gửi là các `gói` (`packet/datagram`)
- IP truyền tải dữ liệu `không đảm bảo`, cố gắng truyền đi một cách nhanh nhất
- Hiện nay thế giới thường sử dụng `IPv4` và `IPv6`
- IPv4 sử dụng 32bit thiết lập địa chỉ, còn IPv6 sử dụng 128bit. 
- Giao thức IP có trách nhiệm đặt `địa chỉ` cho các host, `đóng gói dữ liệu` vào các gói tin và `định tuyến` từ host nguồn sang host đích
- Mỗi gói tin sẽ có 2 phần là `header` và `payload`. Header chứ `IP nguồn` và `IP đích` và các `siêu dữ liệu` (`metadata`) để định tuyến

### Socket
- `Socket` là một `cổng logic` để một chương trình/máy dùng để kết nối với chương trình/máy khác.
- Mỗi luồng socket bắt buộc phải có một `port` 
- Socket có thể một lúc nhiều luồng nhưng phải khác `số hiệu cổng` (`port`)
- Socket sử dụng 2 `giao thức truyền tải` là `UDP` và `TCP`
- Socket hoạt động theo các mô hình như `Client - Server`, `Peer - to - peer`
- Các chương trình/máy muốn kết nối với nhau bằng socket bắt buộc phải biết port Socket và IP của chương trình/máy còn lại

### TCP
- `TCP (Tranmission Control Protocol)` là giao thức giúp các ứng dụng trên các host có thể tạo kết nối với nhau để trao đổi dữ liệu hoặc gói tin
- TCP là tầng trung gian giữa giao thức IP và ứng dụng.
- khác với `UDP`, TCP đòi hỏi việc phải thiết lập kết nối trước khi bắt đầu gửi dữ liệu 
- TCP là một giao thức truyền tin đáng tin cậy: đảm bảo gói tin sẽ đến đúng được đích và tuần tự
- Flow: Ứng dụng `nguồn` gửi cá dòng 8-bit tới TCP -> TCP phân chia dòng này thành các đoạn (`segment`) -> TCP chuyển các segment này tới giao thức IP để  giao thức IP tiếp tục chuyển tới máy `đích` -> Khi bên máy đích nhận được sẽ gửi lại file `ACK` cho bên nguồn để báo thành công
- Các segment sẽ được TCP đánh dấu số thứ tự (`sequence number`) để hỗ trợ việc đảm bảo gói tin truyền đi không bị thất lạc và đảm bảo dữ liệu được trao cho ứng dụng theo đúng thứ tự
- Sẽ có một bộ `timer` để kiểm tra `time-out`. Nếu vượt quá thời gian mà vẫn chưa nhận được file trong khoảng thời gian thì TCP sẽ tự động truyền tải lại.
- TCP sẽ sử dụng `checksum` để kiểm tra file có bị hỏng trong lúc truyền tải không
- TCP sử dụng quy trình bắt tay 3 bước (`3-way handshake`) để thiết lập kết nối: Client gửi file request (`SYN, SequenceNumber = x`) tới server -> server response (`SYN + ACK(x+1), SequenceNumber =y `) -> client gửi tiếp tục file `ACK(y+1)` xác nhận. Sau 3 bước này kết nối giữa client và server đã được thiết lập 
- Đặc điểm của TCP:
	+ Truyền dữ liệu không lỗi (cơ chế tự sửa lỗi/ truyền lại)
	+ Truyền các gói dữ liệu theo đúng thứ tự 
	+ Truyền lại các gói dữ liệu mất trên đường truyền
	+ Loại bỏ các gói dữ liệu trùng lắp
	+ Hạn chế tắc nghẽn đường truyền
- TCP sử dụng các port để định danh các ứng dụng gửi và nhận dữ liệu 
- Một gói tin của TCP gồm 2 phàn chính là header (20bytes) và data. Phần header có các trường gồm: 	+ Source port, Destination port, Sequence number, ACK number, Data offset, Reserved, Flags (URG, ACK, PSH, RST, SYN, FIN), Window, Checksum và option, option là trường không bắt buộc phải có dữ liệu.

### HTTP
- `HTTP (HyperText Transfer Protocol)` là giao thức truyên tải siêu văn bản, là một giao thức ứng dụng trong bộ các giao thức TCP/IP
- HTTP hoạt động dựa trên mô hình `Client - Server`. Lúc này Client sẽ là máy của người dùng sử dụng `browser` để truy cập web
- Port mặc định của HTTP là `80`
- HTTP là giao thức `connectionless (kết nối không liên tục)`, client - server chỉ kết nối khi gửi request và reponse, kết nối sẽ được đóng khi gửi xong
- HTTP là `một phương tiện độc lập`, bất kỳ loại dữ liệu nào cũng có thể được gửi bởi HTTP
- HTTP là `stateless`, sau khi kết nối xong client và server sẽ quên đi các địa chỉ
- Các `request methods` của HTPT: `GET` `HEAD` `POST` `PUT` `DELETE` `CONNECT` `OPTIONS` `TRACE`, 2 phương thức được sử dụng nhiều nhất là HTTP Get và Post
- `HTTP Response`, Khi nhận một request, server sẽ response lại `trạng thái`, các `trường header`, `dòng trống` để chỉ định rằng header đã kết thúc, `một phần thân` thông báo 
`
HTTP/1.1 200 OK
Date: Mon, 23 May 2005 22:38:34 GMT
Content-Type: text/html; charset=UTF-8
Content-Encoding: UTF-8
Content-Length: 138
Last-Modified: Wed, 08 Jan 2003 23:11:55 GMT
Server: Apache/1.3.3.7 (Unix) (Red-Hat/Linux)
ETag: "3f80f-1b6-3e1cb03b"
Accept-Ranges: bytes
Connection: close

<html>
<head>
  <title>An Example Page</title>
</head>
<body>
  Hello World, this is a very simple HTML document.
</body>
</html>
`


