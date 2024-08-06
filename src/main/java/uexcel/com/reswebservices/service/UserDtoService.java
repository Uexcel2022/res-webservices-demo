package uexcel.com.reswebservices.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import uexcel.com.reswebservices.exception.UserNotFoundException;
import uexcel.com.reswebservices.user.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class UserDtoService {

    List<User> users = new ArrayList<>();

    @PostConstruct
    public void initialize(){
        users.add(new User(1, "uexcel", LocalDate.now().minusYears(30)));
        users.add(new User(2, "victor", LocalDate.now().minusYears(10)));
        users.add(new User(3, "excellence", LocalDate.now().minusYears(20)));
        users.add(new User(4, "francis", LocalDate.now().minusYears(15)));
    }

    public List<User> findAllUsers() {
        return users;
    }

    public User findUserById(int id) {
       return users.stream()
               .filter(userId -> userId.getId()==id)
               .findFirst()
               .orElseThrow( () -> new UserNotFoundException("id:"+id));
    }

    public User saveUser(User user) {
        int maxId = users.stream().mapToInt(User::getId).max().orElse(0);
        user.setId(maxId+1);
        user.setName(nameToLowerCase.apply(user.getName()));
        users.add(user);
        return user;
    }

    public void deleteUserById(int id) {
        User user = findUserById(id);
        users.remove(user);
    }

    public void updateUser(User user) {

        User userToUpdate = findUserById(user.getId());

        if(user.getName()!=null) {
            userToUpdate.setName(nameToLowerCase.apply(user.getName()));
        }
        if(user.getBirthDate()!= null){
            userToUpdate.setBirthDate(user.getBirthDate());
        }
    }

    Function<String,String> nameToLowerCase = String::toLowerCase;

}
