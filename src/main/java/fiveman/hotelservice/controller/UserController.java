package fiveman.hotelservice.controller;


import fiveman.hotelservice.entities.User;
import fiveman.hotelservice.request.UserRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.UserResponse;
import fiveman.hotelservice.service.UserService;
import fiveman.hotelservice.utils.Common;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.*;


@RestController
@Api(tags = "User")
@RequestMapping("/api/v1")
public class UserController {
	
    @Autowired
    private UserService userService;
    
    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    @ApiOperation(value = "GetAllUser", response = User.class, authorizations = { @Authorization(value="apiKey") })
    public ResponseEntity<List<UserResponse>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }


    @PostMapping("/login")
    @ApiOperation(value = "Login")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public String login(//
                        @ApiParam("Username") @RequestParam String username, //
                        @ApiParam("Password") @RequestParam String password) {
        return userService.signin(username, password);
    }

    @PostMapping("/signup")
    @ApiOperation( value = "Registration")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<CustomResponseObject> signup(@RequestBody UserRequest user){
    	userService.signup(user);
        return new ResponseEntity<CustomResponseObject>(new CustomResponseObject(Common.ADDING_SUCCESS, "Add user success: " + user.getName()), HttpStatus.OK);
    }
    

    @GetMapping(value = "/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation(value = "Get Current User", response = UserResponse.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public UserResponse whoami(HttpServletRequest req) {
        return modelMapper.map(userService.whoami(req), UserResponse.class);
    }






}
