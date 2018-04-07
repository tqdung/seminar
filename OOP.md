OOP JAVA
===========

Tính trừu tượng (Abstraction)
----------------
- `Tính trừu tượng` trong `hướng đối tượng` là để chỉ việc người sử dụng một class trừu tượng chỉ cần biết và gọi được các phương thức/thuộc tính của class đó. Có nghĩa là người sử dụng sẽ không cần quan tâm phương thức đó flow như thế nào, mà cần biết được `cách sử dụng` và `kết quả trả ra` của phương thức
- Có abstract class Animal với hành động hienThiTiengKeu
```
public abstract class Animal {
    private String tiengKeu;
     
    public abstract void hienThiTiengKeu();
}

public class Cat extends Animal {
 
    @Override
    public void hienThiTiengKeu() {
        //do something
        //flow
        System.out.println("Meo");
    }
     
}

public class Dog extends Animal{

    @Override public void hienThiTiengKeu(){
        //do something
        //flow
        System.out.println("Cho");
    }
}
```
```
public class Main {
 
    public static void main(String[] args) {
        Animal dog = new Dog();
        //Người dùng biết method bên dưới là hiển thị tiếng kêu, kiểu trả về là void
        dog.hienThiTiengKeu();
         
        Animal cat = new Cat();
        cat.hienThiTiengKeu();
    }
 
}
```
- Output : `Cho Meo`
- Một lớp trừu tượng `không thể khởi tạo`. Nếu ta khai báo `Animal a = new Animal()` compiler sẽ tự động báo lỗi


Tính kế thừa (Inheritance)
--------------------
- `Tính kế thừa` trong `hướng đối tượng` là kỹ thuật mà lớp con thừa kế lại các `phương thức` và `thuộc tính` của lớp cha, trừ các phương thức, thuộc tính `private`
- Sử dụng kế thừa thông qua từ khóa `extends` và `implements`
- Một class có thể `extends` một class (chỉ một), và `implements` nhiều interface. Một class có thể vừa `extends` vừa `implements` cùng lúc, các `interface` có thể `extends` `interface` khác nhưng không thể `implements`(đối với JDK7, hiện nay JDK8 đã hỗ trợ)
### Đơn kế thừa 
```
class Animal {
    void eat() {
        System.out.println("eating...");
    }
}
 
class Dog extends Animal {
    void bark() {
        System.out.println("barking...");
    }
}
 
public class TestInheritance1 {
    public static void main(String args[]) {
        Dog d = new Dog();
        d.bark();
        d.eat();
    }
}
```
Output
```
barking...
eating...
```

### Kế thừa nhiều cấp
```
class Animal {
    void eat() {
        System.out.println("eating...");
    }
}
 
class Dog extends Animal {
    void bark() {
        System.out.println("barking...");
    }
}
 
class BabyDog extends Dog {
    void weep() {
        System.out.println("weeping...");
    }
}
 
public class TestInheritance2 {
    public static void main(String args[]) {
        BabyDog d = new BabyDog();
        d.weep();
        d.bark();
        d.eat();
    }
}
```
Output
```
weeping...
barking...
eating...
```

Tính đa hình (Polymorphism)
------------------------
- `Tính đa hình` trong `hướng đối tượng` là để chỉ cùng một phương thức nhưng lại có các luồng, các xử lý khác nhau khi được gọi ra. Nghĩa là các class con sẽ `ghi đè (override)` phương thức của lớp cha

Ví dụ
```
class Shape {
    void draw() {
        System.out.println("drawing...");
    }
}
 
class Rectangle extends Shape {
    void draw() {
        System.out.println("drawing rectangle...");
    }
}
 
class Circle extends Shape {
    void draw() {
        System.out.println("drawing circle...");
    }
}

class TestPolymorphism2 {
    public static void main(String args[]) {
        Shape s1 = new Shape();
        Shape s2 = new Rectangle();
        Shape s3 = new Circle();
        s1.draw();
        s2.draw();
        s3.draw();
    }
}
```
Output
```
drawing....
drawing rectangle...
drawing circle...
```
```
class Shape {
    void draw() {
        System.out.println("drawing...");
    }
}
 
class Rectangle extends Shape {
    void draw() {
        System.out.println("drawing rectangle...");
    }
}
 
class Circle extends Shape {
    public static void main(String args[]) {
        Shape s1 = new Shape();
        Shape s2 = new Rectangle();
        Shape s3 = new Circle();
        s1.draw();
        s2.draw();
        s3.draw();
    }
}
```
Output
```
drawing....
drawing rectangle...
drawing...
```
- Vì Circle chưa override phương thức draw() nên sẽ lấy draw của lớp Shape
- Chúng ta truy cập thành viên dữ liệu bởi biến tham chiếu của lớp cha mà tham chiếu tới đối tượng lớp con. Khi chúng ta truy cập thành viên dữ liệu mà không bị ghi đè, thì nó sẽ luôn luôn truy cập thành viên dữ liệu của lớp cha
```
class Bike{  
 int speedlimit=90;  
}  
class Honda3 extends Bike{  
 int speedlimit=150;  
   
 public static void main(String args[]){  
  Bike obj=new Honda3();  
  System.out.println(obj.speedlimit);//90  
}  
```
Output: `90`

Đóng gói (Encapsulation)
------------------
`Tính đóng gói` trong `hướng đối tượng` là kỹ thuật giấu đi thông tin hoặc hiện ra các thông tin. Mục đích của việc đóng gói là giảm thiểu sự phức tạp phát triển phần mềm
- Các phương thức, dữ liệu `private` sẽ không truy xuất được từ bên ngoài. Muốn truy xuất các dữ liệu `private` lập trình viên cần tạo ra các phương thức `getter/setter`
Ví dụ
```
public class Person {
    // khai báo các thuộc tính của đối tượng là private
    private String sdt;
    private String hoTen;

    public void setSDT(String sdt) {
        this.sdt = sdt.substring(1);
    }

    public String getSDTVN(){
        return "+84 "+sdt;
    }

    public String getSDTUS(){
        return "0"+sdt;
    }

    public String getHoTen() {
        return hoTen;
    }
    
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
     
}
```

```
 
public class TestPerson {
 
    public static void main(String[] args) {
        Person person = new Person();
         
        person.setHoTen("Trần Văn Bình");   
        person.setSDT("0919000999");
        System.out.println("Tên: " + person.getHoTen() + ", số dt VN: " + person.getSDTVN() + ", số đt US: " + person.getSDTUS());
    }
 
}
```
Output : `Tên: Trần Văn Bình, số dtVN: 0919000999, số dt US: +84 918000999`

super và final trong Java
---------
- Trong Java hỗ trợ 2 từ khóa `super` và `final` để thực hiện các thao tác với class
### super
- Từ khóa `super` được sử dụng để tham chiếu trực tiếp đến biến instance của lớp cha.
Ví dụ
```
class Vehicle {
    int speed = 50;
}
 
public class Bike2 extends Vehicle {
    int speed = 100;
 
    void display() {
        System.out.println(super.speed);
    }
 
    public static void main(String args[]) {
        Bike2 b = new Bike2();
        b.display();
 
    }
}
```
Output `50`
- `super()` được sử dụng để gọi trực tiếp Constructor của lớp cha.
Ví dụ
```
class Vehicle {
    Vehicle() {
        System.out.println("Vehicle is created");
    }
}
 
class Bike2 extends Vehicle {
    Bike2() {
        super();//gọi Constructor của lớp cha  
        System.out.println("Bike is created");
    }
 
    public static void main(String args[]) {
        Bike2 b = new Bike2();
    }
}
```
Output
```
Vehicle is created
Bike is created
```
- `super.method()` cũng có thể được sử dụng để gọi phương thức của lớp cha
Ví dụ
```
class Person {
    void message() {
        System.out.println("welcome");
    }
}
 
public class Student16 extends Person {
    void message() {
        System.out.println("welcome to java");
    }
 
    void display() {
        message();
        super.message();
    }
 
    public static void main(String args[]) {
        Student16 s = new Student16();
        s.display();
    }
}
```
Output
```
welcome to java
welcome
```
### final
- Một phương thức là `final` thì không ghi đè được `final void run(){System.out.println("running");}  `
- Không thể kế thừa một class final `final class Bike{}`
- Một biến final không thể thay đổi `final CMND 14124124`
