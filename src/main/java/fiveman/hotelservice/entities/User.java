package fiveman.hotelservice.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(required = true)
    private long id;

    @ApiModelProperty(required = true)
    private String username;

    @ApiModelProperty(required = true)
    private String password;

    private String firstName;
    private String middleName;
    private String lastName;

    @NonNull
    private boolean gender;

    @Min(value = 9, message = "Phone min is 9")
    @Max(value = 10, message = "Phone max is 10")
    private String phoneNumber;

    private String dateOfBirth;

    @ElementCollection(fetch = FetchType.EAGER)
    List<AppUserRole> appUserRoles;

    @NonNull
    private boolean isActive;

    @ManyToOne(fetch = FetchType.EAGER)
    private Hotel hotel;

}
