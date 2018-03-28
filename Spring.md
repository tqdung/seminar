SPRING
===========

Spring Container
---------------
- `Spring Container` hay `IoC Container trong Spring` là core của Spring Framework. 
- `IoC Container` sẽ config các object và quản lý chúng từ lúc được tạo ra đến khi bị hủy. IoC sử dụng `DI(Dependency Injection)` để quản lý các module. Các object này sẽ được xem là `Spring Bean`
- Để tạo đối tượng, cấu hình, lắp rắp chúng, Spring Container sẽ đọc thông tin từ các file xml và thực thi

* HelloWorld.java
```
public class HelloWorld {
  private String message;
  public void setMessage(String message) {
    this.message = message;
  }
  public void getMessage() {
    System.out.println("Print : " + message);
  }
}
```
* beans.xml
```
<?xml version = "1.0" encoding = "UTF-8"?>
<beans xmlns = "http://www.springframework.org/schema/beans"
   xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance"
   xsi:schemaLocation = "http://www.springframework.org/schema/beans
   http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">
   <bean id = "helloWorld" class = "stackjava.com.springioc.beanfactory.HelloWorld" >
      <property name = "message" value = "Hello World!"/>
   </bean>
</beans>
```
- các `field` cần chú ý là `bean(id, class)` `property(name, value)`
- Sau khi build, sử dụng `BeanFactory (DefaultListableBeanFactory)` để đọc thông tin / Spring cung cấp 2 container là `BeanFactory` và `ApplicationContext`
```
// create factory 
DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
// reading xml beans
XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
reader.loadBeanDefinitions(new ClassPathResource("beans.xml"));
//create new hello world object
HelloWorld obj = (HelloWorld) factory.getBean("helloWorld");
obj.getMessage();
```
- Output `Print: Hello World`

Spring Bean
-------------
- `Spring Bean` là các object được tạo ra thông qua `Spring Container`
### Spring Bean Scope
- `singleton` : chỉ một instance của bean được tạo trong mỗi container
- `prototype` : một instance mới sẽ được tạo ra mỗi lần bean được request
- `session` : bean mới sẽ được tạo ra cho mỗi HTTP session
- `request` : một instance mới sẽ được tạo ra cho mỗi HTTP request
- `global-session`: chưa hiểu xD

```
<bean id = "..." class = "..." scope = "singleton/prototype/session/request/global-session">
</bean>
```

### Spring Bean Configuration
- 

Spring Annotation
---------------

### @Autowired
- `Auto-wiring` là Spring Container sẽ tự động tìm bean thích hợp để inject nó vào
- `@Autowired` annotation giúp tự động liên kết các Bean lại với nhau
```
public class FooService {
 
    private FooFormatter fooFormatter;
 
    @Autowired
    public FooService(FooFormatter fooFormatter) {
        this.fooFormatter = fooFormatter;
    }
}
```
### @Configuration
- `@Configuration` là annotation giúp khởi tạo Spring Bean trong code Java (trước đó là xml)

```
public class HelloWorld {
    
    public void print()  {
        System.out.print("Hello World!");
    }
}
```

```
@Configuration
public class ApplicationConfiguration {
 
    @Bean
    public HelloWorld helloWorld() {
        return new HelloWorld();
    }
}
```

```
public class Application {
 
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
 
        HelloWorld helloWorld = (HelloWorld) context.getBean("helloWorld");
        helloWorld.print();
    }
}
```
- Ouput `Hello World!`
- `id` của bean lúc này là `tên phương thức`

### Component
- `Auto component` là chương trình tạo ra Bean một cách tự động

* context:component-scan
```
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
  http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context
  http://www.springframework.org/schema/context/spring-context.xsd">
 
    <context:component-scan base-package="com.duong.springcomponentscan" />
</beans>
```
- Có các annotation để thêm vào nếu muốn đối tượng tự tạo bean:
    + @Component: dùng cho những đối tượng không liên quan đến database, business logic hay presentation layer.

    + @Repository: dùng cho những đối tượng liên quan đến database layer.

    + @Service: dùng cho những đối tượng liên quan đến business logic layer.

    + @Controller: dùng cho những đối tượng liên quan đến presentation layer.

```
import org.springframework.stereotype.Component;
 
@Component
public class HelloWorld {
    
    public void print()  {
        System.out.print("Hello World!");
    }
}
```

```
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
 
public class Application {
 
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
 
        HelloWorld helloWorld = (HelloWorld) context.getBean("helloWorld");
        helloWorld.print();
    }
}
```
- Output `Hello World!`

### @Controller, @RestController
- `@Controller` annotation để khai báo một class là một controller trong spring MVC. Nhiệm vụ của class đó là nhận request từ người dùng > xử lý > reponse
- `@RestController` khác với `@Controller` là `@ResController` trả về một data dạng chuỗi như json, xml chứ không phải view
```
@Controller
public class DefaultController {

    @GetMapping("/")
    public String home1() {
        return "/home";
    }

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }

    @GetMapping("/user")
    public String user() {
        return "/user";
    }

    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

}
```
### @RequestMapping
- `@RequestMapping` là annotation để map request với method/class xử lý request đó
```
@Controller
public class HomeController {
  @RequestMapping(value = { "/", "/home" })
  public String home() {
    return "home";
  }
  @RequestMapping(value = "/test", method = RequestMethod.GET)
  public String doGet() {
    return "test1";
  }
  @RequestMapping(value = "/test", method = RequestMethod.POST)
  public String doPost() {
    return "test2";
  }
  @RequestMapping(value = "/method0", headers = "name=kai")
  public String method0() {
    return "page0";
  }
  @RequestMapping(value = "/method1", headers = { "name=kai", "id=1" })
  public String method1() {
    return "page1";
  }
  @RequestMapping(value = "/method2", produces = { "application/json"}, consumes = "text/html")
  public String method2() {
    return "page2";
  }
}
```

JPA (Hibernate) with JDBC(MySQL)
------------------
- `Hibernate` là một framework cung cấp API  giúp cho dữ liệu có thể thao tác với database