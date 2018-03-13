NETWORKING
===========
Protocol
-------------
- `Protocol` (giao thức) là một tập hợp các quy tắc chuẩn dành cho việc biểu diễn dữ liệu, phát tín hiệu, chứng thực và phát hiện lỗi dữ liệu
- Các máy tính và thiết bị muốn trao đổi thông tin với nhau đều phải có một protocol
- Các protocol thường sẽ được chia thành nhiều bước, bên gửi sẽ thực hiện từ trên xuống để gửi,
còn bên nhận sẽ thực hiện từ dưới lên để nhận
- Một số giao thức thông dụng như: `TCP/IP, HTTP, POP3, FTP, SMTP` (Các chữ "P" trong này là Protocol)
- Giao thức không kết nối: Không giúp kiểm soát đường truyền, bên `gửi` gửi đi dưới dạng gói dữ liệu (`datagrams`) và mặc kệ bên `đích` có nhận được không
- Giao thức kết nối: Kiểm soát đường truyền, dữ liệu gửi đi tuần tự (`sequence datagrams`), bên nhận sẽ gửi file `ACK` khi nhận được
- Giao thức còn chia thành các giao thức định tuyến/không định tuyến để cho phép/không cho phép đi qua các thiết bị mạng như router

IP
--------
- `IP` (Internet Protocol) là một giao thức truyền thông chính trên internet 
- IP là giao thức hướng dữ liệu sử dụng bởi host nguồn và đích để truyền dữ liệu liền mạch
- Các dữ liệu mà IP gửi là các `gói` (`packet/datagram`)
- IP truyền tải dữ liệu `không đảm bảo`, cố gắng truyền đi một cách nhanh nhất
- Hiện nay thế giới thường sử dụng `IPv4` và `IPv6`
- IPv4 sử dụng 32bit thiết lập địa chỉ, còn IPv6 sử dụng 128bit. 
- Giao thức IP có trách nhiệm đặt `địa chỉ` cho các host, `đóng gói dữ liệu` vào các gói tin và `định tuyến` từ host nguồn sang host đích
- Mỗi gói tin sẽ có 2 phần là `header` và `payload`. Header chứ `IP nguồn` và `IP đích` và các `siêu dữ liệu` (`metadata`) để định tuyến

Socket
--------
- `Socket` là một `cổng logic` để một chương trình/máy dùng để kết nối với chương trình/máy khác.
- Mỗi luồng socket bắt buộc phải có một `port` 
- Socket có thể một lúc nhiều luồng nhưng phải khác `số hiệu cổng` (`port`)
- Socket sử dụng 2 `giao thức truyền tải` là `UDP` và `TCP`
- Ngoài 2 giao thức cơ bản còn có `UDT`, giao thức UDP nhưng có handle (sắp xếp, đảm bảo dữ liệu)
- Socket hoạt động theo các mô hình như `Client - Server`, `Peer - to - peer`
- Các chương trình/máy muốn kết nối với nhau bằng socket bắt buộc phải biết port Socket và IP của chương trình/máy còn lại
- Nói ngắn gọn `Socket = IP + Port`

TCP
---------
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

HTTP
------------
- `HTTP (HyperText Transfer Protocol)` là giao thức truyên tải siêu văn bản, là một giao thức ứng dụng trong bộ các giao thức TCP/IP
- HTTP hoạt động dựa trên mô hình `Client - Server`. Lúc này Client sẽ là máy của người dùng sử dụng `browser` để truy cập web
- Port mặc định của HTTP là `80`
- HTTP là giao thức `connectionless (kết nối không liên tục)`, client - server chỉ kết nối khi gửi request và reponse, kết nối sẽ được đóng khi reponse đã trả về cho client
- HTTP là `một phương tiện độc lập`, bất kỳ loại dữ liệu nào cũng có thể được gửi bởi HTTP
- HTTP là `stateless`, sau khi kết nối xong client và server sẽ quên đi các địa chỉ
### HTTP Status Code
- được server phản hồi khi nhận được request có 5 giá trị của ký tự đầu tiên:
	+ 1xx: Thông tin (100 - Continue, 101 - Switching protocol,...)
	+ 2xx: Thành công (200 - Successful, 201- Created,...)
	+ 3xx: Điều hướng lại (301 - Moved permanetly, 302 - Moved temporarily,...)
	+ 4xx: Lỗi từ client (400 - Bad request, 404 - Not found,...)
	+ 5xx: Lỗi từ server (500 - Internal server error, 501 - Not implemented,...)
### HTTP Caching
- Khi server gửi http reponse trên header sẽ có 1 dòng `Cache-control` mà server yêu cầu cho client
	+ `Cache-control: nosotr`or`Cache-control: no-cache, no-store, must-revalidate`, client sẽ không cache lại bất cứ thứ gì
	+ `Cache-control: private/public`, chỉ được một user hiện hành sử dụng bộ cache này hay toàn bộ
	+ `Cache-control: max-age=31536000`, Cache lại theo một khoảng thời gian, với 31536000 là được tính bằng giây, sau khoảng thời gian đó bộ cache sẽ tự động xoá
	+ `Cache-control: must-revalidate`, client sẽ xác nhận lại các cache cũ đã hết hạn hoặc còn được sử dụng không 
### HTTP Cookies 
- HTTP Cookie là 1 phần dữ liệu mà server gửi về cho client, thường được sử dụng để lưu lại những tác vụ như đăng nhập, giỏ hàng, điểm game, hay là những dữ liệu cần lưu trong một khoảng thời gian. Trên header từ server gửi về sẽ có dòng `Set-Cookie: <cookiename>=<cookievalue>`
- Mỗi cookie sẽ có một khoảng thời gian được trả về nhất định (ngắn) `Set-Cookie: id=a3fWa; Expires=Wed, 21 Oct 2015 07:28:00 GMT;`
- Cookies được gửi từ server nên có thể dùng cookie để tiếp tục session tại một máy khác
### HTTP Request Methods
- HTTP định nghĩa một số phương thức để cho biết `hành động` dành cho các resource nhất định
- Các `request methods` của HTTP: `GET` `HEAD` `POST` `PUT` `PATCH` `DELETE` `CONNECT` `OPTIONS` `TRACE` , 2 phương thức được sử dụng nhiều nhất là HTTP Get và Post
- `POST` là một method có thể làm hầu hết mọi thứ trên HTTP
- giữa `PUT` và `PATCH` cần phải phân biệt được là: `PUT` thao tác trên toàn bộ entity, còn `PATCH` dùng cho một chỉ định cụ thể như field
- Sử dụng `HEAD` khi muốn check hoặc tracking, vì `HEAD` không trả về body nên dữ liệu rất nhẹ so với phương thức khác
### HTTP Header
- `HTTP Response`, Khi nhận một request, server sẽ response lại `trạng thái`, các `trường header`, `dòng trống` để chỉ định rằng header đã kết thúc và `một phần thân` thông báo 
### Cross-Origin Resource Sharing (CORS)
- `CORS` là cơ chế sử dụng HTTP header để gán cho người dùng quyền truy cập vào tài nguyên của server bởi một domain khác
- Các request dùng đến CORS là XMLHttpRequest, Fetch API, CSS, drawImage, Scripts 
### HTTP Content-Security-Policy
- HTTP CSP giúp cho server có thể quản lý và kiểm soát tài nguyên tốt hơn
- Syntax: `Content-Security-Policy:<policy-directive>;<policy-directive>`
- Có 4 loại `Directives` chính : `Fetch Directives`, `Document directives`, `Navigation directives`, `Reporting directives` ngoài ra còn một số các directives khác
### HTTPS và HTTP
- Trong HTTP thì "S" trong này có nghĩa là Secure, là HTTP có sử dụng thêm các chứng chỉ SSL hay TLS giúp mã hóa dữ liệu truyên tải, nói cách khác HTTPS là phiên bản an toàn hơn của HTTP, HTTPS sử dụng `port 443`, tất nhiên với bước mã hóa của HTTPS thì tốc độ truy cập sẽ không nhanh bằng HTTP
### Về HTTP/2
- HTTP/2 là bản nâng cấp mới nhất của giao thức HTTP. Ở HTTP/2 dữ liệu được truyền tải nhị phân thay vì dạng text như HTTP/1, giúp tác vụ được thực hiện hiệu quả và ít tốn thời gian hơn. Các header cũng sẽ được nén trước khi gửi đi kèm với những truy vấn mô tả, nguồn gốc, kiểu, độ dài,.. của dữ liệu
- HTTP/2 giải quyết sự bất đồng bộ nên các truy vấn nhỏ hơn hoặc nhanh hơn có thể được xử lý sớm hơn (HTTP/1 theo 1 trật tự)
- HTTP/2 cho phép xử lý nhiều truy vấn giữa server và client
- Với ngày xưa HTTP/1 xử dụng giao thức truyền tải `half-duplex` thì một thiết bị/host chỉ có thể đảm nhiệm 1 vai trò là `thu` hoặc `phát` tín hiệu trong cùng `1 thời điểm` thì giờ HTTP/2 đã sử dụng `full-duplex` , với giao thức truyền tải này một thiết bị/host có thể vừa thu vừa phát cùng lúc giúp cho việc giao tiếp tốt hơn và nhanh hơn. ngoài ra còn có `simple mode`, tức là một thiết bị chỉ đóng vai trò thu hoặc phát cố định
