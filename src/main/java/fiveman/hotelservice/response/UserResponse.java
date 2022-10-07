package fiveman.hotelservice.response;


import fiveman.hotelservice.entities.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserResponse {
    @ApiModelProperty(position = 0)
    private Long id;
    @ApiModelProperty(position = 1)
    private String name;
    @ApiModelProperty(position = 2)
    private String username;
    @ApiModelProperty(position = 3)
    private List<Role> roles = new ArrayList<>();
}
