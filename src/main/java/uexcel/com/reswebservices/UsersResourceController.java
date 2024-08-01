package uexcel.com.reswebservices;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uexcel.com.reswebservices.user.User;
import uexcel.com.reswebservices.user.UserDtoService;


import java.net.URI;
import java.util.List;

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
    public User retrieveOneUser(@PathVariable int id) {
        return userDtoService.findUserById(id);
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
}
