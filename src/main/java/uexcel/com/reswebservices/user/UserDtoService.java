package uexcel.com.reswebservices.user;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import uexcel.com.reswebservices.exception.UserNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        if(user.getId()>0) {
            user.setName(user.getName().toLowerCase());
            users.add(user);
            return user;
        }

        int maxId = users.stream().mapToInt(User::getId).max().orElse(0);
        user.setId(maxId+1);
        user.setName(user.getName().toLowerCase());
        users.add(user);
        return user;
    }

    public void deleteUserById(int id) {
        User user = findUserById(id);
        users.remove(user);
    }

}
