package uexcel.com.reswebservices.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uexcel.com.reswebservices.filtering.JacksonFilteringAndCustomizationDemoBean;

import java.util.Arrays;
import java.util.List;

@RestController
public class JsonOperationController {
    @GetMapping("filter")
    public MappingJacksonValue getProperties(){

//        Dynamic Filtering process
//        Mapping Jackson value
//        Define filter
//        add @JacksonFilter and pass in the filter name eg: JsonFilteringAndCustomizationDemoBeanFilter

        JacksonFilteringAndCustomizationDemoBean demoBean  =
                new JacksonFilteringAndCustomizationDemoBean("uexcel","1982-06-20", "82jsjss");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(demoBean);
        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("username","birthDate");
        FilterProvider filters = new SimpleFilterProvider().addFilter("JacksonFilteringAndCustomizationDemoBeanFilter", simpleBeanPropertyFilter);
        mappingJacksonValue.setFilters(filters);

       return mappingJacksonValue;
    }



    @GetMapping("filter-list")
    public MappingJacksonValue getListOfProperties(){
        List<JacksonFilteringAndCustomizationDemoBean> demoBean =
                Arrays.asList(new JacksonFilteringAndCustomizationDemoBean("uexcel","wwe4e", "82jsjss"),
                new JacksonFilteringAndCustomizationDemoBean("jeuezi","IOOOOs", "82jsjss"));

        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("username","phone");
        FilterProvider filters = new SimpleFilterProvider().addFilter("JacksonFilteringAndCustomizationDemoBeanFilter",simpleBeanPropertyFilter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(demoBean);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }


}
