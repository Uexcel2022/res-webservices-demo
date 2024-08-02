package uexcel.com.reswebservices.hello;

import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import java.util.Locale;


@RestController
public class HelloController {

    private final MessageSource messageSource;

    public HelloController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping(path="hello-world")
    public String sayHello() {
        return "Hello World";
    }

//    Example: en - English (Good Morning)
//    Example: nl - Dutch (Goedemorgen)
//    Example: fr - French (Bonjour)
//    Example: de - Deutsch (Guten Morgen)

//    then in the header
//    Example: in the header add Accept-language, value = nl;

    @GetMapping(path="hello-world-internationalization")
    public String sayHelloInternalization() {
        Locale Locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Messages", Locale);
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
