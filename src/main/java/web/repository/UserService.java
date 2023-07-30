package web.repository;

import org.springframework.stereotype.Repository;
import web.model.User;

import java.util.List;
@Repository
public interface UserService {
    void add(User user);
    List<User> listUsers();
    User getUser(long id);
    void removeUser(long id);

}
