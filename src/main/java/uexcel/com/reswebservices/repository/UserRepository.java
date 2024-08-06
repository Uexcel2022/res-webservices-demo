package uexcel.com.reswebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uexcel.com.reswebservices.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
