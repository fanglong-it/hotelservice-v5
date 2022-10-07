package fiveman.hotelservice.entities;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

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

    private String createDate;
    private String updateDate;
    private String createBy;
    private String updateBy;
    @NonNull
    private boolean status;

}
