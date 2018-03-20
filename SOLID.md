SOLID
===========

"S"ingle reponsiblity principle 
-----------------------

### Vấn đề
- Một class chỉ nên giữ `một trách nhiệm/chức năng duy nhất`
- Khi class có quá nhiều chức năng sẽ khiến nó trở nên cồng kềnh, mỗi khi mình cần edit một vài chức năng nào đó trong class có thể sẽ khiến cho các chức năng khác bị thay đổi/ảnh hưởng theo
- Việc khó nhất trong nguyên tắc SRP là `gom nhóm, xác định chức năng`
- Ví dụ
```
public class Company{
    public String _name;
    
    public String getNameCompany(){
        return _name;
    }
    
    public void setNameCompany(String value){
        _name = value;
    }

    public void saveToDB(Company company){
        contextDB.save(company);
    }

    public void loadCompany(){
        contextDB.load();
    }
}
```
- class Company phía trên có quá nhiều chức năng, khi lập trình viên muốn nâng cấp hay maintaince class này sẽ thấy rối và khó khăn. Để đúng SRP cần tách ra 

### Giải quyết
```
public class Company{
    public String _name;
    
    public String getNameCompany(){
        return _name;
    }
    
    public void setNameCompany(String value){
        _name = value;
    }
}

public class Store{
    public void saveToDB(Company company){
        contextDB.save(company);
    }

    public void loadCompany(){
        contextDB.load();
    }
}
```
- Trường hợp đây là một class nhỏ thì các class nên tách ra thêm
```
public class Company{
    public String _name;
}

public class CompanyController{
    public String getNameCompany(){
        return _name;
    }
    
    public void setNameCompany(String value){
        _name = value;
    }
}

public class Store{
    public void saveToDB(Company company){
        contextDB.save(company);
    }

    public void loadCompany(){
        contextDB.load();
    }
}
```

"O"pen/Closed Principle
---------------

### Vấn đề
- Khi một class được sử dụng, nên `hạn chế việc chỉnh sửa code bên trong` class đó. Thay vào đó, nên mở rộng bằng cách `kế thừa` bằng một class con
Ví dụ khi cần thêm thuộc tính và phương thức cho class Company
```
class Company{
    String _name;
    boolean _isVip;
    public boolean vipCompany(){
        return _isVip;
    }
}
```
- Việc sửa thế này hoàn toàn đúng, nhưng các phương thức khác có thể bị ảnh hưởng theo, chẳng hạn như `saveToDB(Company company)` thì lúc này company không chỉ đơn thuần chỉ là _name nữa mà có thêm _isVip
- Vậy nên ta cần sử dụng cách mở rộng bằng cách kế thừa

### Giải quyết
```
class VipCompany extends Company{
    boolean _isVip;
    public boolean vipCompany(){
        return _isVip;
    }
}
```
- Cách làm này thì nếu muốn mở rộng thêm các chức năng khác thì vẫn chỉ cần kế thừa từ clas Company, class AdvancedCompany sẽ không bị ảnh hưởng theo
```
class ImportanCompany extends Company{
    boolean _isImportant;
    public boolean isImportant(){
        return _isImportant;
    }
}
```
- ImportantCompany Không hề ảnh hưởng tới VipCompany


"L"iskov Substitution Principle
---------------

### Vấn đề
- Functions that use pointers or references to base classes must be able to use objects of derived classes without knowing it
- Nguyên tắc LSP đảm bảo các `instance của lớp con có thể thay thế instance của lớp cha` mà chương trình vẫn có thể `chạy ổn định`
- Khi mở rộng phần mềm bằng các lớp con kế thừa, cần đảm bảo các lớp con này có thẻ `chạy được và đúng` những function mà lớp cha đã sử dụng trước đó
- Ví dụ vi phạm LSP
```
class Company(){
    void aboutMe(){
        System.out.println("A company");
    }
}

class VipCompany extends Company{
    void aboutMe(){
        System.out.println("A Vip company");
    }
}

class ImportantCompany extends Company{
    void aboutMe(){
        System.out.println("A Important company");
    }
}

class ForeignCompany extends Company{
    void aboutme(){
        throw Exception("No, i'm not");
    }
}
```
- Giả sử ta cần tất cả Company cho biết thông tin
```
List<Company> allCompany =  new ArrayList<Company>();
allCompany.add(new VipCompany);
allCompany.add(new ImportantCompany);
allCompany.add(new ForeignCompany);
for(int i=0;i<allCompany.size();i++){
    allCompany.get(i).aboutMe();
}
```
- Khi như vậy thì đến ForeignCompany sẽ quăng ra một Exception, khiến chương trình bị crash, ForeignCompany không thể đáp ứng được các hành động mà lớp Company có

### Giải quyết
- Updating

"I"nterface Segregation Principle
----------------

### Vấn đề
- ISP là nguyên lý áp dụng trên interface
- Theo đó thì những class nào implements interface thì phải override `tất cả các method` của interface đó đã khai báo
- Các class thường implement interface để `cá nhân hóa` các cài đặt của riêng nó
- Các  module của một hệ thống thường sẽ giao tiếp với nhau `qua interface`
- ISP muốn chỉ rằng nếu một interface quá lón thì nên tách  thành các interface nhỏ hơn. Nguyên nhân bởi vì nếu một interface quá lớn (100,200 method) thì một class chỉ cần  sử dụng 3 method của interface đó cũng cần phải override tất cả các method của interface đó
- Ví dụ 
```
public interface CompanyTrade{
    void buyLikeRich();
    void buyLikePoor();
    void buyLikeNormal();
}
```
- Vậy một class nào đó implements CompanyTrade cần phải override cả 3 phương thức trên
```
public class PoorCompany{
    public override buyLikeRich(){

    }
    
    public override buyLikeNormal(){

    }
    public override buyLikePoor(){
        System.out.println("Buy like poor");
    }
}
```

### Giải quyết
- Chúng ta sẽ tách interface ra một cách phân biệt để các company khác nhau implements interface cần thiết
```
public interface PoorCompanyTrade{
    void buyLikePoor();
}

public interface NormalCompanyTrade{
    void buyLikeNormal();
}

public interface RichCompanyTrade{
    void buyLikeRich();
}
```
- Như vậy các class Company khác nhau chỉ cần implement interface phù hợp
```
public class PoorCompany implements CPoorCompanyTrade{
    public void override buyLikePoor(){
        System.out.println("Buy like poor");
    }
}

public class NormalCompany implements NormalCompanyTrade{
    public void override buyLikeNormal(){
        System.out.println("Buy like normal");
    }
}

public class RichCompany implements RichCompanyTrade{
    public void override buyLikeRich(){
        System.out.println("Buy like rich");
    }
}
```

"D"ependency Inversion Principle
----------------------

### Vấn đề
- Các module cấp cao không nên phụ thuộc vào module cấp thấp mà cả 2 nên phụ thuộc vào abstraction/interface
- Các class giao tiếp với nhau thông qua interface, không phải thông qua implementation
- Ví dụ
```
class Company{
    private PoorCompany _poor;

    public String getCompany(){
        return _poor._name;
    }

    public float calculated(){
        return _poor._money*5;
    }
}

class PoorCompany{
    float _money;
    String _name;

}
```
- Lúc này Company sẽ phụ thuộc hoàn toàn vào PoorCompany, nếu như cần xử lý thêm RichCompany hay NormalCompany thì class Company sẽ ngày càng phình to ra


### Giải quyết
- Tạo một interface Company, và class CompanyManager để có thể quản lý các Company mà không cần biết các Company khác đến từ đâu
```
public interface Company{
    float calculated();
}

public class PoorCompany implements Company{
    float _poorMoney;

    public float override calculated(){
        return this._poorMoney*5;
    }
}

public class RichCompany implements Company{
    float _richMoney;
    public float override calculated(){
        return this._richMoney*3;
    }
}

public class CompanyManager{
    private Company company;
    public float calculatedCompany(Company company){
        return company.calculated();
    }
}
```
- Trường hợp trên ta muốn mở rộng thêm một loại Company nào đos thì chỉ cần tạo ra 1 class mới và implements Company, không ảnh hưởng tới CompanyManger