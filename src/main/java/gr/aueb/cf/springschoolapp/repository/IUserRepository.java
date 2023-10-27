package gr.aueb.cf.springschoolapp.repository;

import gr.aueb.cf.springschoolapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findUserByUsernameEquals(String username);
//    User getUserByUsernameEquals(String username);
    User getById(Long id);
    List<User> getUsersByUsernameStartingWith(String username);

    @Query("SELECT count(*) > 0 FROM User U WHERE U.username = ?1")
    boolean isUsernameValid(String username);
}
