package web.service;

import org.springframework.stereotype.Repository;
import web.exeptions.UserNotFoundException;
import web.model.User;

import java.util.List;
@Repository
public interface UserService {
    void add(User user);
    List<User> listUsers();
    User getUser(long id) throws UserNotFoundException;
    void removeUser(long id) throws UserNotFoundException;

}
