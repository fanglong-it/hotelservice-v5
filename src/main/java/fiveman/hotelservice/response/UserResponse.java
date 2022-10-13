package fiveman.hotelservice.response;


import fiveman.hotelservice.entities.Hotel;
import fiveman.hotelservice.entities.UserRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponse {

    private long id;
    private String username;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private boolean gender;
    private String phoneNumber;
    private String dateOfBirth;
    private String userRole;
    private boolean isActive;
    private long hotel_Id;

}
