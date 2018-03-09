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
### TCP
- `TCP (Tranmission Control Protocol)` là giao th giúp các ứng dụng trên các host có thể tạo kết nối với nhau để trao đổi dữ liệu hoặc gói tin
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
