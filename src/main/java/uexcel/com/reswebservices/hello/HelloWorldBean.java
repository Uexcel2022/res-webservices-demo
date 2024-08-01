package uexcel.com.reswebservices.hello;

public class HelloWorldBean {

    private String message;

    public HelloWorldBean(String message) {
        this.message=message;
    }

    public HelloWorldBean(){}

    public String getMessage() {
        return message;
    }

    public void getMessage(String message) {
        this. message= message;
    }

    @Override
    public String toString() {
        return "HelloWorldBean{" +
                "message='" + message + '\'' +
                '}';
    }
}
