package uexcel.com.reswebservices.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(path="hello-world")
    public String sayHello() {
        return "Hello World";
    }

    @GetMapping(path="hello-world-bean")
    public HelloWorldBean sayHelloBean() {
        return new HelloWorldBean("Hello world");
    }

    @GetMapping(path="hello-world/path-variable/{name}")
    public HelloWorldBean sayHellPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello world %s ",name));
    }
}
