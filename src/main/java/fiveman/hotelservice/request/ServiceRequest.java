package fiveman.hotelservice.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Data
@Getter
@Setter
public class ServiceRequest {

    @ApiModelProperty(required = true)
    private long id;

    @ApiModelProperty(required = true)
    @NotBlank(message = "Name are mandatory")
    private String name;


    @ApiModelProperty(required = true)
    @NotBlank(message = "price are mandatory")
    private double price;

    @NotBlank(message = "description are mandatory")
    private String description;

    private boolean status;

    private String createDate;
    private String updateDate;
    private String createBy;
    private String updateBy;

    @ApiModelProperty(required = true)
    private long serviceCategory_Id;

}
