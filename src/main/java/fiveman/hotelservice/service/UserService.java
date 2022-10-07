package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.Role;
import fiveman.hotelservice.entities.User;
import fiveman.hotelservice.request.UserRequest;
import fiveman.hotelservice.response.UserResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    String addRoleToUser(String username, String roleName);
    String setRoleAdmin(String userName, String roleName);
    User getUser(String username);
    List<UserResponse> getUsers();
    String signin(String username, String password);
    UserRequest signup(UserRequest user);
    User whoami(HttpServletRequest request);
    String refresh(String username);
}
