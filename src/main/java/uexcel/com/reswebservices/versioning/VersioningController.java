package uexcel.com.reswebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {

// URI VERSIONING -- Twitter_______________

    @GetMapping(path = "/v1/person")
    public PersonV1 getPersonV1UriVersion() {
        return new PersonV1("Udoka Excellence");
    }

    @GetMapping(path = "/v2/person")
    public PersonV2 getPersonV2UriVersion() {
        return new PersonV2( new Person("Udoka", "Excellence"));
    }

//    REQUEST PARAMETER VERSIONING ---AMAZON

    @GetMapping(path = "/person",params = "version=1")
    public PersonV1 getVersionOneOfPersonParams() {
        return new PersonV1("Udoka Excellence");
    }

    @GetMapping(path = "/person",params = "version=2")
    public PersonV2 getVersionTwoOfPersonParams() {
        return new PersonV2( new Person("Udoka", "Excellence"));
    }

//    REQUEST HEADER VERSIONING ---MICROSOFT

    @GetMapping(path = "/person/header",headers = "X-API-VERSION=1")
    public PersonV1 getPersonV1RequestHeaderParams() {
        return new PersonV1("Udoka Excellence");
    }

    @GetMapping(path = "/person/header",headers = "X-API-VERSION=2")
    public PersonV2 getPersonV2RequestHeaderParams() {
        return new PersonV2( new Person("Udoka", "Excellence"));
    }

//    Media type versioning (a.k.a “content negotiation” or “accept header”) - GitHub

    @GetMapping(path = "/person/accept",produces = "application/vnd.company.app-v1+json")
    public PersonV1 getPersonV1ProducesAcceptHeader() {
        return new PersonV1("Udoka Excellence");
    }

    @GetMapping(path = "/person/accept",produces = "application/vnd.company.app-v2+json")
    public PersonV2 getPersonV2ProducesAcceptHeader() {
        return new PersonV2( new Person("Udoka", "Excellence"));
    }



}

