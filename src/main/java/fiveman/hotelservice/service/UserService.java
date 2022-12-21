package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.User;
import fiveman.hotelservice.request.UserRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    User saveUser(User user);

    List<User> getUsers();

    String signin(String username, String password);

    User signup(UserRequest userRequest);

    User updateUser(UserRequest userRequest);

    User deleteUser(long userId);

    User whoami(HttpServletRequest request);

    String refresh(String username);

    List<String> getRoles();

}
