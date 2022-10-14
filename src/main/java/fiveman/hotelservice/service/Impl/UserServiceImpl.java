package fiveman.hotelservice.service.Impl;

import fiveman.hotelservice.entities.User;
import fiveman.hotelservice.entities.UserRole;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.UserRepository;
import fiveman.hotelservice.request.UserRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.UserResponse;
import fiveman.hotelservice.security.JwtTokenProvider;
import fiveman.hotelservice.service.UserService;
import fiveman.hotelservice.utils.Common;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public User saveUser(User user) {
//        List<Role> roles = new ArrayList<>();
//        roles.add(roleRepository.findRoleByName(Common.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

//    @Override
//    public Role saveRole(Role role) {
//        logger.info("START TO ADD ROLE");
//        roleRepository.save(role);
//        return roleRepository.findRoleByName(role.getName());
//    }

//	@Override
//	public String addRoleToUser(String username, String roleName) {
//		return null;
//	}

//	@Override
//	public String addRoleToUser(String username, String roleName) {
//		logger.info("START ADD ROLE TO USER");
//		if (userRepository.existsByUsername(username) || !roleName.equals(Common.ADMIN)) {
//			User user = userRepository.findUserByUsername(username);
//			Role role = roleRepository.findRoleByName(roleName);
//			List<Role> roles = user.getRoles();
//			roles.add(role);
//			user.setRoles(roles);
//			userRepository.save(user);
//		} else {
//			throw new AppException(HttpStatus.NOT_FOUND.value(),
//					new CustomResponseObject(Common.GET_FAIL, "UserName has already existed"));
//		}
//
//		logger.info("END ADD ROLE TO USER");
//		return username;
//	}

//    @Override
//    public User getUser(String username) {
//        logger.info("START GET USER BY USER NAME");
//        return userRepository.findUserByUsername(username);
//    }

    @Override
    public List<UserResponse> getUsers() {
        logger.info("START GET ALL USER");
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            UserResponse userResponse = modelMapper.map(users.get(i), UserResponse.class);
            userResponses.add(userResponse);
        }

        logger.info("END GET ALL USER");
        return userResponses;
    }


    public UserResponse mapUserToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setPassword(user.getPassword());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setMiddleName(user.getMiddleName());
        userResponse.setLastName(user.getLastName());
        userResponse.setGender(user.isGender());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setDateOfBirth(user.getDateOfBirth());
        userResponse.setActive(user.isActive());
        userResponse.setUserRole(user.getUserRole().toString());
        userResponse.setHotel_Id(user.getHotel().getId());
        return userResponse;
    }

    @Override
    public UserResponse whoami(HttpServletRequest request) {
        String username = jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(request));
        User user = userRepository.findUserByUsername(username);
        UserResponse userResponse = mapUserToUserResponse(user);
        return userResponse;
    }

    public String signin(String username, String password) {
        logger.info("START CHECK SIGN IN");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            User user = userRepository.findUserByUsername(username);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();


            logger.info("END CHECK SIGN IN");
            List<UserRole> userRoles = new ArrayList<>();
            userRoles.add(user.getUserRole());
            return jwtTokenProvider.createToken(username, userRoles);
        } catch (AuthenticationException e) {
            throw new AppException(HttpStatus.NOT_FOUND.value(),
                    new CustomResponseObject(Common.GET_FAIL, "INVALID USER NAME OR PASSWORD"));
        }
    }

    @Override
    public User signup(User user) {
        logger.info("START CHECK REGISTRATION");
        if (userRepository.existsByUsername(user.getUsername())) {
			throw new AppException(HttpStatus.UNPROCESSABLE_ENTITY.value(),
					new CustomResponseObject(Common.ADDING_FAIL, "Username is already in use"));
		}
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUsername(user.getUsername());
            user.setUserRole(UserRole.ROLE_USER);
            user.setActive(true);
            userRepository.save(user);

            logger.info("END CHECK REGISTRATION");
            return user;
        }

        @Override
        public String refresh (String username) {
            User user = userRepository.findUserByUsername(username);
            List<UserRole> userRoles = new ArrayList<>();
            userRoles.add(user.getUserRole());
            return jwtTokenProvider.createToken(username, userRoles);
        }

//        public static User MapUserRequestToUser (UserRequest user){
//            return new User(0, user.getName(), user.getUserName(), user.getPassword(), null);
//        }

//        @Override
//        public String setRoleAdmin (String userName, String roleName){
//            logger.info("START SET ROLE ADMIN FOR ACCOUNT ");
//            if (userRepository.existsByUsername(userName) && roleName.equals(Common.ADMIN)) {
//                User user = userRepository.findUserByUsername(userName);
////                Role role = roleRepository.findRoleByName(userName);
////                List<Role> roles = user.getRoles();
////                roles.add(role);
////                user.setRoles(roles);
//                userRepository.save(user);
//            } else {
//                throw new AppException(HttpStatus.NOT_FOUND.value(),
//                        new CustomResponseObject(Common.GET_FAIL, "NOT Found UserName"));
//            }
//
//            logger.info("END SET ROLE ADMIN FOR ACCOUNT ");
//
//            return userName;
//        }
}
