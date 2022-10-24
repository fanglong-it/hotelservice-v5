package fiveman.hotelservice.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@Getter
public class ServiceCategoryRequest {

    @ApiModelProperty(required = true)
    private long id;
    
    @NotBlank(message = "Name are mandatory")
    private String name;
    
    @NotBlank(message = "description are mandatory")
    private String description;

    private Boolean status;

    private boolean foodAndBeverage;

    @ApiModelProperty( required = true)
    private long hotel_Id;

}
