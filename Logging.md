LOGGING JAVA
===============
### Intro
- Logging là process ghi ra những `log (nhật ký)` các message khi chương trình đang được thực thi ra một file hoặc một vị trí nào đó
- Logging có thể cho phép người sử dụng biết được các cảnh báo, lỗi hay báo cáo của chương trình

### Log level
- Trong Java Logging có 7 Level Log `SERVERE` `WARNING` `INFO` `CONFIG` `FINE` `FINER` `FINEST`
- Có thể set level cho Logging bằng `logVariable.setLevel(Level.WARNING)` hoặc dùng `OFF` để tắt log, `ALL` để ghi ra mọi thứ

### Handler
- Các logger có thể sử dụng một số handler
- Handler sẽ nhận các `log message` từ logger và đưa ra cho người dùng thấy
- Java có 2 handler logger thường sử dụng là `ConsoleHandler` để ghi log ra console và `FileHandler` để ghi ra file

### Formatter
- Người dùng log có thể `configure` log với `format` riêng 
- 2 `Formatter` có sẵn là `SimpleFormatter` để generate message ra text và `XMLFormatter` generate message ra XML
- Formatter có thể được build riêng tùy theo người dùng, mục đích sử dụng

### Ghi log
- Ví dụ 
```
static public void setup() throws IOException{
        //get the global loggẻ
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        //set level logger
        logger.setLevel(Level.ALL);

        //using FileHandler to write to file txt
        FileHandler fileTxt = new FileHandler("Log.txt");

        //using simepleformatter
        SimpleFormatter formatTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatTxt);

        logger.addHandler(fileTxt);

        //message to write log
        logger.severe("important");
        logger.warning("info log");
        logger.info(("info log"));
        logger.finest("something not important");
        
    }
```

```
public static void main(String[] args) throws IOException {
        // TODO code application logic here
        setup(); 
    }
```
- Ở hàm main gọi function setup() thì lúc này trong project sẽ xuất hiện file `Log.txt` với đoạn text đã configure 
```
Mar 26, 2018 9:56:08 AM test.Test setup
SEVERE: important
Mar 26, 2018 9:56:08 AM test.Test setup
WARNING: info log
Mar 26, 2018 9:56:08 AM test.Test setup
INFO: info log
Mar 26, 2018 9:56:08 AM test.Test setup
FINEST: something not important
```
- Các thông tin trên log có cấu trúc gồm <time> <project>.<class> <function> | <level log> <log message>