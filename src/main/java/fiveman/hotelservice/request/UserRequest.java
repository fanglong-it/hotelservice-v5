package fiveman.hotelservice.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {
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
    @Max(value = 11, message = "Phone max is 11")
    private String phoneNumber;

    private String dateOfBirth;

}
