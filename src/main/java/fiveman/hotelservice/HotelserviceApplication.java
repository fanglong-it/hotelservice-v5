package fiveman.hotelservice;


import fiveman.hotelservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class HotelserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(HotelserviceApplication.class, args);
    }



    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {
//            userService.saveRole(new Role(0, "ROLE_USER"));
//            userService.saveRole(new Role(0, "ROLE_MANAGER"));
//            userService.saveRole(new Role(0, "ROLE_ADMIN"));
//            userService.saveRole(new Role(0, "ROLE_SUPER_ADMIN"));
//
//            userService.saveUser(new User(0, "john travola", "john", "123456", new ArrayList<>()));
//            userService.saveUser(new User(0, "mouse", "mickey", "123456", new ArrayList<>()));
//            userService.saveUser(new User(0, "abert ainsten", "enstent", "123456", new ArrayList<>()));
//            userService.saveUser(new User(0, "nikola tesla", "tesla", "123456", new ArrayList<>()));

//            userService.addRoleToUser("john", "ROLE_USER");
//            userService.addRoleToUser("mickey", "ROLE_MANAGER");
//            userService.addRoleToUser("enstent", "ROLE_ADMIN");
//            userService.addRoleToUser("tesla", "ROLE_SUPER_ADMIN");
//

        };
    }
}
