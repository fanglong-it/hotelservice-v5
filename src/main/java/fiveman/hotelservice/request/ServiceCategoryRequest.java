package fiveman.hotelservice.request;

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
    @ApiModelProperty(position = 0, required = true)
    private long id;
    @ApiModelProperty(position = 1)
    @NotBlank(message = "Name are mandatory")
    private String name;
    @ApiModelProperty(position = 2)
    @NotBlank(message = "description are mandatory")
    private String description;

    @NonNull
    private Boolean status;

    @ApiModelProperty(position = 4, required = true)
    private long hotel_Id;
}
