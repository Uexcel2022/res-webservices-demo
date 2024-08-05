package uexcel.com.reswebservices.controller;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uexcel.com.reswebservices.user.User;
import uexcel.com.reswebservices.user.UserDtoJpaService;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UsersJpaResourceController {
    private final UserDtoJpaService userDtoJpaService;

    public UsersJpaResourceController(UserDtoJpaService userDtoService) {
        this.userDtoJpaService = userDtoService;
    }

    @GetMapping(path = "jpa-users")
    public List<User> retrieveAllUsers() {
        return userDtoJpaService.findAllUsers();
    }

    @GetMapping(path = "jpa-users/{id}")
    public EntityModel<User> retrieveOneUser(@PathVariable int id) {
       User user = userDtoJpaService.findUserById(id);
       EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).retrieveAllUsers());
       entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @PostMapping(path = "jpa-users")
    public ResponseEntity<String> createUser(@Valid @RequestBody User user) {
        User newUser =  userDtoJpaService.saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();
       return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "jpa-users/{id}")
    public void DeleteUser(@PathVariable int id) {
        userDtoJpaService.deleteUserById(id);
    }

    @PutMapping(path = "jpa-users/{id}")
    public void updateUser(@Valid @RequestBody User user, @PathVariable int id) {
        user.setId(id);
        userDtoJpaService.updateUser(user);
    }
}
