package fiveman.hotelservice;

import fiveman.hotelservice.entities.User;
import fiveman.hotelservice.entities.UserRole;
import fiveman.hotelservice.service.HotelService;
import fiveman.hotelservice.service.UserService;
import fiveman.hotelservice.service.Impl.HotelServiceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
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
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Autowired
    HotelService hotelService;

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            // userService.saveRole(new Role(0, "ROLE_USER"));
            // userService.saveRole(new Role(0, "ROLE_MANAGER"));
            // userService.saveRole(new Role(0, "ROLE_ADMIN"));
            // userService.saveRole(new Role(0, "ROLE_SUPER_ADMIN"));
            //
            // userService.saveUser(new User(0, "john travola", "john", "123456", new
            // ArrayList<>()));
            // userService.saveUser(new User(0, "mouse", "mickey", "123456", new
            // ArrayList<>()));
            // userService.saveUser(new User(0, "abert ainsten", "enstent", "123456", new
            // ArrayList<>()));
            // userService.saveUser(new User(0, "nikola tesla", "tesla", "123456", new
            // ArrayList<>()));

            // userService.addRoleToUser("john", "ROLE_USER");
            // userService.addRoleToUser("mickey", "ROLE_MANAGER");
            // userService.addRoleToUser("enstent", "ROLE_ADMIN");
            // userService.addRoleToUser("tesla", "ROLE_SUPER_ADMIN");
            //



            // User admin = new User();
            // admin.setId(0);
            // admin.setUsername("admin");
            // admin.setFirstName("admin@gmail.com");
            // admin.setMiddleName("admin");
            // admin.setLastName("admin");
            // admin.setPassword("admin");
            // admin.setHotel(hotelService.getHotelById(Long.valueOf("1")));
            // admin.setUserRole(UserRole.ROLE_ADMIN);
            // userService.signup(admin);
            
            // User manager = new User();
            // manager.setId(0);
            // manager.setUsername("manager");
            // manager.setFirstName("manager@gmail.com");
            // manager.setMiddleName("manager");
            // manager.setLastName("manager");
            // manager.setPassword("manager");
            // manager.setUserRole(UserRole.ROLE_MANAGER);
            // manager.setHotel(hotelService.getHotelById(Long.valueOf("1")));
            // userService.signup(manager);
            // // ROLE_STAFF, ROLE_RECEPTIONIST, ROLE_HOUSEKEEPING, ROLE_RESTAURANT;
            // User staff = new User();
            // staff.setId(0);
            // staff.setUsername("staff");
            // staff.setFirstName("staff@gmail.com");
            // staff.setMiddleName("staff");
            // staff.setLastName("staff");
            // staff.setPassword("staff");
            // staff.setUserRole(UserRole.ROLE_STAFF);
            // staff.setHotel(hotelService.getHotelById(Long.valueOf("1"))); 
            // userService.signup(staff);

            // User receptionist = new User();
            // receptionist.setId(0);
            // receptionist.setUsername("receptionist");
            // receptionist.setFirstName("receptionist@gmail.com");
            // receptionist.setMiddleName("receptionist");
            // receptionist.setLastName("receptionist");
            // receptionist.setPassword("receptionist");
            // receptionist.setUserRole(UserRole.ROLE_RECEPTIONIST);
            // receptionist.setHotel(hotelService.getHotelById(Long.valueOf("1"))); 
            // userService.signup(receptionist);


            // User housekeeping = new User();
            // housekeeping.setId(0);
            // housekeeping.setUsername("housekeeping");
            // housekeeping.setFirstName("housekeeping@gmail.com");
            // housekeeping.setMiddleName("housekeeping");
            // housekeeping.setLastName("housekeeping");
            // housekeeping.setPassword("housekeeping");
            // housekeeping.setUserRole(UserRole.ROLE_HOUSEKEEPING);
            // housekeeping.setHotel(hotelService.getHotelById(Long.valueOf("1"))); 
            // userService.signup(housekeeping);


            // User restaurant = new User();
            // restaurant.setId(0);
            // restaurant.setUsername("restaurant");
            // restaurant.setFirstName("restaurant@gmail.com");
            // restaurant.setMiddleName("restaurant");
            // restaurant.setLastName("restaurant");
            // restaurant.setPassword("restaurant");
            // restaurant.setUserRole(UserRole.ROLE_RESTAURANT);
            // restaurant.setHotel(hotelService.getHotelById(Long.valueOf("1"))); 
            // userService.signup(restaurant);


            //
            // User user = new User();
            // user.setId(2);
            // user.setUsername("user");
            // user.setFirstName("Client@gmail.com");
            // user.setPassword("user");
            // user.setAppUserRoles(new
            // ArrayList<AppUserRole>(Arrays.asList(AppUserRole.ROLE_USER)));
            // userService.signup(user);

        };
    }
}
