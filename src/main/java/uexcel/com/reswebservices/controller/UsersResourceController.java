package uexcel.com.reswebservices.controller;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uexcel.com.reswebservices.user.User;
import uexcel.com.reswebservices.user.UserDtoService;


import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UsersResourceController {
    private final UserDtoService userDtoService;

    public UsersResourceController(UserDtoService userDtoService) {
        this.userDtoService = userDtoService;
    }

    @GetMapping(path = "users")
    public List<User> retrieveAllUsers() {
        return userDtoService.findAllUsers();
    }

    @GetMapping(path = "users/{id}")
    public EntityModel<User> retrieveOneUser(@PathVariable int id) {
       User user = userDtoService.findUserById(id);
       EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).retrieveAllUsers());
       entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @PostMapping(path = "users")
    public ResponseEntity<String> createUser(@Valid @RequestBody User user) {
        user.setId(0);
        User newUser =  userDtoService.saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();
       return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "users/{id}")
    public void DeleteUser(@PathVariable int id) {
        userDtoService.deleteUserById(id);
    }
    @PutMapping(path = "users/{id}")
    public void updateUser(@Valid @RequestBody User user, @PathVariable int id) {
        user.setId(id);
        userDtoService.updateUser(user);
    }
}
