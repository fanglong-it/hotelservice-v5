package fiveman.hotelservice.entities;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "hotel")
public class Hotel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(required = true)
    private long id;
    @NotBlank(message = "Name are mandatory")
    private String shortName;

    @NotBlank(message = "Full Name are mandatory")
    private String fullName;

    @NotBlank(message = "phone are mandatory")
    @Min(value = 9, message = "The min of phone is 9")
    @Max(value = 10, message = "The max of phone is 10")
    private String phoneNumber;

    //Note gio den va gio di
    //Default gio den 12AM, Default gio di la 14PM
    
    private String address;
    private String email;
    private String website;
    private String checkInTime;
    private String checkOutTime;
    private boolean breakfast;
    private int totalArea;
    private int totalRoom;
    private String description;
    private String longitude;
    private String latitude;

    private boolean status;

    private String createDate;
    private String updateDate;
    private String createBy;
    private String lastModifyBy;
    
}
