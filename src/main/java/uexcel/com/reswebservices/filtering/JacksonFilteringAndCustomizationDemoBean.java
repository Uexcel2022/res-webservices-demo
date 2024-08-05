package uexcel.com.reswebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;

//@JsonIgnoreProperties(value = {"password"})  //filtering at class level
@JsonFilter("JacksonFilteringAndCustomizationDemoBeanFilter")  //Dynamic filter annotation and name
public class JacksonFilteringAndCustomizationDemoBean {

    private final String username;

//    @JsonProperty("birth_day")        //static property name customization
    private final String birthDate;

//    @JsonIgnore                       // static filtering password
    private String phone;

    public JacksonFilteringAndCustomizationDemoBean(String username, String birthDate, String phone) {
        this.username = username;
        this.birthDate = birthDate;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getPhone() {
        return phone;
    }
}
