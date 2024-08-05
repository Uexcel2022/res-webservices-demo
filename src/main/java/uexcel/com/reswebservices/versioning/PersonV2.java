package uexcel.com.reswebservices.versioning;

public class PersonV2 {
    private Person name;

    public PersonV2(Person name) {
        this.name = name;
    }

    public Person getName() {
        return name;
    }

    public void setName(Person name) {
        this.name = name;
    }
}
