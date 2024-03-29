package fiveman.hotelservice.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

    private boolean gender;

    @Min(value = 9, message = "Phone min is 9")
    @Max(value = 10, message = "Phone max is 10")
    private String phoneNumber;

    private String dateOfBirth;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Hotel.class)
    @JsonBackReference
    private Hotel hotel;

    private String createDate;
    private String updateDate;
    private String createBy;
    private String lastModifyBy;

}
