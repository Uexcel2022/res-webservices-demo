package uexcel.com.reswebservices.service;
import org.springframework.stereotype.Component;
import uexcel.com.reswebservices.exception.UserNotFoundException;
import uexcel.com.reswebservices.user.User;
import uexcel.com.reswebservices.repository.UserRepository;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

@Component
public class UserDtoJpaService {
    private final UserRepository userRepository;

    public UserDtoJpaService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(int id) {
       return userRepository.findById(id)
               .orElseThrow( () -> new UserNotFoundException("id:"+id));
    }

    public User saveUser(User user) {
       return userRepository.save(user);
    }

    public void deleteUserById(int id) {
         findUserById(id);
         userRepository.deleteById(id);
    }

    public void updateUser(User user) {
        User userToUpdate = findUserById(user.getId());
        if(user.getName()!=null) {
            userToUpdate.setName(nameToLowerCase.apply(user.getName()));
        }
        if(user.getBirthDate()!= null){
            userToUpdate.setBirthDate(user.getBirthDate());
        }
        userRepository.save(userToUpdate);
    }

    Function<String,String> nameToLowerCase = String::toLowerCase;

}
